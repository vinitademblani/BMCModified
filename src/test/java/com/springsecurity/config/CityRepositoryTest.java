package com.springsecurity.config;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.springsecurity.bean.City;
import com.springsecurity.dao.CityDao;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CityRepositoryTest {
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private CityDao cityDao;

	@Test
	public void testCityRepository() {
		City c1 = new City();
		// c1.setCityId(3L);
		c1.setCityName("Delhi");
		entityManager.persist(c1);
		Optional<City> actual = this.cityDao.findByCityName(c1.getCityName());
		assertThat(c1.getCityId()).isEqualTo(actual.get().getCityId());
		assertThat(c1.getCityName()).isEqualTo(actual.get().getCityName());
	}
}
