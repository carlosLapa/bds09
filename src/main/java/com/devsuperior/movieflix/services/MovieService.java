package com.devsuperior.movieflix.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.dto.MovieDTOWithGenre;
import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.repositories.GenreRepository;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;

@Service
public class MovieService {

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private GenreRepository genreRepository;

	@Autowired
	private ReviewRepository reviewRepository;

	@Transactional(readOnly = true)
	public MovieDTO findById(Long id) {
		Optional<Movie> obj = movieRepository.findById(id);
		Movie entity = obj.orElseThrow(() -> new ResourceNotFoundException("Movie does not exist"));
		Genre genre = genreRepository.getOne(entity.getGenre().getId());
		entity.setGenre(genre);

		return new MovieDTOWithGenre(entity);
	}

	@Transactional(readOnly = true)
	public Page<MovieDTO> findByGenre(Long genreId, Pageable pageable) {
		try {
			Genre genre = (genreId == 0) ? null : genreRepository.getOne(genreId);
			Page<Movie> page = movieRepository.findByGenre(genre, pageable);
			movieRepository.findMoviesWithgenres(page.getContent());
			return page.map(m -> new MovieDTO(m));
		} catch (ResourceNotFoundException e) {
			throw new ResourceNotFoundException("Genre id not found!");
		}

	}

	@Transactional(readOnly = true)
	public List<ReviewDTO> findByReviews(Long movieId) {
		try {
			List<Review> list = reviewRepository.findReviews(movieId);
			return list.stream().map(r -> new ReviewDTO(r)).collect(Collectors.toList());
		} catch (ResourceNotFoundException e) {
			throw new ResourceNotFoundException("Movie id not found!");
		}
	}

}
