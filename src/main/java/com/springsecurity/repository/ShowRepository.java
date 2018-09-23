package com.springsecurity.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springsecurity.bean.Show;

@Repository
public interface ShowRepository extends JpaRepository<Show, Long> {
	@Query("select s from Show s where s.movie.movieId=:movieId and s.theatre.theatreId=:theatreId")
	public Optional<List<Show>> findAllShowsOfMovieInTheatre(@Param("movieId") Long movieId,@Param("theatreId") Long theatreId);
	
	@Query("select s from Show s where s.movie.city.cityId=:cityId")
	public List<Show> getShowsByCity(@Param("cityId") Long cityId);

	@Query("select s.image from Show s where s.movie.city.cityId=:cityId and s.movie.imageName=:movieImage")
	public byte[] findImageByCityId(@Param("cityId") Long cityId,@Param("movieImage") String movieImage);
	
	@Query("select distinct(s.image) from Show s where s.movie.city.cityId=:cityId")
	public byte[] getAllMovieImageByCityId(@Param("cityId") Long cityId);
}
