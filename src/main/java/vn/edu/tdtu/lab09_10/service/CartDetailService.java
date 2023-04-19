package vn.edu.tdtu.lab09_10.service;

import vn.edu.tdtu.lab09_10.model.CartDetail;

import java.util.List;

public interface CartDetailService {
    public List<CartDetail> getAllCartDetails();

    public List<CartDetail> getCartDetailsById_cart(Integer id_cart);

    public CartDetail saveCartDetail(CartDetail cartDetail);

    public CartDetail getCartDetailById(Integer id);

    public CartDetail updateCartDetail(CartDetail cartDetail);

    void deleteCartDetailById(Integer id);
}