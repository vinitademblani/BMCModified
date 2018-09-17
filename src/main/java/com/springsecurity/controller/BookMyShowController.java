package com.springsecurity.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.springsecurity.bean.City;
import com.springsecurity.bean.Movie;
import com.springsecurity.bean.Show;
import com.springsecurity.bean.Theatre;
import com.springsecurity.exception.ResourceNotFoundException;
import com.springsecurity.service.CityService;
import com.springsecurity.service.MovieService;
import com.springsecurity.service.ShowService;


@RestController
public class BookMyShowController {
	
	
	@Autowired
	CityService cityService;
	
	@Autowired
	MovieService movieService;
	
	@Autowired
	ShowService showService;
	
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
				List<City> cities=cityService.getAllCities();
				return new ResponseEntity<List<City>>(cities, HttpStatus.OK);
	}
	@GetMapping("/cities/{cityId}")
	public ResponseEntity<City> getCityById(@PathVariable("cityId") long cityId)
	{
				Optional<City> city=cityService.findByCityId(cityId);
				if(city.isPresent())
					return new ResponseEntity<City>(city.get(), HttpStatus.OK);
				else 
					return new ResponseEntity<City>(new City(),HttpStatus.NO_CONTENT);
	}
	@GetMapping(value="/cities/cityNames",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<String>> getAllCityNames()
	{
				Optional<List<String>> cityNames=cityService.findAllCityNames();
				if(cityNames.isPresent())
					return new ResponseEntity<List<String>>(cityNames.get(), HttpStatus.OK);
				else
					return new ResponseEntity<List<String>>(new ArrayList<>(),HttpStatus.NO_CONTENT);
	}
	
	//find all the movies in specific city passing cityid.
	//This can be used when the user selects specific city from dropdown then  this method will populate all the movies in that city
	@GetMapping("/cities/{cityId}/movies")
	public ResponseEntity<List<Movie>>  getAllMovies(@PathVariable("cityId") Long cityId)
	{
	
		if(!cityService.existsById(cityId))
			throw new ResourceNotFoundException("No city found with cityId as "+cityId);
		
		List<Movie> moviesInCity=cityService.findAllMoviesByCityId(cityId);
		if(moviesInCity.isEmpty())
			throw new ResourceNotFoundException("No movie found in the city with city id as "+cityId);
		else
			return new ResponseEntity<List<Movie>>(moviesInCity, HttpStatus.NOT_FOUND);
		
	}
	
	//getting the specific movie from the specific  city
	@GetMapping("/cities/{cityId}/movies/{movieId}")
	public ResponseEntity<Movie>  getMovieInCity(@PathVariable("cityId") Long cityId,@PathVariable("movieId") Long movieId)
	{
		if(!cityService.existsById(cityId))
			throw new ResourceNotFoundException("No city found with cityId as "+cityId);
			
			Optional<Movie> movieInCity=movieService.findMovieByCityIdAndMovieId(movieId, cityId);
					
			if(!movieInCity.isPresent())
				throw new ResourceNotFoundException("No movie found with  cityId as "+cityId+" movieId as "+movieId);
			else
			return new ResponseEntity<Movie>(movieInCity.get(), HttpStatus.OK);
	}
	
	@GetMapping("/cities/{cityId}/movies/{movieId}/movieName")
	public ResponseEntity<String>  getMovieNameByMovieId(@PathVariable("cityId") Long cityId,@PathVariable("movieId") Long movieId)
	{
		if(!cityService.existsById(cityId))
			throw new ResourceNotFoundException("No city found with cityId as "+cityId);
			
			Optional<String> movieNameInCity=movieService.findMovieNameByMovieId(movieId,cityId);
					
			if(!movieNameInCity.isPresent())
				throw new ResourceNotFoundException("No movie found with  cityId as "+cityId+" movieId as "+movieId);
			else
			return new ResponseEntity<String>(movieNameInCity.get(), HttpStatus.OK);
	}
	
	@GetMapping("/cities/{cityId}/movies/{movieId}/theatres")
	public ResponseEntity<List<Theatre>>  getAllTheatresByMovieId(@PathVariable("cityId") Long cityId ,@PathVariable("movieId") Long movieId)
	{
		if(!cityService.existsById(cityId))
			throw new ResourceNotFoundException("No city found with cityId as "+cityId);
			
			Optional<Movie> movieInCity=movieService.findMovieByCityIdAndMovieId(cityId, movieId);
					
			if(!movieInCity.isPresent())
				throw new ResourceNotFoundException("No movie found with  cityId as "+cityId+" movieId as "+movieId);
			
				List<Theatre> allTheatresOfMovie=movieService.findAllTheatresByMovieId(cityId,movieId );
				if(allTheatresOfMovie.isEmpty())
					throw new ResourceNotFoundException("No theatres found with  cityId as "+cityId+" with movieId as "+movieId);
				
				return new ResponseEntity<List<Theatre>>(allTheatresOfMovie,HttpStatus.OK);
	}
	
	@GetMapping("/cities/{cityId}/movies/{movieId}/theatres/{theatreId}/shows")
	public ResponseEntity<List<Show>>  getAllShowsOfMovieInTheatre(@PathVariable("cityId") Long cityId ,@PathVariable("movieId") Long movieId,@PathVariable("theatreId") Long theatreId)
	{
		if(!cityService.existsById(cityId))
			throw new ResourceNotFoundException("No city found with cityId as "+cityId);
			
			Optional<Movie> movieInCity=movieService.findMovieByCityIdAndMovieId(cityId, movieId);
					
			if(!movieInCity.isPresent())
				throw new ResourceNotFoundException("No movie found with  cityId as "+cityId+" movieId as "+movieId);
			
				List<Theatre> allTheatresOfMovie=movieService.findAllTheatresByMovieId(cityId, movieId);
				if(allTheatresOfMovie.isEmpty())
					throw new ResourceNotFoundException("No theatres found with  cityId as "+cityId+" with movieId as "+movieId);
				
				Optional<List<Show>> showsOfMovie=showService.findAllShowsOfMovieInTheatre(movieId,theatreId);
				if(!showsOfMovie.isPresent())
					throw new ResourceNotFoundException("No shows found with  of movie with movie id as  as "+movieId+" in theatre with theatre id  as "+theatreId);
			
				return new ResponseEntity<List<Show>>(showsOfMovie.get(),HttpStatus.OK);
	}
	
	@GetMapping("/cities/{cityId}/shows")
	public ResponseEntity<List<Show>> getShowsByCity(@PathVariable("cityId") Long cityId)
	{
		if(!cityService.existsById(cityId))
			throw new ResourceNotFoundException("No city found with cityId as "+cityId);
			
			List<Show> shows=showService.getShowsByCity(cityId);
			
			return new ResponseEntity<List<Show>>(shows, HttpStatus.OK);
	}
	
	
}
