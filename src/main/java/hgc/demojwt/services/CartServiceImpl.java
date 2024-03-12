package hgc.demojwt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hgc.demojwt.entitys.Cart;
import hgc.demojwt.repository.CartRepository;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
    private final CartRepository cartRepository;

    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    @Override
    public Cart getCartById(Integer cartId) {
        return cartRepository.findById(cartId).orElse(null);
    }

    @Override
    public Cart createCart(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public Cart updateCart(Integer cartId, Cart cart) {
        if (cartRepository.existsById(cartId)) {
            cart.setId(cartId);
            return cartRepository.save(cart);
        }
        return null;
    }

    @Override
    public boolean deleteCart(Integer cartId) {
        if (cartRepository.existsById(cartId)) {
            cartRepository.deleteById(cartId);
            return true;
        }
        return false;
    }
}
