package vn.edu.tdtu.lab09_10.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import vn.edu.tdtu.lab09_10.model.Product;
import vn.edu.tdtu.lab09_10.service.ProductService;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    public ProductController(ProductService productService) {
        super();
        this.productService = productService;
    }

    //ADMIN
    @GetMapping("/admin/products")
    public String listProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "admin_products";
    }

    @GetMapping("/index/product/{id}")
    public String showDetailProduct(@PathVariable Integer id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        return "detail_product";
    }

    @GetMapping("/admin/products/new")
    public String createProductForm(Model model) {
        // create product object to hold product form data
        Product product = new Product();
        model.addAttribute("product", product);
        return "create_product";
    }

    @RequestMapping(value="/admin/products/new", method = RequestMethod.POST)
    public String saveProduct(Model model,@RequestParam("image") MultipartFile multipartFile,
                              @RequestParam("name") String name,
                              @RequestParam("category") String category,
                              @RequestParam("price") Double price,
                              @RequestParam("brand") String brand) throws IOException {
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            String uploadDir = "src/main/resources/static/images/product_images/";
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            try (InputStream inputStream = multipartFile.getInputStream()) {
                Path filePath = uploadPath.resolve(fileName);
                System.out.println(filePath.toFile().getAbsolutePath());
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new IOException("Could not save uploaded file: " + fileName);
            }

            String imagePath = fileName;
            Product product = new Product(name, category, price, brand, imagePath);
            productService.saveProduct(product);
        return "redirect:/admin/products";

    }

    @GetMapping("/admin/products/edit/{id}")
    public String editProductForm(@PathVariable Integer id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        return "edit_product";
    }
    @PostMapping("/admin/products/{id}")
    public String updateProduct(@PathVariable Integer id, @ModelAttribute("product") Product product, Model model) {
        // get student from database by id
        Product existingProduct = productService.getProductById(id);
        existingProduct.setId(id);
        existingProduct.setName(product.getName());
        existingProduct.setCategory(product.getCategory());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setBrand(product.getBrand());
        existingProduct.setImage(product.getImage());
        productService.updateProduct(existingProduct);
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/products/{id}")
    public String deleteProduct(@PathVariable Integer id) {
        productService.deleteProductById(id);
        return "redirect:/admin/products";
    }
}