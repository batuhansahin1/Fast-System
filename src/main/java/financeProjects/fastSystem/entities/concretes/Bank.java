 package financeProjects.fastSystem.entities.concretes;

import java.util.List;

import financeProjects.fastSystem.core.utilities.POJO.BankPojo;
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

@Table(name="banks")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bank {

	
//	public Bank(String name,String bankCode,String vKimlikNumber,double totalMoney) {
//	this.bankPojo.setName(name);
//	this.bankPojo.setBankCode(bankCode);
//	this.bankPojo.setvKimlikNumber(vKimlikNumber);
//	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
    
	@Column(name="total_money")
	private double totalMoney;
	
	//şu anlık güncellenmemiş
//	@AttributeOverrides({
//		@AttributeOverride(name="name",column = @Column(name="Name")),
//		@AttributeOverride(name="bankCode",column = @Column(name="bankCode")),
//		@AttributeOverride(name="vKimlikNumber",column=@Column(name="vKimlikNumber"))
//	})
	@Embedded
	private BankPojo bankPojo;
	
	@OneToMany(mappedBy = "bank")
	List<PersonAccount> accounts;
	
	/*
	 * @Column(name = "name") private String name;
	 * 
	 * 
	 * 
	 * @Column(name = "bank_code") private String bankCode;
	 * 
	 * @Column(name = "vergi_kimlik_numarası") private String vKimlikNumber;
	 */
	
	//silinecek çünkü person ve person account'ı eklememiştim yanlış design oluyo
	/*
	 * @OneToMany(mappedBy = "bank") private List<Iban> ibans;
	 */
	
  //create,update,delete  denenecek, pojo entegre edildi read de çalıştı.

}
 