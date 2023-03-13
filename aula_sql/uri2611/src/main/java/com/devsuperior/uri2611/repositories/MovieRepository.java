package com.devsuperior.uri2611.repositories;

import com.devsuperior.uri2611.dtos.MovieMinDTO;
import com.devsuperior.uri2611.entities.Movie;
import com.devsuperior.uri2611.projections.MovieMinProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query(value = "SELECT movies.id, movies.name FROM movies INNER JOIN genres ON movies.id_genres = genres.id WHERE genres.description = :genreName", nativeQuery = true)
    List<MovieMinProjection> searchSQL(String genreName);

    @Query(value = "SELECT new com.devsuperior.uri2611.dtos.MovieMinDTO(obj.id, obj.name) FROM Movie obj WHERE obj.genre.description = :genreName")
    List<MovieMinDTO> searchJPQL(String genreName);
}