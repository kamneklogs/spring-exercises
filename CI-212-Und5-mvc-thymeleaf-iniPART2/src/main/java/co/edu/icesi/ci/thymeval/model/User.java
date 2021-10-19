package co.edu.icesi.ci.thymeval.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotBlank(groups = add2.class)
	@Size(min = 2, groups = { add2.class })
	private String name;

	@NotBlank(groups = add1.class)
	@Size(min = 3, groups = { add1.class })
	private String username;

	@NotBlank(groups = add1.class)
	@Size(min = 8, groups = { add1.class })
	private String password;

	@NotBlank(groups = add2.class)
	@Email(groups = { add2.class })
	private String email;

	@NotNull(groups = add2.class)
	private UserType type;

	@Past(groups = add1.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthDate;

	@NotNull(groups = add2.class)
	private UserGender gender;

}
