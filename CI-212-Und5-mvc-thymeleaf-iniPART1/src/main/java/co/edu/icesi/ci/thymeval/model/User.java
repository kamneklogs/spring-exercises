package co.edu.icesi.ci.thymeval.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String name;

	private String email;

	private UserType type;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthDate;

	private UserGender gender;

	// @OneToMany
	// private List<Appointment> appointments;
}
