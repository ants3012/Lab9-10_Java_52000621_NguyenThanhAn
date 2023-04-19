package vn.edu.tdtu.lab09_10.serviceImpl;

import org.springframework.stereotype.Service;
import vn.edu.tdtu.lab09_10.model.Product;
import vn.edu.tdtu.lab09_10.repository.ProductRepository;
import vn.edu.tdtu.lab09_10.service.ProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    public ProductServiceImpl(ProductRepository productRepository) {
        super();
        this.productRepository = productRepository;
    }
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getProductById(Integer id) {
        return productRepository.findById(id).get();
    }

    @Override
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProductById(Integer id) {
        productRepository.deleteById(id);
    }
}