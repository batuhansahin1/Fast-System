package financeProjects.fastSystem.dataAcces.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import financeProjects.fastSystem.entities.concretes.Iban;
import financeProjects.fastSystem.entities.concretes.PersonAccount;

public interface IbanRepository extends JpaRepository<Iban, Integer> {

	
	//BU QUERY METHOD İŞE YARAMADI STANDARDI NE ARTIK GERÇEKTEN BİLMİYORUM
	boolean existsPersonAccountByPersonAccount_Id(int personAccountId);
	PersonAccount findPersonAccountByPersonAccount_Id(int personAccountId);
}
