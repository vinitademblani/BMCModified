/*package com.springsecurity.config;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.springsecurity.bean.City;
import com.springsecurity.bean.Movie;
import com.springsecurity.bean.Theatre;
import com.springsecurity.repository.CityRepository;
import com.springsecurity.repository.Movierepository;
import com.springsecurity.repository.TheatreRepository;
@Component
//@EntityScan("com.springsecurity.*")
@Order(1)
public class TheatreMovieMapping implements CommandLineRunner {
	@Autowired
	private Movierepository movieRepository;
	@Autowired
	TheatreRepository theatreRepository;
	
	@Autowired
	CityRepository cityRepository;
	
	@Transactional
	@Override
	public void run(String... args) throws Exception {
		//cities
		City c3=new City("Delhi");
		City c4=new City("Chennai");
		
		//movies
		Movie m1=new Movie("Soorma",2.8,c3);
		Movie m2=new Movie("Chak De", 2.4, c3);
		Movie m3=new Movie("Dear Zindagi", 2.7, c3);
		
		//set of movies
		Set<Movie> moviesInCity3=new HashSet<>();
		moviesInCity3.add(m1);
		moviesInCity3.add(m2);
		moviesInCity3.add(m3);
		
		c3.setMovies(moviesInCity3);
		
		//Theatres
		
		
		//movie in many theatres
		m1.setTheatres(mumbaiTheatres);
		m2.setTheatres(mumbaiTheatres);
		m3.setTheatres(mumbaiTheatres);
		
				//Iterator<Movie> movieItr=SpringSecurityUsingGoogleLoginApplication.moviesInCity1.iterator();
				while(movieItr.hasNext())
				{
				movieItr.next().setTheatres(mumbaiTheatres);
				}
				cityRepository.save(c3);
				cityRepository.save(c4);
				movieRepository.save(m1);
				movieRepository.save(m2);
				movieRepository.save(m3);
				
				while(movieItr.hasNext())
				{
				movieRepository.save(movieItr.next());
				}
				theatreRepository.findAll().forEach(System.out::println);
				
				
		
	}

}
*/