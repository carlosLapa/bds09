package com.devsuperior.movieflix.dto;

import java.io.Serializable;

import com.devsuperior.movieflix.entities.Movie;

public class MovieDTOWithGenre extends MovieDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private GenreDTO genre;

	public MovieDTOWithGenre() {
		super();
	}

	public MovieDTOWithGenre(Long id, String title, String subTitle, Integer year, String synopsis, String imgUrl,
			GenreDTO genre) {
		super(id, title, subTitle, synopsis, year, imgUrl);
		this.genre = new GenreDTO();
	}

	public MovieDTOWithGenre(Movie entity) {
		super(entity);
		this.genre = new GenreDTO(entity.getGenre());
	}

	public GenreDTO getGenre() {
		return genre;
	}

	public void setGenre(GenreDTO genre) {
		this.genre = genre;
	}

}
