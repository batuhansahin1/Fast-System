package financeProjects.fastSystem.dataAcces.abstracts;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import financeProjects.fastSystem.entities.concretes.Bank;

public interface BankRepository extends JpaRepository<Bank, Integer> {

	//buda çalışıyor denendi BankPojo_Name değişkeniyle oluyo
	boolean existsByBankPojo_Name(String name);
	boolean existsByBankPojo_VKimlikNumber(String vKimlikNumber);
	boolean existsByBankPojo_BankCode(String bankCode);  
	Optional<Bank> findByBankPojo_VKimlikNumber(String vKimlikNumber);
}
