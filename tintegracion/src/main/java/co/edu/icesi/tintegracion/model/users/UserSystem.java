package co.edu.icesi.tintegracion.model.users;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class UserSystem {

    @Id
    private String username;

    private String password;

    private UserType type;

}
