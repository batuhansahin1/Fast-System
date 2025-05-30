package financeProjects.fastSystem.entities.concretes;

import java.util.List;

import financeProjects.fastSystem.core.utilities.POJO.PersonAccountPojo;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
	 
    @Column(name = "account_number",unique = true)
	private String accountNumber;


	@ManyToOne
	@JoinColumn(name = "bank_id")
	private Bank bank;
	
	@ManyToOne
	@JoinColumn(name = "person_id")     
	private Person person;
	
	
	//buraya transferList'i eklemek lazım
	//bir hesabın birden fazla transferList'i olabilir yani birden fazla transfer gelebilir bir hesap
	//birden fazla transfer yapabilir ya da alabilir

	// in memory tutuyor 
	//transfer ile ilgili iç içe çağırmalar yapıp duruyor transfer içinden account'u account içinden
	//transferi şimdilik response nesnesinde göstermeyeceğim
	@OneToMany(mappedBy = "receiverPersonAccount",fetch = FetchType.LAZY)
	List<Transfer> receivedTransfers;  
	@OneToMany(mappedBy = "senderPersonAccount",fetch = FetchType.LAZY)
	List<Transfer> sendedTransfers;

	//bir hesabın birden fazla ibanı olabilir mantığıyla yapmıştık ama böyle bir şey yok 1 hesabın 1 tane ibanı var
	//çünkü kullanıcının açtığı her bir hesap için hesapNumarası oluşturuluyor ve ibanı da bu parametrelerle oluşturuyoruz
	//bu yüzden iban tablosuna gerek yok her iban zaten 1 hesap demek. Iban tablosunu denklemden çıkaracaz ve buraya ibanNumber
	//field'ı ekledim pojolar olduğu için elle mapleme yapmayı düşünüyorum gelen createPersonAccountRequestte olması gereken fieldler:
	//iban,hesapNo,banka adı,kişinin tcKimlikNo'su (veri için nüfusa istek yapılıp öyle kaydedilecek) diğer bilgileri de doğrulamak için
    // tüm bilgileri isteyebilirim,ibanı oluşturup onu bankaya response döndüreceğiz kişiyi de personAccpuntRequestte oluşturacağız
	
	//@OneToMany(mappedBy = "personAccount")
	//List<Iban> ibanList;  

	   
}
