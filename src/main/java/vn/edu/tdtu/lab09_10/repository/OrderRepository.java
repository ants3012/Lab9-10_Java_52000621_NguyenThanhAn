package vn.edu.tdtu.lab09_10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.tdtu.lab09_10.model.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> { }