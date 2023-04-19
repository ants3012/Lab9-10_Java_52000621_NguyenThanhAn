package vn.edu.tdtu.lab09_10.serviceImpl;

import org.springframework.stereotype.Service;
import vn.edu.tdtu.lab09_10.model.Cart;
import vn.edu.tdtu.lab09_10.repository.CartRepository;
import vn.edu.tdtu.lab09_10.service.CartService;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    private CartRepository cartRepository;
    public CartServiceImpl(CartRepository cartRepository) {
        super();
        this.cartRepository = cartRepository;
    }
    @Override
    public List<Cart> getAllCarts() { return cartRepository.findAll(); }

    @Override
    public Cart saveCart(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public Cart getCartById(Integer id) {
        return cartRepository.findById(id).get();
    }

    @Override
    public Cart getCardByUserId(Integer id_user) {
        return cartRepository.findByUserId(id_user);
    }

    @Override
    public Cart getCartByCheckout(Boolean checkout) {
        return cartRepository.findCartByCheckout(checkout);
    }

    @Override
    public Cart updateCart(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public void deleteCartById(Integer id) {
        cartRepository.deleteById(id);
    }
}