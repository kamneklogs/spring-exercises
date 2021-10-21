package co.edu.icesi.ci.thymeval.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

	@NotBlank(groups = editValidations.class)
	@Size(min = 2, groups = { editValidations.class })
	@NotBlank(groups = add2.class)
	@Size(min = 2, groups = { add2.class })
	private String name;

	@NotBlank(groups = add1.class)
	@Size(min = 3, groups = { add1.class })
	private String username;

	@NotBlank(groups = add1.class)
	@Size(min = 8, groups = { add1.class })
	private String password;

	@NotBlank(groups = editValidations.class)
	@Email(groups = { editValidations.class })
	@NotBlank(groups = add2.class)
	@Email(groups = { add2.class })
	private String email;

	@NotNull(groups = add2.class)
	private UserType type;

	@Past(groups = editValidations.class)
	@Past(groups = add1.class)
	@NotNull(groups = add1.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthDate;

	@NotNull(groups = add2.class)
	private UserGender gender;

	@NotNull(groups = editValidations.class, message = "not match")
	private String confirmPassword;

	public void setPassword(String password) {
		this.password = password;
		checkPassword();// check
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
		checkPassword();// check
	}

	private void checkPassword() {
		if (this.password == null || this.confirmPassword == null) {
			return;
		} else if (!this.password.equals(confirmPassword)) {
			this.confirmPassword = null;
		}
	}

}
