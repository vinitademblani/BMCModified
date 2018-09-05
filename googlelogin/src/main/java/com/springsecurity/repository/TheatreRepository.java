package com.springsecurity.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springsecurity.bean.Theatre;

@Repository
public interface TheatreRepository extends JpaRepository<Theatre, Long> {
	
	@Query("select t.movies from Theatre t where t.theatreId=:theatreId")
	public Optional<List<Theatre>> getAllMoviesInTheatreByTheatreId(Long theatreId);
}
