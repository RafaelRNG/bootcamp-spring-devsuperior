package com.devsuperior.uri2602;

import com.devsuperior.uri2602.dtos.CustomerMinDTO;
import com.devsuperior.uri2602.projections.CustomerMinProjection;
import com.devsuperior.uri2602.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class Uri2602Application implements CommandLineRunner {

	@Autowired
	private CustomerRepository customerRepository;

	public static void main(String[] args) {
		SpringApplication.run(Uri2602Application.class, args);
	}

	public void run(String... args) throws Exception {

		List<CustomerMinProjection> list = customerRepository.searchSQL("rs");
		List<CustomerMinDTO> resultSQL =  list.stream().map(obj -> new CustomerMinDTO(obj)).collect(Collectors.toList());

		System.out.println("\n **Resultado SQL nativo**");
		for(CustomerMinDTO object: resultSQL) {
			System.out.println(object);
		}

		System.out.println("\n");

		List<CustomerMinDTO> resultJPQL = customerRepository.searchJPQL("rs");

		System.out.println("\n \n **Resultado SQL com JPQL**");
		for(CustomerMinDTO object: resultJPQL) {
			System.out.println(object);
		}
	}
}
