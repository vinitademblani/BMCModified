package com.springsecurity.service;

import java.util.List;
import java.util.Optional;

import com.springsecurity.bean.Movie;
import com.springsecurity.bean.Theatre;

public interface MovieService {

	Optional<Movie> findMovieByCityIdAndMovieId(Long cityId, Long movieId);

	Optional<String> findMovieNameByMovieId(Long movieId, Long cityId);

	List<Theatre> findAllTheatresByMovieId(Long cityId, Long movieId);

}
