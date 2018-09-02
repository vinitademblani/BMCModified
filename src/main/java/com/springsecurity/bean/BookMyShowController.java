package com.springsecurity.bean;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springsecurity.exception.MovieNotFoundException;
import com.springsecurity.repository.CityRepository;
import com.springsecurity.repository.Movierepository;


@RestController
public class BookMyShowController {
	
	@Autowired
	Movierepository movierepository;
	
	@Autowired
	CityRepository cityRepository;
	
	@GetMapping("/user")
	public Principal getPrincipal(Principal principal)
	{
		System.out.println(principal.getName());
		return principal;
	}
	//find all the cities.This method can be used to dynamically populate the date in the dropdown of cities
	@GetMapping("/cities")
	public ResponseEntity<List<City>> getAllCities()
	{
				List<City> cities=cityRepository.findAll();
				return new ResponseEntity<List<City>>(cities, HttpStatus.OK);
	}
	
	//find all the movies in specific city passing cityid.
	//This can be used when the user selects specific city from dropdown then  this method will populate all the movies in that city
	@GetMapping("/cities/{cityId}/movies/")
	public ResponseEntity<List<Movie>>  getAllMovies(@PathVariable("cityId") Long cityId)
	{
		List<Movie> moviesInCity=movierepository.findAllByCity(cityId)
				.orElseThrow(()->new MovieNotFoundException("No movies found with city id "+cityId));
	
			return new ResponseEntity<List<Movie>>(moviesInCity, HttpStatus.NOT_FOUND);
	}
	
	//getting the specific movie from the specific  city
	@GetMapping("/cities/{cityId}/movies/{movieId}")
	public ResponseEntity<Movie>  getMovieInCity(@PathVariable("cityId") Long cityId,@PathVariable("movieId") Long movieId)
	{
	Movie movieInCity=movierepository.findByCityIdAndMovieId(cityId, movieId).
				orElseThrow(()->new MovieNotFoundException("No movies found with city id "+cityId+" movie id is "+movieId));
	
			return new ResponseEntity<Movie>(movieInCity, HttpStatus.NOT_FOUND);
	}
	
	
}
