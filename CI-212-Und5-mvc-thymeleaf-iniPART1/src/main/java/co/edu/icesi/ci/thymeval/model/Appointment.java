package co.edu.icesi.ci.thymeval.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Data;

@Entity
@Data
public class Appointment {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long id;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;
	
	@DateTimeFormat(iso = ISO.TIME)
	private LocalTime time;
	
	@ManyToOne
	private User patient;
	
	@ManyToOne
	private User doctor;
}
