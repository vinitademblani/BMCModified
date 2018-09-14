package com.springsecurity.bean;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "CITIES")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="cityId")
public class City implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "CITY_ID")
	private Long cityId;

	@Column(name = "CITY_NAME", unique = true)
	@Size(max = 200)
	private String cityName;


	// doubtful even if this is needed or not
	@JsonIgnore
	@OneToMany(mappedBy = "city")
	private Set<Movie> movies;

	public City() {
		super();
	}

	public City(String cityName) {
		this.cityName = cityName;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	

	@Override
	public String toString() {
		return "City [cityId=" + cityId + ", cityName=" + cityName + ", movies=" + movies.size() + "]";
	}

	public Set<Movie> getMovies() {
		return movies;
	}

	public void setMovies(Set<Movie> movies) {
		this.movies = movies;
	}

}
