package financeProjects.fastSystem.entities.concretes;

import java.util.List;

import financeProjects.fastSystem.core.utilities.POJO.PersonAccountPojo;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Table(name="person_accounts")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonAccount {

	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	
	@Embedded
    private PersonAccountPojo personAccountPojo;
	
	@ManyToOne
	@JoinColumn(name = "bank_id")
	private Bank bank;
	
	@ManyToOne
	@JoinColumn(name = "person_id")
	private Person person;
	
	@OneToMany(mappedBy = "personAccount")
	List<Iban> ibanList;  
	   
}
