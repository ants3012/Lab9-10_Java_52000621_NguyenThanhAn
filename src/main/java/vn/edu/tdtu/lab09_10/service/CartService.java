package vn.edu.tdtu.lab09_10.service;

import vn.edu.tdtu.lab09_10.model.Cart;

import java.util.List;

public interface CartService {
    public List<Cart> getAllCarts();

    public Cart saveCart(Cart cart);

    public Cart getCartById(Integer id);

    public Cart getCardByUserId(Integer id_user);

    public Cart getCartByCheckout(Boolean checkout);

    public Cart updateCart(Cart cart);

    void deleteCartById(Integer id);
}