package com.devsuperior.uri2611;

import com.devsuperior.uri2611.dtos.MovieMinDTO;
import com.devsuperior.uri2611.projections.MovieMinProjection;
import com.devsuperior.uri2611.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class Uri2611Application implements CommandLineRunner {

	@Autowired
	private MovieRepository movieRepository;

	public static void main(String[] args) {
		SpringApplication.run(Uri2611Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		//Native SQL
		System.out.println("\n native SQL");
		List<MovieMinProjection> searchSQL = movieRepository.searchSQL("Action");
		List<MovieMinDTO> movieListDTO = searchSQL.stream().map(obj -> new MovieMinDTO(obj)).collect(Collectors.toList());

		movieListDTO.stream().forEach(obj -> System.out.println(obj));

		System.out.println("\n \n");

		//JPQL
		System.out.println("\n Query JPQL");
		List<MovieMinDTO> searchJPQL = movieRepository.searchJPQL("Action");

		searchJPQL.stream().forEach(obj -> System.out.println(obj));
	}
}
