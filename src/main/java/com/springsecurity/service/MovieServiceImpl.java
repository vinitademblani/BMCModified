package com.springsecurity.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springsecurity.bean.Movie;
import com.springsecurity.bean.Theatre;
import com.springsecurity.repository.Movierepository;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	Movierepository movieRepository;

	@Override
	public Optional<Movie> findMovieByCityIdAndMovieId(Long movieId, Long cityId) {
		return movieRepository.findByCityIdAndMovieId(cityId, movieId);
	}

	@Override
	public Optional<String> findMovieNameByMovieId(Long movieId, Long cityId) {
		return movieRepository.findMovieNameByMovieId(movieId, cityId);
	}

	@Override
	public List<Theatre> findAllTheatresByMovieId(Long movieId, Long cityId) {
		return movieRepository.findAllTheatresByMovieId(movieId, cityId);
	}
}
