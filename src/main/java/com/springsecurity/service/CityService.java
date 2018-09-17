package com.springsecurity.service;

import java.util.List;
import java.util.Optional;

import com.springsecurity.bean.City;
import com.springsecurity.bean.Movie;

public interface CityService {
	public List<City> getAllCities();

	public Optional<List<String>> findAllCityNames();

	public boolean existsById(Long cityId);

	public List<Movie> findAllMoviesByCityId(Long cityId);
	
	public Optional<City> findByCityId(Long cityId);
}
