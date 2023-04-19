package vn.edu.tdtu.lab09_10.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import vn.edu.tdtu.lab09_10.model.Cart;
import vn.edu.tdtu.lab09_10.model.CartDetail;
import vn.edu.tdtu.lab09_10.model.Product;
import vn.edu.tdtu.lab09_10.service.CartDetailService;
import vn.edu.tdtu.lab09_10.service.CartService;
import vn.edu.tdtu.lab09_10.service.ProductService;
import vn.edu.tdtu.lab09_10.service.UserService;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController {
    @Autowired
    private CartService cartService;
    private CartDetailService cartDetailService;
    private ProductService productService;
    private UserService userService;

    public CartController(CartService cartService, CartDetailService cartDetailService, ProductService productService, UserService userService) {
        this.cartService = cartService;
        this.cartDetailService = cartDetailService;
        this.productService = productService;
        this.userService = userService;
    }

//    ADMIN
    @GetMapping("/admin/carts")
    public String listCarts(Model model) {
        model.addAttribute("carts", cartService.getAllCarts());
        return "admin_carts";
    }

    @GetMapping("/admin/carts/detail/{id}")
    public String showCartDetail(@PathVariable Integer id, Model model) {
        model.addAttribute("cart", cartService.getCartById(id));
        model.addAttribute("cartDetails", cartDetailService.getCartDetailsById_cart(id));
        return "detail_cart";
    }

    @GetMapping("/admin/carts/{id}")
    public String deleteCart(@PathVariable Integer id) {
        cartService.deleteCartById(id);
        return "redirect:/admin/carts";
    }

//    USER
    @GetMapping("/cart")
    public String showCart(Model model) {
        Cart currentCart = cartService.getCartByCheckout(false);
        if (currentCart != null)
            model.addAttribute("cartDetails", cartDetailService.getCartDetailsById_cart(currentCart.getId()));
        else {
            model.addAttribute("cartIsEmpty", "Cart is empty. Return to home page and buy now!");
        }
        return "cart";
    }

    @GetMapping("/cart/cartDetail/{id}")
    public String deleteProductFromCart(@PathVariable Integer id) {
        cartDetailService.deleteCartDetailById(id);
        return "redirect:/cart";
    }

    @RequestMapping(value="/updateCard/{id}",method = RequestMethod.POST)
    public String saveCartDetail(@PathVariable Integer id,
                           @RequestParam("quantity") int quantity,
                           @RequestParam("current_day") Date dateCreate,
                           HttpServletRequest request,
                           Model model) {
        HttpSession session = request.getSession();
        Integer id_user = (Integer) session.getAttribute("user");
        List<Cart> cartList = cartService.getAllCarts();
        List<Cart> existCartList = new ArrayList<>();
        for (Cart cart : cartList) {
            if(!cart.isCheckout())
                existCartList.add(cart);
        }

        if (existCartList.size() == 0) {
            Cart cart = new Cart();
            cart.setId_user(id_user);
            cart.setDateCreate(dateCreate);
            cart.setCheckout(false);
            cartService.saveCart(cart);
            existCartList.add(cart);
        }

        for (Cart existCard : existCartList) {
             if (!existCard.isCheckout()) {
                List<CartDetail> cartDetailList = cartDetailService.getCartDetailsById_cart(existCard.getId());
                Product product = productService.getProductById(id);

                for (CartDetail cartDetail : cartDetailList) {
                    if (product.getId() == cartDetail.getId_product()) {
                        int total_quantity = cartDetail.getQuantity() + quantity;
                        cartDetail.setQuantity(total_quantity);
                        cartDetail.setSum_price(product.getPrice() * total_quantity);
                        cartDetailService.saveCartDetail(cartDetail);
                        return "redirect:/cart";
                    }
                }

                double sum_price = product.getPrice() * quantity;
                CartDetail cartDetail = new CartDetail(existCard.getId(), product.getId(), product.getName(), quantity, product.getPrice(), sum_price);
                cartDetailService.saveCartDetail(cartDetail);

                List<CartDetail> cartDetailList1 = cartDetailService.getCartDetailsById_cart(existCard.getId());
                double total = 0;
                for (CartDetail cartDetail1 : cartDetailList1) {
                    total = total + cartDetail1.getSum_price();
                }
                existCard.setTotal(total);
                cartService.saveCart(existCard);
            }
            else {
                Cart cart = new Cart();
                cart.setId_user(id_user);
                cart.setDateCreate(dateCreate);
                cart.setCheckout(false);
                cartService.saveCart(cart);
            }
        }
        Cart currentCart = cartService.getCartByCheckout(false);
        model.addAttribute("cartDetails", cartDetailService.getCartDetailsById_cart(currentCart.getId()));
        return "cart";
    }
}