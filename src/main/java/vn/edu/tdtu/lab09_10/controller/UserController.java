package vn.edu.tdtu.lab09_10.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.edu.tdtu.lab09_10.model.User;
import vn.edu.tdtu.lab09_10.service.UserService;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        super();
        this.userService = userService;
    }

    @GetMapping({ "/admin"})
    public String welcomeAdmin(Model model) {
        return "admin_index";
    }

    //ADMIN
    @GetMapping("/admin/users")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin_users";
    }

    @GetMapping("/admin/users/{id}")
    public String deleteUser(@PathVariable Integer id) {
        userService.deleteUserById(id);
        return "redirect:/admin/users";
    }

    //USER
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        // create user object to hold user form data
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register")
    public String saveUser(@ModelAttribute(name = "user") User user, RedirectAttributes redirectAttributes) {
        User existUser = null;
        existUser = userService.getUserByUsername(user.getUsername());

        if (existUser != null) {
            redirectAttributes.addFlashAttribute("failRegister", "Username is already exist!!!");
            return "redirect:/register";
        }

        if (!user.getPassword().equals(user.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("failRegister", "Password authentication is incorrect!!!");
            return "redirect:/register";
        }

        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashedPassword);
        userService.saveUser(user);
        return "redirect:/index";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        return "login";
    }

    @PostMapping("/login")
    public String checkLogin(@ModelAttribute(name = "user") User user, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        User existUser = null;
        HttpSession session = request.getSession();
        existUser = userService.getUserByUsername(user.getUsername());
        if (existUser != null) {
            if (BCrypt.checkpw(user.getPassword(), existUser.getPassword())) {
                if (existUser.getRole().equals("USER")){
                    session.setAttribute("user", existUser.getId());
                    return "redirect:/index";
                }
                return "redirect:/admin";
            }
            if (existUser.getPassword().equals(user.getPassword())) {
                if (existUser.getRole().equals("USER")){
                    session.setAttribute("user", existUser.getId());
                    return "redirect:/index";
                }
                return "redirect:/admin";
            }
            else {
                redirectAttributes.addFlashAttribute("failLogin", "Username or password is incorrect");
                return "redirect:/login";
            }
        }
        redirectAttributes.addFlashAttribute("failLogin", "Username or password is incorrect");
        return "redirect:/login";
    }
}