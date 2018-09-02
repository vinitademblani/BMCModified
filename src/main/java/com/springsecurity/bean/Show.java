package com.springsecurity.bean;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

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
	
	@Column(name = "SHOW_TIME")
	private LocalTime showTime;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "THEATRE_ID")
	private Theatre theatre;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "MOVIE_ID")
	private Movie movie;

	public Show() {
		super();
	}

	public Show(int noOfSeats, LocalTime showTime, Theatre theatre, Movie movie) {
		super();
		this.noOfSeats = noOfSeats;
		this.showTime = showTime;
		this.theatre = theatre;
		this.movie = movie;
	}

	public Long getShowId() {
		return showId;
	}

	public void setShowId(Long showId) {
		this.showId = showId;
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
		return "Show [showId=" + showId + ", noOfSeats=" + noOfSeats + ", showTime=" + showTime + ", theatre=" + theatre
				+ ", movie=" + movie.getMovieName() + "]";
	}

	
}
