package com.devsuperior.uri2621;

import com.devsuperior.uri2621.dtos.ProductMinDto;
import com.devsuperior.uri2621.projections.ProductMinProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.devsuperior.uri2621.repositories.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class Uri2621Application implements CommandLineRunner {

	@Autowired
	private ProductRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(Uri2621Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		List<ProductMinProjection> products = repository.searchSQL(10, 20, "P");
		List<ProductMinDto> productsDtos = products.stream().map(product -> new ProductMinDto(product)).collect(Collectors.toList());

		System.out.println("SQL Raiz: ");
		productsDtos.stream().forEach(product -> System.out.println(product));

		List<ProductMinDto> productsMinDtos = repository.searchJPQL(10, 20, "P");

		System.out.println("Query com JPQL: ");
		productsMinDtos.stream().forEach(product -> System.out.println(product));
	}
}
