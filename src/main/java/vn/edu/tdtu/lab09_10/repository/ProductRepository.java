package vn.edu.tdtu.lab09_10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.tdtu.lab09_10.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> { }