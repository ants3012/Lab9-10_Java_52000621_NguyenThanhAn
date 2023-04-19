package vn.edu.tdtu.lab09_10.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.edu.tdtu.lab09_10.model.Cart;
import vn.edu.tdtu.lab09_10.model.Order;
import vn.edu.tdtu.lab09_10.service.CartDetailService;
import vn.edu.tdtu.lab09_10.service.CartService;
import vn.edu.tdtu.lab09_10.service.OrderService;
import vn.edu.tdtu.lab09_10.service.UserService;

import java.sql.Date;

@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;
    private CartService cartService;
    private CartDetailService cartDetailService;
    private UserService userService;

    public OrderController(OrderService orderService, CartService cartService, CartDetailService cartDetailService, UserService userService) {
        super();
        this.orderService = orderService;
        this.cartService = cartService;
        this.cartDetailService = cartDetailService;
        this.userService = userService;
    }

//  ADMIN
    @GetMapping("/admin/orders")
    public String listOrders(Model model) {
        model.addAttribute("orders", orderService.getAllOrders());
        return "admin_orders";
    }

    @GetMapping("/admin/orders/confirm/{id}")
    public String confirmOrder(@PathVariable Integer id) {
        Order order = orderService.getOrderById(id);
        order.setStatus("CONFIRMED");
        orderService.saveOrder(order);
        return "redirect:/admin/orders";
    }

    @GetMapping("/admin/orders/detail/{id}")
    public String showOrderDetail(@PathVariable Integer id, Model model) {
        model.addAttribute("order", orderService.getOrderById(id));

        model.addAttribute("cartDetails",
                cartDetailService.getCartDetailsById_cart(orderService.getOrderById(id).getId_cart()));
        return "detail_order";
    }

    @GetMapping("/admin/orders/{id}")
    public String deleteOrder(@PathVariable Integer id) {
        orderService.deleteOrderById(id);
        return "redirect:/admin/orders";
    }

//    USER
    @GetMapping("/order/new")
    public String createOrderForm(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer id_user = (Integer) session.getAttribute("user");
        model.addAttribute("username", userService.getUserById(id_user).getUsername());
        Cart currentCart = cartService.getCartByCheckout(false);
        model.addAttribute("total", cartService.getCartById(currentCart.getId()).getTotal());
        model.addAttribute("cartDetails", cartDetailService.getCartDetailsById_cart(currentCart.getId()));
        return "create_order";
    }

    @RequestMapping(value="/order/new",method = RequestMethod.POST)
    public String saveOrder(@RequestParam("phone") String phone,
                            @RequestParam("address") String address,
                            @RequestParam("date_create") Date dateCreate,
                            Model model) {
        Cart currentCart = cartService.getCartByCheckout(false);
        Order order = new Order(currentCart.getId(),
                userService.getUserById(currentCart.getId_user()).getUsername(),
                phone,
                address,
                dateCreate,
                currentCart.getTotal(),
                "UNCONFIRMED");
        orderService.saveOrder(order);
        currentCart.setCheckout(true);
        cartService.saveCart(currentCart);
        return "thank";
    }
}