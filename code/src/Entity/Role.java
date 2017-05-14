package Entity;



import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="role")
public class Role {
	@Id
	@Column(name="id")
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	protected Long id;
	
	@Column(name="type_role")
	protected String typeRole;
	
	@OneToMany(mappedBy = "role")
	private List<Utilisateur> Utilisateurs;
}
