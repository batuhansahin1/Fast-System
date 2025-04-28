package financeProjects.fastSystem.entities.concretes;

import java.util.List;

import financeProjects.fastSystem.core.utilities.POJO.PersonPojo;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "person")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Embedded
	PersonPojo personPojo;
	
	
	@OneToMany(mappedBy = "person")
	List<PersonAccount> accounts;

	
    	

}
