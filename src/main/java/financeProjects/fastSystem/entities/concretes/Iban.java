package financeProjects.fastSystem.entities.concretes;

import java.util.List;

import financeProjects.fastSystem.core.utilities.POJO.IbanPojo;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="ibans")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Iban {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
    @Embedded
    IbanPojo ibanPojo;
	//banka ve customer id eklenebilir
	
	
	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name = "bank_id") private Bank bank;
	 */
	
	  @ManyToOne
	  @JoinColumn(name="person_account_id") 
	  PersonAccount personAccount;
	 
    
    @OneToMany(mappedBy = "gonderici")
    List<Transfer> sendedTransfers;
    
    @OneToMany(mappedBy = "alici")
    List<Transfer> receivedTransfers;
    
    
}
