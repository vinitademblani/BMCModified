package com.springsecurity.bean;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springsecurity.exception.ErrorDetails;
import com.springsecurity.exception.ResourceNotFoundException;
import com.springsecurity.repository.CityRepository;
import com.springsecurity.repository.Movierepository;
import com.springsecurity.repository.ShowRepository;


@RestController
public class BookMyShowController {
	
	@Autowired
	Movierepository movieRepository;
	
	@Autowired
	CityRepository cityRepository;
	
	@Autowired
	ShowRepository showRepository;
	
	@GetMapping("/user")
	public Principal getPrincipal(Principal principal)
	{
		System.out.println(principal.getName());
		return principal;
	}
	//find all the cities.This method can be used to dynamically populate the date in the dropdown of cities
	@RequestMapping(value="/cities",produces=MediaType.APPLICATION_JSON_VALUE)
	
	public ResponseEntity<List<City>> getAllCities()
	{
				List<City> cities=cityRepository.findAll();
				return new ResponseEntity<List<City>>(cities, HttpStatus.OK);
	}
	
	@RequestMapping(value="/cities/cityNames",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<String>> getAllCityNames()
	{
				Optional<List<String>> cityNames=cityRepository.findAllCityNames();
				if(cityNames.isPresent())
				return new ResponseEntity<List<String>>(cityNames.get(), HttpStatus.OK);
				else
					return new ResponseEntity<List<String>>(new ArrayList<>(),HttpStatus.NO_CONTENT);
	}
	
	//find all the movies in specific city passing cityid.
	//This can be used when the user selects specific city from dropdown then  this method will populate all the movies in that city
	@GetMapping("/cities/{cityId}/movies/")
	public ResponseEntity<List<Movie>>  getAllMovies(@PathVariable("cityId") Long cityId)
	{
	
		if(!cityRepository.existsById(cityId))
			throw new ResourceNotFoundException("No city found with cityId as "+cityId);
		
		List<Movie> moviesInCity=cityRepository.findAllMoviesByCityId(cityId);
		if(moviesInCity.isEmpty())
			throw new ResourceNotFoundException("No movie found in the city with city id as "+cityId);
		else
			return new ResponseEntity<List<Movie>>(moviesInCity, HttpStatus.NOT_FOUND);
		
	}
	
	//getting the specific movie from the specific  city
	@GetMapping("/cities/{cityId}/movies/{movieId}")
	public ResponseEntity<Movie>  getMovieInCity(@PathVariable("cityId") Long cityId,@PathVariable("movieId") Long movieId)
	{
		if(!cityRepository.existsById(cityId))
			throw new ResourceNotFoundException("No city found with cityId as "+cityId);
			
			Optional<Movie> movieInCity=movieRepository.findMovieByCityIdAndMovieId(movieId, cityId);
					
			if(!movieInCity.isPresent())
				throw new ResourceNotFoundException("No movie found with  cityId as "+cityId+" movieId as "+movieId);
			else
			return new ResponseEntity<Movie>(movieInCity.get(), HttpStatus.OK);
	}
	@GetMapping("/cities/{cityId}/movies/{movieId}/movieName")
	public ResponseEntity<String>  getMovieNameByMovieId(@PathVariable("cityId") Long cityId,@PathVariable("movieId") Long movieId)
	{
		if(!cityRepository.existsById(cityId))
			throw new ResourceNotFoundException("No city found with cityId as "+cityId);
			
			Optional<String> movieNameInCity=movieRepository.findMovieNameByMovieId(movieId,cityId);
					
			if(!movieNameInCity.isPresent())
				throw new ResourceNotFoundException("No movie found with  cityId as "+cityId+" movieId as "+movieId);
			else
			return new ResponseEntity<String>(movieNameInCity.get(), HttpStatus.OK);
	}
	
	@GetMapping("/cities/{cityId}/movies/{movieId}/theatres")
	public ResponseEntity<List<Theatre>>  getMovieNamByMovieId(@PathVariable("cityId") Long cityId ,@PathVariable("movieId") Long movieId)
	{
		if(!cityRepository.existsById(cityId))
			throw new ResourceNotFoundException("No city found with cityId as "+cityId);
			
			Optional<Movie> movieInCity=movieRepository.findMovieByCityIdAndMovieId(movieId, cityId);
					
			if(!movieInCity.isPresent())
				throw new ResourceNotFoundException("No movie found with  cityId as "+cityId+" movieId as "+movieId);
			
				List<Theatre> allTheatresOfMovie=movieRepository.findAllTheatresByMovieId(movieId, cityId);
				if(allTheatresOfMovie.isEmpty())
					throw new ResourceNotFoundException("No theatres found with  cityId as "+cityId+" with movieId as "+movieId);
				
				return new ResponseEntity<List<Theatre>>(allTheatresOfMovie,HttpStatus.OK);
	}
	
	@GetMapping("/cities/{cityId}/movies/{movieId}/theatres/{theatreId}/shows")
	public ResponseEntity<List<Show>>  getAllShowsOfMovieInTheatre(@PathVariable("cityId") Long cityId ,@PathVariable("movieId") Long movieId,@PathVariable("theatreId") Long theatreId)
	{
		if(!cityRepository.existsById(cityId))
			throw new ResourceNotFoundException("No city found with cityId as "+cityId);
			
			Optional<Movie> movieInCity=movieRepository.findMovieByCityIdAndMovieId(movieId, cityId);
					
			if(!movieInCity.isPresent())
				throw new ResourceNotFoundException("No movie found with  cityId as "+cityId+" movieId as "+movieId);
			
				List<Theatre> allTheatresOfMovie=movieRepository.findAllTheatresByMovieId(movieId, cityId);
				if(allTheatresOfMovie.isEmpty())
					throw new ResourceNotFoundException("No theatres found with  cityId as "+cityId+" with movieId as "+movieId);
				
				List<Show> showsOfMovie=showRepository.findAllShowsOfMovieInTheatre(movieId,theatreId);
				/*if(!showsOfMovie.isPresent())
					throw new ResourceNotFoundException("No shows found with  of movie with movie id as  as "+movieId+" in theatre with theatre id  as "+theatreId);*/
			
				return new ResponseEntity<List<Show>>(showsOfMovie,HttpStatus.OK);
	}
}
