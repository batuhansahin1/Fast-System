package financeProjects.fastSystem.entities.concretes;

import financeProjects.fastSystem.core.utilities.POJO.TransferPojo;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "transfers")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
	private int id;
    
  @Embedded
  TransferPojo transferPojo;
  @ManyToOne
  @JoinColumn(referencedColumnName="account_number")
  PersonAccount receiverPersonAccount;
  @ManyToOne
  @JoinColumn(referencedColumnName = "account_number")
  PersonAccount senderPersonAccount;
    
	/*
	 * @Column(name="transfer_status") private String transferStatus;
	 */
    //ibandaki idden referans alıcaz ikisi için de
    //private String gondericiIbanId;
    
    //private String alıcıIbanId;
	/*
	 * @Column(name = "transfer_number") private Long transferNumber;
	 * 
	 * @Column(name="transfer_date") private Date transferDate;
	 * 
	 * @Column (name = "transfer_description") private String transferDescription;
	 * 
	 * @Column(name="transfer_amount") private int transferAmount;
	 */
  
    
      

      //buna gerek yok gibi çünkü  iki heap'ın da bizim bankada olacağı kesin değil
      //bu yüzden transfer gerçekleştirilen ya da gerçekleşen account olarak 1 tane tutabiliriz 
      //1 tane tutarsak da 2 hesap bizim bankamızda olabilir iki ibana da eklememiz lazım
      //ama şöyle bir şey var bunu merkez bankasına bildireceğimiz için alan kişi için merkez bankası bize request
      //hepsi banka için geçerli


    // @ManyToOne
    // @JoinColumn(name = "gonderici_iban_id",referencedColumnName = "id")
    // private Iban gonderici;
    
    // @ManyToOne
    // @JoinColumn(name = "alici_iban_id",referencedColumnName = "id")
    // private Iban alici;
}
