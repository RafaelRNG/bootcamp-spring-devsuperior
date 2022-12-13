package br.com.rng.dscatalog.services;

import br.com.rng.dscatalog.dto.ProductDTO;
import br.com.rng.dscatalog.entities.Product;
import br.com.rng.dscatalog.repositories.ProductRepository;
import br.com.rng.dscatalog.services.exceptions.DatabaseException;
import br.com.rng.dscatalog.services.exceptions.ResourceNotFoundException;
import br.com.rng.dscatalog.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(value = SpringExtension.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    private long existingId;
    private long nonExistingId;
    private long dependentId;
    private Product product;
    private PageImpl<Product> page;

    @BeforeEach
    void setUp() throws Exception {
        existingId = 1L;
        nonExistingId = 1000L;
        dependentId = 3L;
        product = Factory.createProduct();
        page = new PageImpl(List.of(product));

        Mockito.when(productRepository.findAll((Pageable) ArgumentMatchers.any())).thenReturn(page);

        Mockito.when(productRepository.save(ArgumentMatchers.any())).thenReturn(product);

        Mockito.when(productRepository.findById(existingId)).thenReturn(Optional.of(product));

        Mockito.when(productRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        Mockito.doNothing().when(productRepository).deleteById(existingId);

        Mockito.doThrow(EmptyResultDataAccessException.class).when(productRepository).deleteById(nonExistingId);

        Mockito.doThrow(DataIntegrityViolationException.class).when(productRepository).deleteById(dependentId);
    }

    @Test
    public void findAllPagedShouldReturnPage() {

        Pageable pageable = PageRequest.of(0, 10);

        Page<ProductDTO> result = productService.findAllPaged(pageable);

        Assertions.assertNotNull(result);
        Mockito.verify(productRepository, Mockito.times(1)).findAll(pageable);
    }

    @Test
    public void deleteShouldDoNothingWhenIdExists() {
        Assertions.assertDoesNotThrow(() -> {
            productService.delete(existingId);
        });

        Mockito.verify(productRepository, Mockito.times(1)).deleteById(existingId);
    }

    @Test
    public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExists() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            productService.delete(nonExistingId);
        });

        Mockito.verify(productRepository, Mockito.times(1)).deleteById(nonExistingId);
    }

    @Test
    public void deleteShouldThrowDatabaseExceptionWhenDependentId() {
        Assertions.assertThrows(DatabaseException.class, () -> {
            productService.delete(dependentId);
        });

        Mockito.verify(productRepository, Mockito.times(1)).deleteById(dependentId);
    }
}