package com.devsuperior.uri2609;

import com.devsuperior.uri2609.dto.CategorySumDTO;
import com.devsuperior.uri2609.projections.CategorySumProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.devsuperior.uri2609.repositories.CategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class Uri2609Application implements CommandLineRunner {

	@Autowired
	private CategoryRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(Uri2609Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		List<CategorySumProjection> categorySQL = repository.searchSQL();
		List<CategorySumDTO> categoryDtoSql = categorySQL.stream().map(category -> new CategorySumDTO(category)).collect(Collectors.toList());

		System.out.println("Query SQL Raiz: ");
		categoryDtoSql.stream().forEach(category -> System.out.println(category));

		List<CategorySumDTO> categoryJPQL = repository.searchJPQL();

		System.out.println("\n Query JPQL: ");
		categoryJPQL.stream().forEach(category -> System.out.println(category));
	}
}
