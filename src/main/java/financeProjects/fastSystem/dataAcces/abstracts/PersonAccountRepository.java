package financeProjects.fastSystem.dataAcces.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import financeProjects.fastSystem.entities.concretes.Bank;
import financeProjects.fastSystem.entities.concretes.Person;
import financeProjects.fastSystem.entities.concretes.PersonAccount;

@Repository
public interface PersonAccountRepository extends JpaRepository<PersonAccount, Integer> {

	//19.04.2025'de işe yaramayan metodlar şimdi işe yaradı ve hata vermiyor sorgular düzeltilmiş
	//isimlendirme aşağıdaki gibi olucak exists"Class adı" By "personAccounttaki değişken adı"_"değişken içindeki attribute adı"
	boolean existsBankByBank_Id(int bankId);
	boolean existsPersonByPerson_Id(int personId);
	//yukarıdakiler çalışmıyor bunun yerine service katmanına ilişkilili entitylerin businesRules'larını
	//da entehre edicem ve böylece hem solid prensiplerinden single responsibility'i ihlal etmiş olmicam
	//repository içinde çağırsam başka bir repository entegre ettiğim için ihlal ediyordum.
	//21.04.2025 yapılacaklar:bu businessRules repository entegreleri yapıp pojoları da entegre eklicem 
	//çalışıyor ama personAccount tablosu içinde bu bankId var mı diye kontrol ediyor ama bizim yapmak istediğimiz banka tablosunda
	//bu id'ye ait banka var mı bu yüzden bank'in businessRuleslarını ve repositorylerini PersonAccountManager classına entegre edicem
	boolean existsByBankId(int bankId);
	//işe yaramıyor
	//boolean existsBankById(int bank_id);
	//@Query("SELECT COUNT(b) > 0 FROM Bank b WHERE b.id = :bankId")
	//boolean existsByBankId(@Param("bankId") int bankId);
	boolean existsByPersonId(int personId);
	//işe yaramıyor
	//boolean existsPersonById(int id);
	//boolean existsPersonByPerson_Id(int id);
	//@Query("SELECT COUNT(p) > 0 FROM Person p WHERE p.id = :personId")

	Bank findBankByBank_Id(int id);
	Person findPersonByPerson_Id(int id);
	/*
	 * @Query("SELECT COUNT(p) > 0 FROM PersonAccount p WHERE p.bank.id = :bankId")
	 * boolean existsByBankId(@Param("bank_id") int id);
	 */
	boolean existsByPerson_PersonPojo_TcKimlikNo(String tcKimlikNo);
	PersonAccount findByPersonAccountPojoIbanNumber(String ibanNumber);
}
 