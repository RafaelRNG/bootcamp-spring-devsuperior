package br.com.rng.dscatalog.repositories;

import br.com.rng.dscatalog.entities.Product;
import br.com.rng.dscatalog.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Optional;

@DataJpaTest
public class ProductReposioryTests {

    @Autowired
    private ProductRepository productRepository;

    private Long exintingId;
    private Long nonExistingId;
    private Long countTotatlProducts;

    @BeforeEach
    void setUp() throws Exception {
        exintingId = 1L;
        nonExistingId = 1000L;
        countTotatlProducts = 25L;
    }

    @Test
    public void findProductShouldMustNotReturnProductWhenIdIsNotNull() {

        Optional<Product> product = productRepository.findById(nonExistingId);

        Assertions.assertFalse(product.isPresent());
    }

    @Test
    public void findProductShouldReturnProductWhenIdIsNotNull() {

        Optional<Product> product = productRepository.findById(exintingId);

        Assertions.assertTrue(product.isPresent());
    }

    @Test
    public void saveShouldPersistWithAutoIncrementWhenIdIsNull() {
        Product product = Factory.createProduct();
        product.setId(null);
        product = productRepository.save(product);

        Assertions.assertNotNull(product.getId());
        Assertions.assertEquals(countTotatlProducts + 1, product.getId());
    }

    @Test
    public void deleteShouldDeleteObjectWhenIdExists() {

        productRepository.deleteById(1L);

        Optional<Product> result = productRepository.findById(exintingId);

        Assertions.assertFalse(result.isPresent());
    }

    @Test
    public void deleteShouldThrowEmptyResultDataAccessExceptionWhenidDoesNotExist() {
        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
            productRepository.deleteById(nonExistingId);
        });
    }
}