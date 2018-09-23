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
	public List<Show> getShowsByCity(Long cityId) {
		return showRepository.getShowsByCity(cityId);
	}

	@Override
	public byte[] getImagesByCityId(Long cityId,String movieImage) {
		return showRepository.findImageByCityId(cityId,movieImage);
	}

	@Override
	public byte[] getAllMovieImageByCityId(Long cityId) {
		return showRepository.getAllMovieImageByCityId(cityId);
	}
	
}
