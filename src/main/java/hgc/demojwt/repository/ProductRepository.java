package hgc.demojwt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hgc.demojwt.entitys.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{
    List<Product> findByCategoryId(Integer categoryId);

}
