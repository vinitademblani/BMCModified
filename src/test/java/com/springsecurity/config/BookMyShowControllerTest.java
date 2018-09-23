package com.springsecurity.config;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springsecurity.bean.City;
import com.springsecurity.bean.Movie;
import com.springsecurity.bean.Show;
import com.springsecurity.bean.Theatre;
import com.springsecurity.controller.BookMyShowController;
import com.springsecurity.service.CityService;
import com.springsecurity.service.MovieService;
import com.springsecurity.service.ShowService;

@RunWith(SpringRunner.class)
@WebMvcTest(value=BookMyShowController.class,secure = false)
public class BookMyShowControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CityService cityService;
	
	@MockBean
	private MovieService movieService;
	
	@MockBean
	private ShowService showService;
	
	@Test
	public void testgetAllCities() throws Exception {
		
		City c1=new City();
		c1.setCityId(1L);
		c1.setCityName("Mumbai");
		
		City c2=new City();
		c2.setCityId(2L);
		c2.setCityName("pune");
		
		List<City> expected=new ArrayList<>();
		expected.add(c1);
		expected.add(c2);
		
		//String inputInJson = this.mapToJson(expected);
		
		String URI = "/cities";
		
		Mockito.when(cityService.getAllCities()).thenReturn(expected);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get(URI)
				.accept(MediaType.APPLICATION_JSON);
				

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		//MockHttpServletResponse response=result.getResponse();
		String expectedJson = this.mapToJson(expected);
		
		String outputInJson = result.getResponse().getContentAsString();
		assertThat(expectedJson).isEqualTo(outputInJson);

	}
	@Test
	public void testgetAllCityNames() throws Exception
	{
		List<String> expectedCityNames=new ArrayList<>();
		City c1=new City();
		c1.setCityId(1L);
		c1.setCityName("Mumbai");
		
		City c2=new City();
		c2.setCityId(2L);
		c2.setCityName("pune");
		expectedCityNames.add(c1.getCityName());
		expectedCityNames.add(c2.getCityName());
		Optional<List<String>> optCityNames=Optional.of(expectedCityNames);
		when(cityService.findAllCityNames()).thenReturn(optCityNames);
		
		String expectedCityNamesInJson=this.mapToJson(expectedCityNames);
		
		String URl="/cities/cityNames";
		
		RequestBuilder builder=
				MockMvcRequestBuilders
				.get(URl)
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result=mockMvc.perform(builder).andReturn();
		String actualCityNamesJson=result.getResponse().getContentAsString();
		assertThat(expectedCityNamesInJson).isEqualTo(actualCityNamesJson);
		
	}
	
	@Test
	public void testgetAllMovies() throws Exception
	{
		City c1=new City();
		c1.setCityId(1L);
		c1.setCityName("Mumbai");
		
		Set<Theatre> mumbaiTheatres=new HashSet<>();
		Theatre t1=new Theatre("Huma");
		Theatre t2=new Theatre("Viviana");
		Theatre t3=new Theatre("Eternity");
		
		mumbaiTheatres.add(t1);
		mumbaiTheatres.add(t2);
		mumbaiTheatres.add(t3);
		List<Movie> moviesInCityExpected=new ArrayList<>();
		Movie m1=new Movie("Soorma",2.8,c1,mumbaiTheatres,"soormaImg");
		Movie m2=new Movie("Chak De", 2.4, c1,mumbaiTheatres,"chakDeImg");
		Movie m3=new Movie("Dear Zindagi", 2.7, c1,mumbaiTheatres,"dearZindagiImg");
		moviesInCityExpected.add(m1);
		moviesInCityExpected.add(m2);
		moviesInCityExpected.add(m3);
		Set<Movie> movies=new HashSet<>(moviesInCityExpected);
		c1.setMovies(movies);
		//List<Movie> expectedMovies=new ArrayList<>(moviesInCity1);
		when(cityService.existsById(1L)).thenReturn(true);
		when(cityService.findAllMoviesByCityId(1L)).thenReturn(moviesInCityExpected);
		System.out.println(moviesInCityExpected+"Expected");
		String expectedMoviesInJson=this.mapToJson(moviesInCityExpected);
		
		String URI="/cities/{cityId}/movies";
		RequestBuilder builder=MockMvcRequestBuilders
				.get(URI,1L)
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result=mockMvc.perform(builder).andReturn();
		String actualMoviesInJson=result.getResponse().getContentAsString();
		assertThat(expectedMoviesInJson).isEqualTo(actualMoviesInJson);
	}
	@Test
	public void testFindByCityId() throws Exception
	{
		City c1=new City();
		c1.setCityId(1L);
		c1.setCityName("Mumbai");
		Optional<City> optionalCity=Optional.of(c1);
		
		when(cityService.findByCityId(1L)).thenReturn(optionalCity);
		String expectedCityInJson=this.mapToJson(optionalCity.get());
		System.out.println(optionalCity.get()+" 121212");
		String URI="/cities/{cityId}";
		RequestBuilder builder=MockMvcRequestBuilders
				.get(URI,1L)
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result=mockMvc.perform(builder).andReturn();
		String actualCityInJson=result.getResponse().getContentAsString();
		System.out.println(actualCityInJson+"1313");
		assertThat(expectedCityInJson).isEqualTo(actualCityInJson);
		
	}
	
	@Test
	public void testfindMovieByCityIdAndMovieId() throws Exception
	{
		City c1=new City();
		c1.setCityId(1L);
		c1.setCityName("Mumbai");
		
		Set<Theatre> mumbaiTheatres=new HashSet<>();
		Theatre t1=new Theatre("Huma");
		Theatre t2=new Theatre("Viviana");
		Theatre t3=new Theatre("Eternity");
		
		mumbaiTheatres.add(t1);
		mumbaiTheatres.add(t2);
		mumbaiTheatres.add(t3);
		
		Movie m1=new Movie("Soorma",2.8,c1,mumbaiTheatres,"soormaImg");
		m1.setMovieId(1L);
		Optional<Movie> movieExpected=Optional.of(m1);
		
		when(cityService.existsById(1L)).thenReturn(true);
		when(movieService.findMovieByCityIdAndMovieId(1L, 1L)).thenReturn(movieExpected);
		
		String expectedMovieInJson=this.mapToJson(movieExpected.get());
	
		String URI="/cities/{cityId}/movies/{movieId}";
		
		RequestBuilder builder=MockMvcRequestBuilders
				.get(URI,new Object[]{1L,1L})
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result=mockMvc.perform(builder).andReturn();
		String actualMovieInJson=result.getResponse().getContentAsString();
		assertThat(expectedMovieInJson).isEqualTo(actualMovieInJson);
	
	
	}
	@Test
	public void testgetMovieNameByMovieId() throws Exception
	{
		City c1=new City();
		c1.setCityId(1L);
		c1.setCityName("Mumbai");
		
		Set<Theatre> mumbaiTheatres=new HashSet<>();
		Theatre t1=new Theatre("Huma");
		Theatre t2=new Theatre("Viviana");
		Theatre t3=new Theatre("Eternity");
		
		mumbaiTheatres.add(t1);
		mumbaiTheatres.add(t2);
		mumbaiTheatres.add(t3);
		
		Movie m1=new Movie("Soorma",2.8,c1,mumbaiTheatres,"soormaImg");
		m1.setMovieId(1L);
		Optional<String> movieNameExpected=Optional.of(m1.getMovieName());
		
		System.out.println(m1.getMovieName()+"Movie Name");
		System.out.println(movieNameExpected.get()+"11111");
		when(cityService.existsById(1L)).thenReturn(true);
		when(movieService.findMovieNameByMovieId(1L, 1L)).thenReturn(movieNameExpected);
		
		//String expectedMovieNameInJson=this.mapToJson(movieNameExpected.get());
		//System.out.println(expectedMovieNameInJson+"@@@@@@@@@@@");
		String URI="/cities/{cityId}/movies/{movieId}/movieName";
		
		RequestBuilder builder=MockMvcRequestBuilders
				.get(URI,new Object[]{1L,1L})
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result=mockMvc.perform(builder).andReturn();
		String actualMovieNameInJson=result.getResponse().getContentAsString();
		assertThat(movieNameExpected.get()).isEqualTo(actualMovieNameInJson);
	}
	
	@Test
	public void testgetAllTheatresByMovieId() throws Exception
	{
		
		City c1=new City();
		c1.setCityId(1L);
		c1.setCityName("Mumbai");
		
		Set<Theatre> mumbaiTheatres=new HashSet<>();
		Theatre t1=new Theatre("Huma");
		t1.setTheatreId(1L);
		Theatre t2=new Theatre("Viviana");
		t2.setTheatreId(2L);
		Theatre t3=new Theatre("Eternity");
		t3.setTheatreId(3L);
		
		mumbaiTheatres.add(t1);
		mumbaiTheatres.add(t2);
		mumbaiTheatres.add(t3);
		
		Movie m2=new Movie("Chak De", 2.4, c1,mumbaiTheatres,"chakDeImg");
		m2.setMovieId(2L);
		Optional<Movie> optionalMovie=Optional.of(m2);
		
		List<Theatre> expectedTheatres=new ArrayList<>(mumbaiTheatres);
		
		when(cityService.existsById(1L)).thenReturn(true);
		when(movieService.findMovieByCityIdAndMovieId(1L, 2L)).thenReturn(optionalMovie);
		
		when(movieService.findAllTheatresByMovieId(1L, 2L)).thenReturn(expectedTheatres);
		String expectedTheatresInJson=this.mapToJson(expectedTheatres);
		String URI="/cities/{cityId}/movies/{movieId}/theatres";
		RequestBuilder builder=MockMvcRequestBuilders
				.get(URI,new Object[]{1L,2L})
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result=mockMvc.perform(builder).andReturn();
		String actualTheatresInJson=result.getResponse().getContentAsString();
		assertThat(expectedTheatresInJson).isEqualTo(actualTheatresInJson);
	}
	@Test
	public void testgetAllShowsOfMovieInTheatre() throws Exception
	{
		
		City c1=new City();
		c1.setCityId(1L);
		c1.setCityName("Mumbai");
		
		Set<Theatre> mumbaiTheatres=new HashSet<>();
		Theatre t1=new Theatre("Huma");
		t1.setTheatreId(1L);
		Theatre t2=new Theatre("Viviana");
		t2.setTheatreId(2L);
		Theatre t3=new Theatre("Eternity");
		t3.setTheatreId(3L);
		
		mumbaiTheatres.add(t1);
		mumbaiTheatres.add(t2);
		mumbaiTheatres.add(t3);
		Movie m2=new Movie("Chak De", 2.4, c1,mumbaiTheatres,"chakDeImg");
		m2.setMovieId(2L);
		Optional<Movie> optionalMovie=Optional.of(m2);
		List<Theatre> theatres=new ArrayList<>(mumbaiTheatres);
		Show s1=new Show(90,LocalTime.of(7, 50) , t1, m2,m2.getMovieName(),m2.getImageName(),m2.getImage());
		
		s1.setShowId(1L);
		
		Show s2=new Show(100,LocalTime.of(3, 10) , t1, m2,m2.getMovieName(),m2.getImageName(),m2.getImage());
		s2.setShowId(2L);
		
		List<Show> showsList=new ArrayList<>();
		showsList.add(s1);
		showsList.add(s2);
		Optional<List<Show>> optionalShowsList=Optional.of(showsList);
		String expectedShowsListInJson=this.mapToJson(optionalShowsList.get());
		when(cityService.existsById(1L)).thenReturn(true);
		when(movieService.findMovieByCityIdAndMovieId(1L, 2L)).thenReturn(optionalMovie);
		when(movieService.findAllTheatresByMovieId(1L, 2L)).thenReturn(theatres);
		
		when(showService.findAllShowsOfMovieInTheatre(2L, 1L)).thenReturn(optionalShowsList);
		
		String URI="/cities/{cityId}/movies/{movieId}/theatres/{theatreId}/shows";
		
		RequestBuilder builder=MockMvcRequestBuilders
				.get(URI,new Object[]{1L,2L,1L})
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result=mockMvc.perform(builder).andReturn();
		
		String actualShows=result.getResponse().getContentAsString();
		assertThat(expectedShowsListInJson).isEqualTo(actualShows);
	}
	/*@Test(expected=ResourceNotFoundException.class)
	public void testFailure() throws Exception
	{
		when(cityService.findByCityId(3L).isPresent())
		.thenThrow(new ResourceNotFoundException("No City found with specified id"));
		MvcResult result=mockMvc.perform(get("/cities/{cityId}",3L)).andReturn();
		String expectedJson=this.mapToJson(cityService.findByCityId(3L));
		String actualJson=result.getResponse().toString();
		assertThat(expectedJson).isEqualTo(actualJson);

	}*/
	@Test
	public void testgetShowsByCity() throws Exception
	{
		City c1=new City();
		c1.setCityId(1L);
		c1.setCityName("Mumbai");
		
		Set<Theatre> mumbaiTheatres=new HashSet<>();
		Theatre t1=new Theatre("Huma");
		t1.setTheatreId(1L);
		t1.setCity(c1);
		Theatre t2=new Theatre("Viviana");
		t2.setTheatreId(2L);
		t2.setCity(c1);
		Theatre t3=new Theatre("Eternity");
		t3.setTheatreId(3L);
		t3.setCity(c1);
		
		mumbaiTheatres.add(t1);
		mumbaiTheatres.add(t2);
		mumbaiTheatres.add(t3);
		
		Movie m1=new Movie("Soorma",2.8,c1,mumbaiTheatres,"soormaImg");
		Movie m2=new Movie("Chak De", 2.4, c1,mumbaiTheatres,"chakDeImg");
		Movie m3=new Movie("Dear Zindagi", 2.7, c1,mumbaiTheatres,"dearZindagiImg");
		Set<Movie> moviesInCity1=new HashSet<>();
		moviesInCity1.add(m1);
		moviesInCity1.add(m2);
		moviesInCity1.add(m3);
		
		c1.setMovies(moviesInCity1);
		Show s1=new Show(50,LocalTime.of(2, 30) , t1, m1,m1.getMovieName(),m1.getImageName(),m1.getImage());
		Show s2=new Show(100,LocalTime.of(3, 10) , t1, m1,m1.getMovieName(),m1.getImageName(),m1.getImage());
		Show s3=new Show(90,LocalTime.of(3, 10) , t1, m2,m2.getMovieName(),m2.getImageName(),m2.getImage());
		Show s4=new Show(50,LocalTime.of(10, 10) , t1, m3,m3.getMovieName(),m3.getImageName(),m3.getImage());
		Show s5=new Show(100,LocalTime.of(3, 50) , t2, m1,m1.getMovieName(),m1.getImageName(),m1.getImage());
		Show s6=new Show(10,LocalTime.of(23, 10) , t2, m3,m3.getMovieName(),m3.getImageName(),m3.getImage());
		Show s7=new Show(80,LocalTime.of(9, 10) , t3, m3,m3.getMovieName(),m3.getImageName(),m3.getImage());
		Show s8=new Show(60,LocalTime.of(6, 30) , t3, m2,m2.getMovieName(),m2.getImageName(),m2.getImage());
		List<Show> showsList=new ArrayList<>();
		showsList.add(s1);
		showsList.add(s2);
		showsList.add(s3);
		showsList.add(s4);
		showsList.add(s5);
		showsList.add(s6);
		showsList.add(s7);
		showsList.add(s8);
		
		when(cityService.existsById(1L)).thenReturn(true);
		when(showService.getShowsByCity(1L)).thenReturn(showsList);
		
		String showsListInJson=this.mapToJson(showsList);
		String URI="/cities/{cityId}/shows";
		
		RequestBuilder builder=MockMvcRequestBuilders
				.get(URI,new Object[]{1L})
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result=mockMvc.perform(builder).andReturn();
		
		String actualShows=result.getResponse().getContentAsString();
		assertThat(showsListInJson).isEqualTo(actualShows);
		
	}
	
	/**
	 * Maps an Object into a JSON String. Uses a Jackson ObjectMapper.
	 */
	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}
}
