package hgc.demojwt.services;

import java.util.List;

import hgc.demojwt.entitys.Cart;

public interface CartService {
    List<Cart> getAllCarts();

    Cart getCartById(Integer cartId);

    Cart createCart(Cart cart);

    Cart updateCart(Integer cartId, Cart cart);

    boolean deleteCart(Integer cartId);
}
