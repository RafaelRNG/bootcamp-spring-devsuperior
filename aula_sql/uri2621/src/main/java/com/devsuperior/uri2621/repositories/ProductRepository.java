package com.devsuperior.uri2621.repositories;

import com.devsuperior.uri2621.dtos.ProductMinDto;
import com.devsuperior.uri2621.projections.ProductMinProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.uri2621.entities.Product;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT products.name FROM products INNER JOIN providers ON products.id_providers = providers.id WHERE products.amount BETWEEN :min AND :max AND providers.name LIKE CONCAT(:beginName, '%')", nativeQuery = true)
    List<ProductMinProjection> searchSQL(Integer min, Integer max, String beginName);

    @Query(value = "SELECT new com.devsuperior.uri2621.dtos.ProductMinDto(prod.name) FROM Product prod WHERE prod.amount BETWEEN :min AND :max AND prod.provider.name LIKE CONCAT(:beginName, '%')")
    List<ProductMinDto> searchJPQL(Integer min, Integer max, String beginName);
}