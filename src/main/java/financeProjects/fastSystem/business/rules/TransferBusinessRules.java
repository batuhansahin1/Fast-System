package financeProjects.fastSystem.business.rules;

import org.springframework.stereotype.Service;

import financeProjects.fastSystem.core.utilities.exceptions.BusinessException;
import financeProjects.fastSystem.dataAcces.abstracts.TransferRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class TransferBusinessRules {

	
	private TransferRepository transferRepository;
	
	
	public void checkIfGondericiIbanIdExists(int id) {
		
		//existsByIban_Id girilen iban id'ye ait transfer var mı demek oluyor ama benim yapmak istediğim
		//sorgunun iban tablosunda olması ve bunun da iban idye göre olması lazım 
		if(!transferRepository.existsIbanByGonderici_Id(id)) throw new BusinessException("there is no iban with that id please enter valid id");
	System.out.println(id);
	}
	public void checkIfAlıcıIbanIdExists(int id) {
		
		//existsByIban_Id girilen iban id'ye ait transfer var mı demek oluyor ama benim yapmak istediğim
		//sorgunun iban tablosunda olması ve bunun da iban idye göre olması lazım 
		if(!transferRepository.existsIbanByAlici_Id(id)) throw new BusinessException("there is no iban with that id please enter valid id");
	 System.out.println(id);
	}
	//update işlemi için
	public void checkIfTransferIdExists(int id) {
		if(!transferRepository.existsById(id))  throw new BusinessException("there is no transfer with that id please enter valid id");
	}
}

