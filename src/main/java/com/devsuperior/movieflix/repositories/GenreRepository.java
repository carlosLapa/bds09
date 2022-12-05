package com.devsuperior.movieflix.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.movieflix.entities.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {

	@Query("SELECT obj FROM Genre obj JOIN FETCH obj.movies")
	List<Genre> findGenresMovies();

}
