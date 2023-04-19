package vn.edu.tdtu.lab09_10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.edu.tdtu.lab09_10.model.CartDetail;

import java.util.List;

public interface CartDetailRepository extends JpaRepository<CartDetail, Integer> {

    @Query("SELECT cartDetail FROM CartDetail cartDetail WHERE cartDetail.id_cart = :id_cart")
    List<CartDetail> findCartDetailsById_cart(@Param("id_cart") Integer id_cart);
}