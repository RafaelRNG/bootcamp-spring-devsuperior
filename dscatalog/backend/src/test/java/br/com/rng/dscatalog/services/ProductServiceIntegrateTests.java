package br.com.rng.dscatalog.services;

import br.com.rng.dscatalog.dto.ProductDTO;
import br.com.rng.dscatalog.repositories.ProductRepository;
import br.com.rng.dscatalog.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class ProductServiceIntegrateTests {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    private long existingId;
    private long nonExistingId;
    private long countTotalProducts;

    @BeforeEach
    void setUp() throws Exception {
        existingId = 1L;
        nonExistingId = 1000L;
        countTotalProducts = 25L;
    }

    @Test
    public void findAllPagedShouldReturnPageWhenPage0Size10() {
        PageRequest pageRequest = PageRequest.of(0, 10);

        Page<ProductDTO> dtoPage = productService.findAllPaged(pageRequest);

        Assertions.assertFalse(dtoPage.isEmpty());
        Assertions.assertEquals(0, dtoPage.getNumber());
        Assertions.assertEquals(10, dtoPage.getSize());
        Assertions.assertEquals(countTotalProducts, dtoPage.getTotalElements());
    }

    @Test
    public void findAllPagedShouldReturnEmptyWhenPageDoesNotExists() {
        PageRequest pageRequest = PageRequest.of(50, 10);

        Page<ProductDTO> dtoPage = productService.findAllPaged(pageRequest);

        Assertions.assertTrue(dtoPage.isEmpty());
    }

    @Test
    public void findAllShouldReturnSortedPageWhenSortByName() {
        PageRequest pageRequest = PageRequest.of(0, 10, Direction.ASC, "name");

        Page<ProductDTO> dtoPage = productService.findAllPaged(pageRequest);

        Assertions.assertFalse(dtoPage.isEmpty());
        Assertions.assertEquals("Macbook Pro", dtoPage.getContent().get(0).getName());
        Assertions.assertEquals("PC Gamer", dtoPage.getContent().get(1).getName());
        Assertions.assertEquals("PC Gamer Alfa", dtoPage.getContent().get(2).getName());
    }

    @Test
    public void deleteShouldDeleteResourceWhenIdExists() {

        productService.delete(existingId);

        Assertions.assertEquals(countTotalProducts - 1, productRepository.count());
    }

    @Test
    public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExists() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            productService.delete(nonExistingId);
        });
    }
}