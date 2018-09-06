package com.springsecurity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springsecurity.bean.City;
import com.springsecurity.dao.CityDao;

@Service
public class CityServiceImpl implements CityService {
	@Autowired
	CityDao cityDao;

	public List<City> getAllCities() {
		return cityDao.findAll();
	}
}
