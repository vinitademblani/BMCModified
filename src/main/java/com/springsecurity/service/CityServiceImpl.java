package com.springsecurity.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springsecurity.bean.City;
import com.springsecurity.bean.Movie;
import com.springsecurity.repository.CityRepository;

@Service
public class CityServiceImpl implements CityService {
	@Autowired
	CityRepository cityRepository;

	public List<City> getAllCities() {
		return cityRepository.findAll();
	}

	@Override
	public Optional<List<String>> findAllCityNames() {
		return cityRepository.findAllCityNames();
	}

	@Override
	public boolean existsById(Long cityId) {
		return cityRepository.existsById(cityId);
	}

	@Override
	public List<Movie> findAllMoviesByCityId(Long cityId) {
		return cityRepository.findAllMoviesByCityId(cityId);
	}

	@Override
	public Optional<City> findByCityId(Long cityId) {
		return cityRepository.findById(cityId);
	}
}
