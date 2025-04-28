package financeProjects.fastSystem.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import financeProjects.fastSystem.business.abstracts.PersonService;
import financeProjects.fastSystem.business.requests.CreatePersonRequest;
import financeProjects.fastSystem.business.requests.UpdatePersonRequest;
import financeProjects.fastSystem.business.responses.GetAllPersonsResponse;
import financeProjects.fastSystem.business.responses.GetPersonByIdResponse;
import financeProjects.fastSystem.business.rules.PersonBusinessRules;
import financeProjects.fastSystem.core.utilities.POJO.PersonPojo;
import financeProjects.fastSystem.core.utilities.mappers.ModelMapperService;
import financeProjects.fastSystem.dataAcces.abstracts.PersonRepository;
import financeProjects.fastSystem.entities.concretes.Person;
import lombok.AllArgsConstructor;
import lombok.Data;

@Service
@Data
@AllArgsConstructor
public class PersonManager implements PersonService {

	private PersonRepository personRepository;
	private ModelMapperService modelMapperService;
	private PersonBusinessRules personBusinessRules;
	
	
	@Override
	public List<GetAllPersonsResponse> getAll() {
		List <Person> personList=this.personRepository.findAll();
		List<GetAllPersonsResponse> personResponseList=personList.stream().map(person->{
			this.modelMapperService.forResponse();
			GetAllPersonsResponse getAllPersonResponse=new GetAllPersonsResponse();
			getAllPersonResponse.setFirstName(person.getPersonPojo().getFirstName());
			//getAllPersonResponse.setId(person.getId());
			getAllPersonResponse.setLastName(person.getPersonPojo().getLastName());
			getAllPersonResponse.setTcKimlikNo(person.getPersonPojo().getTcKimlikNo());
		return getAllPersonResponse;
		}).collect(Collectors.toList());
		
		
		return personResponseList;
	}

	@Override
	public GetPersonByIdResponse getById(int id) {
		// TODO Auto-generated method stub
		this.personBusinessRules.checkIfPersonIdExists(id);
		Person person=this.personRepository.findById(id).orElseThrow();
		//yalandan mapping çünkü bütün attributeler pojonun içinde
		GetPersonByIdResponse getPerson=this.modelMapperService.forResponse().map(person, GetPersonByIdResponse.class);
		getPerson.setFirstName(person.getPersonPojo().getFirstName());
		getPerson.setLastName(person.getPersonPojo().getLastName());
		getPerson.setTcKimlikNo(person.getPersonPojo().getTcKimlikNo());
		return getPerson;
	}

	@Override
	public void add(CreatePersonRequest createPersonRequest) {
		
		this.personBusinessRules.checkIfPersonTcKimlikNoExists(createPersonRequest.getTcKimlikNo());
		
		Person person= this.modelMapperService.forRequest().map(createPersonRequest, Person.class);
		//pojo oluşturuyor mu? requestte pojo ile ilgili bir değişken yani pojo altında olan mesela
		//personPojoTcKimlikNumber şeklinde almazsan mapper pojo değişkenini oluşturmuyor
		PersonPojo personPojo=new PersonPojo();
		person.setPersonPojo(personPojo);
		person.getPersonPojo().setBirthDate(createPersonRequest.getBirthDate());
		person.getPersonPojo().setFirstName(createPersonRequest.getFirstName());
		person.getPersonPojo().setLastName(createPersonRequest.getLastName());
		person.getPersonPojo().setTcKimlikNo(createPersonRequest.getTcKimlikNo());
		this.personRepository.save(person);
	}

	@Override
	public void update(UpdatePersonRequest updatePersonRequest) {
		this.personBusinessRules.checkIfPersonIdExists(updatePersonRequest.getId());
		
		//Person person=this.modelMapperService.forRequest().map(updatePersonRequest, Person.class);
		Person person=this.personRepository.findById(updatePersonRequest.getId()).orElseThrow();
		person.getPersonPojo().setBirthDate(updatePersonRequest.getBirthDate());
		person.getPersonPojo().setFirstName(updatePersonRequest.getFirstName());
		person.getPersonPojo().setLastName(updatePersonRequest.getLastName());
		person.getPersonPojo().setTcKimlikNo(updatePersonRequest.getTcKimlikNo());
		this.personRepository.save(person);
	}

	@Override
	public void delete(int id) {
		this.personBusinessRules.checkIfPersonIdExists(id);
		this.personRepository.deleteById(id);
		
	}

	
	
}
