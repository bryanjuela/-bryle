package es.bryle.digital.profesional.model.entities.auth;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name="authority")
public class Authority implements GrantedAuthority{

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

	
	@Override
	public String getAuthority() {
		return name;
	}

}
