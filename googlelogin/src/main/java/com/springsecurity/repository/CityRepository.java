package com.springsecurity.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springsecurity.bean.City;
import com.springsecurity.bean.Movie;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
	
	@Query("select c.movies from City c where c.cityId=:cityId")
	List<Movie> findAllMoviesByCityId(@Param("cityId")Long cityId);
	
	@Query("select c.cityName from City c")
	Optional<List<String>> findAllCityNames();
	
}
