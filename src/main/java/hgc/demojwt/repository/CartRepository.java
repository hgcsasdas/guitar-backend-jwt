package hgc.demojwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hgc.demojwt.entitys.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer>{

}
