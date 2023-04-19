package vn.edu.tdtu.lab09_10.service;

import vn.edu.tdtu.lab09_10.model.Product;

import java.util.List;

public interface ProductService {
    public List<Product> getAllProducts();

    public Product saveProduct(Product product);

    public Product getProductById(Integer id);

    public Product updateProduct(Product product);

    void deleteProductById(Integer id);
}