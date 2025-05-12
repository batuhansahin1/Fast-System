package financeProjects.fastSystem.dataAcces.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import financeProjects.fastSystem.entities.concretes.Iban;
import financeProjects.fastSystem.entities.concretes.Transfer;

public interface TransferRepository extends JpaRepository<Transfer, Integer> {

	//joini gidip verinin olmadığı transfer tablosuna tabi false döner onla değil verinin
	//Iban tablosunda olup olmadığına bakıcak
	// boolean existsIbanByGonderici_Id(int id);
	// boolean existsIbanByAlici_Id(int id);
	// Iban findIbanByGonderici_Id(int id);
	// Iban findIbanByAlici_Id(int id);
    boolean existsByTransferPojoSenderIban(String iban);
    boolean existsByTransferPojoReceiverIban(String iban);
}
