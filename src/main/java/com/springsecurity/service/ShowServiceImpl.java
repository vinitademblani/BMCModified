package com.springsecurity.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springsecurity.bean.Show;
import com.springsecurity.repository.ShowRepository;

@Service
public class ShowServiceImpl implements ShowService {

	@Autowired
	ShowRepository showRepository;

	@Override
	public Optional<List<Show>> findAllShowsOfMovieInTheatre(Long movieId, Long theatreId) {
		return showRepository.findAllShowsOfMovieInTheatre(movieId, theatreId);
	}

	@Override
	public List<Show> getMovieByCity(Long cityId) {
		return showRepository.getMovieByCity(cityId);
	}
}
