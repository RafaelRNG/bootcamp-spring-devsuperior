package br.com.rng.dscatalog.resources;

import br.com.rng.dscatalog.services.exceptions.DatabaseException;
import br.com.rng.dscatalog.services.exceptions.ResourceNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import br.com.rng.dscatalog.dto.ProductDTO;
import br.com.rng.dscatalog.services.ProductService;
import br.com.rng.dscatalog.tests.Factory;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@WebMvcTest(value = ProductResource.class)
public class ProductResourceTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    private ProductDTO productDTO;
    private PageImpl<ProductDTO> page;
    private long existingId;
    private long nonExistingId;
    private long dependentId;

    @BeforeEach
    void setUp() throws Exception {
        productDTO = Factory.createProductDTO();
        page = new PageImpl(List.of(productDTO));

        existingId = 1L;
        nonExistingId = 2L;
        dependentId = 3L;

        //page products
        Mockito.when(productService.findAllPaged(ArgumentMatchers.any())).thenReturn(page);

        //findById Product
        Mockito.when(productService.findById(existingId)).thenReturn(productDTO);
        Mockito.when(productService.findById(nonExistingId)).thenThrow(ResourceNotFoundException.class);

        //update Product
        Mockito.when(productService.update(ArgumentMatchers.eq(existingId), ArgumentMatchers.any())).thenReturn(productDTO);
        Mockito.when(productService.update(ArgumentMatchers.eq(nonExistingId), ArgumentMatchers.any())).thenThrow(ResourceNotFoundException.class);

        //deleteById Product
        Mockito.doNothing().when(productService).delete(existingId);
        Mockito.doThrow(ResourceNotFoundException.class).when(productService).delete(nonExistingId);
        Mockito.doThrow(DatabaseException.class).when(productService).delete(dependentId);
    }

    @Test
    public void findAllShouldReturnPage() throws Exception {
        // action
        ResultActions resultActions = mockMvc
                .perform(MockMvcRequestBuilders.get("/products").accept(MediaType.APPLICATION_JSON));

        // Assertions
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void findByIdShouldReturnProductWhenIdExists() throws Exception {
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/products/{id}", existingId).accept(MediaType.APPLICATION_JSON));

        resultActions.andExpect(MockMvcResultMatchers.status().isOk());

        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.name").exists());
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.description").exists());
    }

    @Test
    public void findByIdShouldsReturnNotFoundWhenIdDoesNotExists() throws Exception {
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/products/{id}", nonExistingId).accept(MediaType.APPLICATION_JSON));

        resultActions.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void updateShouldReturnProductDtoWhenIdExists() throws Exception{

        String jsonBody = objectMapper.writeValueAsString(productDTO);

       ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.put("/products/{id}", existingId)
               .content(jsonBody)
               .contentType(MediaType.APPLICATION_JSON)
               .accept(MediaType.APPLICATION_JSON));

       resultActions.andExpect(MockMvcResultMatchers.status().isOk());
       resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    @Test
    public void updateShouldThrowResourceNotFoundExceptionWhenIdDoesNotExists() throws Exception{
        String jsonBody = objectMapper.writeValueAsString(productDTO);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.put("/products/{id}", nonExistingId)
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        resultActions.andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
