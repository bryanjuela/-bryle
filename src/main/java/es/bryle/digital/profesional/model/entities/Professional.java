package es.bryle.digital.profesional.model.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "professional")
public class Professional {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "dni")
	private String dni;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lasttName;
		
	@OneToMany(mappedBy = "professional", cascade = CascadeType.ALL)
	private Set<Sale> sales;
	
	@OneToOne
	@JoinColumn(name = "user")
	private User user;
}
