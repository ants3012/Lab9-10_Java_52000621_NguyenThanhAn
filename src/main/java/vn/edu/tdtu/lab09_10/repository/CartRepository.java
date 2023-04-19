package vn.edu.tdtu.lab09_10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.edu.tdtu.lab09_10.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    @Query("SELECT cart FROM Cart cart WHERE cart.id_user = ?1")
    Cart findByUserId(Integer id_user);

    @Query("SELECT cart FROM Cart cart WHERE cart.checkout = :checkout")
    Cart findCartByCheckout(@Param("checkout") Boolean checkout);
}