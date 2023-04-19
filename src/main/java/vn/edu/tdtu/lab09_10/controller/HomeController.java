package vn.edu.tdtu.lab09_10.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import vn.edu.tdtu.lab09_10.service.ProductService;
import org.springframework.ui.Model;

@Controller
public class HomeController {
    @Autowired
    private ProductService productService;

    public HomeController(ProductService productService) {
        super();
        this.productService = productService;
    }

    @GetMapping(value={"/","/index"})
    public String showProduct(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer idUser = (Integer) session.getAttribute("user");
        if(idUser != null){
            model.addAttribute("products", productService.getAllProducts());
            return "index";
        }
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session=request.getSession();
        session.removeAttribute("user");
        return "redirect:/login";
    }
}