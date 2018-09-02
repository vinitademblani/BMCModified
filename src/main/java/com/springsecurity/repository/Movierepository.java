package com.springsecurity.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springsecurity.bean.Movie;

@Repository
public interface Movierepository extends JpaRepository<Movie, Long> {
	@Query("from Movie where cityId=?1 and movieId=?2")
	public Optional<Movie>findByCityIdAndMovieId(Long cityId,Long MovieId);
	
	Optional<List<Movie>> findAllByCity(Long cityId);

	
}
