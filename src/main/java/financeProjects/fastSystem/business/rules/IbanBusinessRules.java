package financeProjects.fastSystem.business.rules;

import org.springframework.stereotype.Service;

import financeProjects.fastSystem.core.utilities.exceptions.BusinessException;
import financeProjects.fastSystem.dataAcces.abstracts.IbanRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class IbanBusinessRules {

	private IbanRepository ibanRepository;
	
	public void checkIfIbanIdExists(int id) {
	
		System.out.println(ibanRepository.existsById(id));
		if(!ibanRepository.existsById(id)) {
			throw new BusinessException("There is no ibanId in the system please enter valid id");
		}
	}
	
	public void checkIfPersonAccountIdExists(int personAccountId) {
		System.out.println(personAccountId+" "+ibanRepository.existsPersonAccountByPersonAccount_Id(personAccountId));
		if(!ibanRepository.existsPersonAccountByPersonAccount_Id(personAccountId)) {
			throw new BusinessException("There is no record with that id please enter valid id");
		}
	}
}
