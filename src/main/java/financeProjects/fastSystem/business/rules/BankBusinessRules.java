package financeProjects.fastSystem.business.rules;

import org.springframework.stereotype.Service;

import financeProjects.fastSystem.core.utilities.exceptions.BusinessException;
import financeProjects.fastSystem.dataAcces.abstracts.BankRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BankBusinessRules {

	private BankRepository bankRepository;
	
	public void checkIfBankIdExists(int id) {
		if(!bankRepository.existsById(id)) {
			throw new BusinessException("There is no bank with that id please enter valid id");
		}
	}
	
	public void checIfBankNameExists(String name) {
		if(bankRepository.existsByBankPojo_Name(name)) {
			System.out.println(name);
			throw new BusinessException("there is a bank already in system pls enter different name");
		}
	}
	public void checkIfBankCodeExists(String bankCode) {
		if(bankRepository.existsByBankPojo_BankCode(bankCode)) {
			throw new BusinessException("There is already a bank with that "+bankCode);
			
		}
	}
	public void checkIfVKimlikNumberExists(String vkn) {
		if(bankRepository.existsByBankPojo_VKimlikNumber(vkn)) {
			throw new BusinessException("There is a record with that vkn pls enter different one");
			
		}
	}
}
