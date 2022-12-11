package br.com.rng.dscatalog.tests;

import br.com.rng.dscatalog.dto.ProductDTO;
import br.com.rng.dscatalog.entities.Category;
import br.com.rng.dscatalog.entities.Product;

public class Factory {

    public static Product createProduct() {
        Product product = new Product();
        product.getCategories().add(new Category(1L, "Electronics"));
        return product;
    }

    public static ProductDTO createProductDTO() {
        Product product = new Product();
        return new ProductDTO(product, product.getCategories());
    }
}