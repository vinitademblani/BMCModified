package com.springsecurity.bean;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Arrays;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "SHOWS")
public class Show implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private Long showId;

	@Column(name = "NO_OF_SEATS")
	private int noOfSeats;
	
	@JsonSerialize(using = MyLocalTimeSerializer.class)
	@Column(name = "SHOW_TIME")
	private LocalTime showTime;
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "THEATRE_ID")
	private Theatre theatre;
	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "MOVIE_ID")
	private Movie movie;

	@Column(name="MOVIE_NAME")
	private String movieName;
	
	@Column(name="MOVIE_IMAGE")
	private String imageName;
	
	public String getMovieName() {
		return movieName;
	}
	@Lob
	@Column(name="MOVIE_IMAGE_PIC")
	public byte[] image;
	
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public Show() {
		super();
	}


	public Long getShowId() {
		return showId;
	}

	

	public void setShowId(Long showId) {
		this.showId = showId;
	}

	public Show(int noOfSeats, LocalTime showTime, Theatre theatre, Movie movie, String movieName, String imageName,
			byte[] image) {
		super();
		this.noOfSeats = noOfSeats;
		this.showTime = showTime;
		this.theatre = theatre;
		this.movie = movie;
		this.movieName = movieName;
		this.imageName = imageName;
		this.image = image;
	}

	public int getNoOfSeats() {
		return noOfSeats;
	}

	public void setNoOfSeats(int noOfSeats) {
		this.noOfSeats = noOfSeats;
	}

	public LocalTime getShowTime() {
		return showTime;
	}

	public void setShowTime(LocalTime showTime) {
		this.showTime = showTime;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	@Override
	public String toString() {
		return "Show [showId=" + showId + ", noOfSeats=" + noOfSeats + ", showTime=" + showTime +
			" movieName=" + movieName + ", imageName=" + imageName +  "]";
	}

	/*@Override
	public String toString() {
		return "Show [showId=" + showId + ", noOfSeats=" + noOfSeats + ", showTime=" + showTime 
				+ ", movieName=" + movieName + ", imageName=" + imageName + "]";
	}
	*/
}
