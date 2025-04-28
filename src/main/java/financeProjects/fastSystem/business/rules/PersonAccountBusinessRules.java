package financeProjects.fastSystem.business.rules;

import org.springframework.stereotype.Service;

import financeProjects.fastSystem.core.utilities.exceptions.BusinessException;
import financeProjects.fastSystem.dataAcces.abstracts.PersonAccountRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PersonAccountBusinessRules {

	private PersonAccountRepository personAccountRepository;
	/*
	 * private PersonRepository personRepository; private BankRepository
	 * bankRepository;
	 */
	
	
	public void checkIfPersonAccountIdExists(int id) {
		if(!this.personAccountRepository.existsById(id)) {
			throw new BusinessException("There is no id in systen please enter valid id");
		}
	}
	public void checkIfBankIdExists(int bankId) {
		//yanlış
		System.out.println(bankId +" " +this.personAccountRepository.existsByBankId(bankId));
		if(!this.personAccountRepository.existsByBankId(bankId)) {
			throw new BusinessException("There is no bank id  please enter valid id");
		}
	}
	 public void checkIfPersonIdExists(int personId) {
		 //yanlış
			if(!this.personAccountRepository.existsByPersonId(personId)) {
				throw new BusinessException("there is no customer id please enter valid id");
			}
	}
}
