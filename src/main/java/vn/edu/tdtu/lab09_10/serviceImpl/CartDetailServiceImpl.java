package vn.edu.tdtu.lab09_10.serviceImpl;

import org.springframework.stereotype.Service;
import vn.edu.tdtu.lab09_10.model.CartDetail;
import vn.edu.tdtu.lab09_10.repository.CartDetailRepository;
import vn.edu.tdtu.lab09_10.service.CartDetailService;

import java.util.List;

@Service
public class CartDetailServiceImpl implements CartDetailService {
    private CartDetailRepository cartDetailRepository;
    public CartDetailServiceImpl(CartDetailRepository cartDetailRepository) {
        super();
        this.cartDetailRepository = cartDetailRepository;
    }

    @Override
    public List<CartDetail> getAllCartDetails() {
        return cartDetailRepository.findAll();
    }

    @Override
    public List<CartDetail> getCartDetailsById_cart(Integer id_cart) {
        return cartDetailRepository.findCartDetailsById_cart(id_cart);
    }

    @Override
    public CartDetail saveCartDetail(CartDetail cartDetail) {
        return cartDetailRepository.save(cartDetail);
    }

    @Override
    public CartDetail getCartDetailById(Integer id) {
        return cartDetailRepository.findById(id).get();
    }

    @Override
    public CartDetail updateCartDetail(CartDetail cartDetail) {
        return cartDetailRepository.save(cartDetail);
    }

    @Override
    public void deleteCartDetailById(Integer id) {
        cartDetailRepository.deleteById(id);
    }
}