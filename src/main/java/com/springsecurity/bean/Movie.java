package com.springsecurity.bean;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.mysql.jdbc.Blob;

@Entity
@Table(name = "MOVIES")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "movieId")
public class Movie {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "MOVIE_ID")
	private Long movieId;

	@Column(name = "MOVIE_NAME", unique = true)
	@Size(max = 200)
	private String movieName;

	@Column(name = "MOVIE_DURATION")
	private double duration;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "CITY_ID", referencedColumnName = "CITY_ID")
	private City city;

	@JsonIgnore
	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "MOVIE_THEATRE", joinColumns = { @JoinColumn(name = "MOVIE_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "THEATRE_ID") })
	private Set<Theatre> theatres;

	@JsonIgnore
	@OneToMany(mappedBy = "movie", cascade = CascadeType.MERGE)
	private Set<Show> shows;

	private String imageName;

	@Lob
	private byte[] image;

	public Movie() {
		super();
	}

	public Set<Show> getShows() {
		return shows;
	}

	public Movie(@Size(max = 200) String movieName, double duration, City city, Set<Theatre> theatres, String imageName)
	// Blob image)
	{
		super();
		this.movieName = movieName;
		this.duration = duration;
		this.city = city;
		this.theatres = theatres;
		this.imageName = imageName;
		// this.image = image;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] bs) {
		this.image = bs;
	}

	public void setShows(Set<Show> shows) {
		this.shows = shows;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public Long getMovieId() {
		return movieId;
	}

	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

	public Set<Theatre> getTheatres() {
		return theatres;
	}

	public void setTheatres(Set<Theatre> theatres) {
		this.theatres = theatres;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "Movie [movieId=" + movieId + ", movieName=" + movieName + ", duration=" + duration + ", city=" + city.getCityName()
				+ "]";
	}

}
