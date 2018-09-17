package com.springsecurity.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springsecurity.bean.Movie;
import com.springsecurity.bean.Theatre;

@Repository
public interface Movierepository extends JpaRepository<Movie, Long> {
	@Query("select m from Movie m where m.city.cityId=:cityId and m.movieId=:movieId")
	public Optional<Movie>findByCityIdAndMovieId(@Param("cityId")Long cityId,@Param("movieId")Long movieId);
	
	@Query("select m from Movie m where m.movieId=:movieId and m.city.cityId=:cityId")
	public Optional<Movie> findMovieByCityIdAndMovieId(@Param("movieId")Long movieId,@Param("cityId")Long cityId);
	
	@Query("select m.movieName from Movie m where m.movieId=:movieId and m.city.cityId=:cityId")
	public Optional<String> findMovieNameByMovieId(@Param("movieId")Long movieId,@Param("cityId")Long cityId);
	
	@Query("select m.theatres from Movie m where m.movieId=:movieId and m.city.cityId=:cityId")
	public List<Theatre> findAllTheatresByMovieId(@Param("movieId")Long movieId,@Param("cityId")Long cityId);
	
}
