package com.springsecurity.service;

import java.util.List;
import java.util.Optional;

import com.springsecurity.bean.Show;

public interface ShowService {

	Optional<List<Show>> findAllShowsOfMovieInTheatre(Long movieId, Long theatreId);
	List<Show> getShowsByCity(Long cityId);
	byte[] getImagesByCityId(Long cityId,String movieImage);
	byte[] getAllMovieImageByCityId(Long cityId);

}
