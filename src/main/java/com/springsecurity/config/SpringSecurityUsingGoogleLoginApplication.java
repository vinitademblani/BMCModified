package com.springsecurity.config;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import org.springframework.core.io.ClassPathResource;
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

	
	public static void main(String[] args)  {
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
		
		Movie m1=new Movie("Soorma",2.8,c1,mumbaiTheatres,"SoormaImg");
		m1.setImage(getMovieImagePic(m1.getImageName()));
		Movie m2=new Movie("Chak De", 2.4, c1,mumbaiTheatres,"ChakDeImg");
		m2.setImage(getMovieImagePic(m2.getImageName()));
		Movie m3=new Movie("Dear Zindagi", 2.7, c1,mumbaiTheatres,"DearZindagiImg");
		m3.setImage(getMovieImagePic(m3.getImageName()));
		moviesInCity1.add(m1);
		moviesInCity1.add(m2);
		moviesInCity1.add(m3);
		
		Set<Movie> moviesInCity2=new HashSet<>();
		Movie m4=new Movie("stree", 2.5, c2,puneTheatres,"streeImg");
		m4.setImage(getMovieImagePic(m4.getImageName()));
		Movie m5=new Movie("YJHD", 2.4, c2,puneTheatres,"yjhdImg");
		m5.setImage(getMovieImagePic(m5.getImageName()));
		Movie m6=new Movie("Bang Bang", 2.7, c2,puneTheatres,"bangbangImg");
		m6.setImage(getMovieImagePic(m6.getImageName()));
		
		moviesInCity2.add(m4);
		moviesInCity2.add(m5);
		moviesInCity2.add(m6);
		
		
		c2.setMovies(moviesInCity2);

		System.out.println("===============shows details=======================");
		Show s1=new Show(50,LocalTime.of(2, 30) , t1, m1,m1.getMovieName(),m1.getImageName(),m1.getImage());
		Show s2=new Show(100,LocalTime.of(3, 10) , t1, m1,m1.getMovieName(),m1.getImageName(),m1.getImage());
		Show s3=new Show(90,LocalTime.of(3, 10) , t4, m4,m4.getMovieName(),m4.getImageName(),m4.getImage());
		Show s4=new Show(50,LocalTime.of(10, 10) , t2, m3,m3.getMovieName(),m3.getImageName(),m3.getImage());
		Show s5=new Show(100,LocalTime.of(3, 50) , t5, m5,m5.getMovieName(),m5.getImageName(),m5.getImage());
		Show s6=new Show(10,LocalTime.of(23, 10) , t3, m3,m3.getMovieName(),m3.getImageName(),m3.getImage());
		Show s7=new Show(80,LocalTime.of(9, 10) , t6, m6,m6.getMovieName(),m6.getImageName(),m6.getImage());
		Show s8=new Show(60,LocalTime.of(6, 30) , t3, m3,m3.getMovieName(),m3.getImageName(),m3.getImage());
		

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
	
	public static byte[] getMovieImagePic(String imageName) throws IOException
	{
		System.out.println("Image passed "+imageName);
		File f=new ClassPathResource("images/"+imageName+".jpg").getFile();
		
		//Path path=Paths.get("classpath:/images/"+imageName+".jpg");
		byte[] data=null;
		try {
			data=Files.readAllBytes(f.toPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return data;
	}
}
	
	

