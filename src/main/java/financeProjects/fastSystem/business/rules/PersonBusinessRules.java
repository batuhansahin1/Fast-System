package financeProjects.fastSystem.business.rules;

import org.springframework.stereotype.Service;

import financeProjects.fastSystem.core.utilities.exceptions.BusinessException;
import financeProjects.fastSystem.dataAcces.abstracts.PersonRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PersonBusinessRules {

	private PersonRepository personRepository;
	
	public void checkIfPersonIdExists(int id) {
		if(!this.personRepository.existsById(id)) {
			throw new BusinessException("There is no record with that id please enter valid id");
		}
		
	}

	public void checkIfPersonTcKimlikNoExists(String tcKimlikNo) {
		if(this.personRepository.existsByPersonPojo_TcKimlikNo(tcKimlikNo)) {
			throw new BusinessException("There is already person with that TCKimlikNo please try different TCKimlikNo");
		}
		
	}

}
