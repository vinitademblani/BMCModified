package com.springsecurity.config;


import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.springsecurity.bean.City;
import com.springsecurity.bean.Movie;
import com.springsecurity.bean.Show;
import com.springsecurity.bean.Theatre;
import com.springsecurity.repository.CityRepository;
import com.springsecurity.repository.Movierepository;
import com.springsecurity.repository.ShowRepository;
import com.springsecurity.repository.TheatreRepository;

@SpringBootApplication
@ComponentScan("com.springsecurity.*")
@EnableJpaRepositories("com.springsecurity.repository")
@EntityScan("com.springsecurity.*")
public class SpringSecurityUsingGoogleLoginApplication implements CommandLineRunner{

	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private Movierepository movieRepository;
	
	@Autowired
	private TheatreRepository theatreRepository;
	
	@Autowired
	private ShowRepository showRepository;

	
	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityUsingGoogleLoginApplication.class, args);
	}

	@Override
	@Transactional

	public void run(String... args) throws Exception {
		Set<Theatre> mumbaiTheatres=new HashSet<>();
		Theatre t1=new Theatre("Huma");
		Theatre t2=new Theatre("Viviana");
		Theatre t3=new Theatre("Eternity");
		
		mumbaiTheatres.add(t1);
		mumbaiTheatres.add(t2);
		mumbaiTheatres.add(t3);
		
		Set<Theatre> puneTheatres=new HashSet<>();
		Theatre t4=new Theatre("RCity");
		Theatre t5=new Theatre("RMall");
		Theatre t6=new Theatre("Raghullela");
		
		puneTheatres.add(t4);
		puneTheatres.add(t5);
		puneTheatres.add(t6);
		
		// Cities 
		City c1=new City("Mumbai");
		City c2=new City("pune");
		cityRepository.save(c1);
		cityRepository.save(c2);
		
		
		//Movies
		Set<Movie> moviesInCity1=new HashSet<>();
		
		Movie m1=new Movie("Soorma",2.8,c1,mumbaiTheatres,"soormaImg");
		Movie m2=new Movie("Chak De", 2.4, c1,mumbaiTheatres,"chakDeImg");
		Movie m3=new Movie("Dear Zindagi", 2.7, c1,mumbaiTheatres,"dearZindagiImg");
		moviesInCity1.add(m1);
		moviesInCity1.add(m2);
		moviesInCity1.add(m3);
		
		Set<Movie> moviesInCity2=new HashSet<>();
		Movie m4=new Movie("Darr", 2.5, c2,puneTheatres,"darrImg");
		Movie m5=new Movie("YJHD", 2.4, c2,puneTheatres,"yjhdImg");
		Movie m6=new Movie("Bang Bang", 2.7, c2,puneTheatres,"bangBangImg");
		
		moviesInCity2.add(m4);
		moviesInCity2.add(m5);
		moviesInCity2.add(m6);
		
		c1.setMovies(moviesInCity1);
		c2.setMovies(moviesInCity2);

		System.out.println("===============shows details=======================");
		Show s1=new Show(50,LocalTime.of(2, 30) , t1, m1,m1.getMovieName(),m1.getImageName());
		Show s2=new Show(100,LocalTime.of(3, 10) , t1, m1,m1.getMovieName(),m1.getImageName());
		Show s3=new Show(90,LocalTime.of(3, 10) , t1, m2,m2.getMovieName(),m2.getImageName());
		Show s4=new Show(50,LocalTime.of(10, 10) , t1, m3,m3.getMovieName(),m3.getImageName());
		Show s5=new Show(100,LocalTime.of(3, 50) , t2, m1,m1.getMovieName(),m1.getImageName());
		Show s6=new Show(10,LocalTime.of(23, 10) , t2, m3,m3.getMovieName(),m3.getImageName());
		Show s7=new Show(80,LocalTime.of(9, 10) , t3, m3,m3.getMovieName(),m3.getImageName());
		Show s8=new Show(60,LocalTime.of(6, 30) , t3, m2,m2.getMovieName(),m2.getImageName());
		

		System.out.println("Creating theatre Details");
		
		System.out.println("========================Getting all theatres================");
		
		//Theatre with many movies
		t1.setMovies(moviesInCity1);
		t2.setMovies(moviesInCity1);
		t3.setMovies(moviesInCity1);
		
		t4.setMovies(moviesInCity2);
		t5.setMovies(moviesInCity2);
		t6.setMovies(moviesInCity2);
		
		//newly added relationship
		t1.setCity(c1);
		t2.setCity(c1);
		t3.setCity(c1);
		
		t4.setCity(c2);
		t5.setCity(c2);
		t6.setCity(c2);
		
		Set<Show> showSetInHumaTheatreForSoormaMovie=new HashSet<>();
		showSetInHumaTheatreForSoormaMovie.add(s1);
		showSetInHumaTheatreForSoormaMovie.add(s2);
		
		//No need of calling cityrepository.save(c1) 
		//as we have written cascade.ALL in city so it will be saved automatically
		movieRepository.save(m1);
		movieRepository.save(m2);
		movieRepository.save(m3);
		movieRepository.save(m4);
		movieRepository.save(m5);
		movieRepository.save(m6);
		
		theatreRepository.save(t1);
		theatreRepository.save(t2);
		theatreRepository.save(t3);
		theatreRepository.save(t4);
		theatreRepository.save(t5);
		theatreRepository.save(t6);
		
		showRepository.save(s1);
		showRepository.save(s2);
		showRepository.save(s3);
		showRepository.save(s4);
		showRepository.save(s5);
		showRepository.save(s6);
		showRepository.save(s7);
		showRepository.save(s8);
		
		List<City> cityList=cityRepository.findAll();
		cityList.stream().forEach(c->System.out.println(c));
		
		List<Movie> movieList=movieRepository.findAll();
		movieList.stream().forEach(System.out::println);
		
		List<Theatre> theatreList=theatreRepository.findAll();
		theatreList.stream().forEach(System.out::println);
		
		List<Show> showList=showRepository.findAll();
		showList.stream().forEach(System.out::println);
		
	
		
		}
}	
	
	

