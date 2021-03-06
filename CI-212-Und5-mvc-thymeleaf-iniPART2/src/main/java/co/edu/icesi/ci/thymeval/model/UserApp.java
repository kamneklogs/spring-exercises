package co.edu.icesi.ci.thymeval.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Data
public class UserApp {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotBlank(groups = { PersonalInfoValidation.class, CompleteInfoValidation.class })
	@Size(min = 2, groups = { PersonalInfoValidation.class, CompleteInfoValidation.class })
	private String name;

	@NotBlank(groups = CredentialsInfoValidation.class)
	@Size(min = 3, groups = { CredentialsInfoValidation.class })
	private String username;

	@NotNull(groups = CompleteInfoValidation.class, message = "el tamaño debe ser de minimo 8")
	@Size(min = 8, groups = { CredentialsInfoValidation.class })
	private String password;

	@NotBlank(groups = { PersonalInfoValidation.class, CompleteInfoValidation.class })
	@Email(groups = { PersonalInfoValidation.class, CompleteInfoValidation.class })
	private String email;

	@NotNull(groups = { PersonalInfoValidation.class, CompleteInfoValidation.class })
	private UserType type;

	@Past(groups = { CredentialsInfoValidation.class, CompleteInfoValidation.class })
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthDate;

	@NotNull(groups = PersonalInfoValidation.class)
	private UserGender gender;

	@Transient
	@NotNull(groups = CompleteInfoValidation.class, message = "Contraseñas no coinciden")
	private String confirmPassword;

	public void setPassword(String password) {
		this.password = password;
		verifyPasswordConstraints();
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
		verifyPasswordConstraints();
	}

	private void verifyPasswordConstraints() {
		if (this.password == null || this.confirmPassword == null) {
			return;
		} else if (this.password.equals(confirmPassword)) {
			if (!this.confirmPassword.isEmpty() && confirmPassword.length() < 8) {
				password = null;
			}
		} else {
			this.confirmPassword = null;
		}
	}

}
