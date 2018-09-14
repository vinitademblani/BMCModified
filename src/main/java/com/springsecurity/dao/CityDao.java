/*package com.springsecurity.dao;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springsecurity.bean.City;

@Repository
public interface CityDao extends JpaRepository<City, Long> {

	@Query("Select c from City c where c.cityName=:cityName")
	public Optional<City> findByCityName(@Param("cityName")String cityName);
}
*/