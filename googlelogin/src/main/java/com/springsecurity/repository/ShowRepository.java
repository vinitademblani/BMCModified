package com.springsecurity.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springsecurity.bean.Show;

@Repository
public interface ShowRepository extends JpaRepository<Show, Long> {
	@Query("select s from show s where s.movie.movieId:=movieId and s.theatre.theatreId:=theatreId")
	public List<Show> findAllShowsOfMovieInTheatre(@Param("movieId") Long movieId,@Param("theatreId") Long theatreId);
}