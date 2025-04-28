package financeProjects.fastSystem.business.concretes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import financeProjects.fastSystem.business.abstracts.PersonAccountService;
import financeProjects.fastSystem.business.requests.CreatePersonAccountRequest;
import financeProjects.fastSystem.business.requests.UpdatePersonAccountRequest;
import financeProjects.fastSystem.business.responses.GetAllPersonAccountsResponse;
import financeProjects.fastSystem.business.responses.GetPersonAccountByIdResponse;
import financeProjects.fastSystem.business.rules.BankBusinessRules;
import financeProjects.fastSystem.business.rules.PersonAccountBusinessRules;
import financeProjects.fastSystem.business.rules.PersonBusinessRules;
import financeProjects.fastSystem.core.utilities.POJO.PersonAccountPojo;
import financeProjects.fastSystem.core.utilities.mappers.ModelMapperService;
import financeProjects.fastSystem.dataAcces.abstracts.BankRepository;
import financeProjects.fastSystem.dataAcces.abstracts.PersonAccountRepository;
import financeProjects.fastSystem.dataAcces.abstracts.PersonRepository;
import financeProjects.fastSystem.entities.concretes.Bank;
import financeProjects.fastSystem.entities.concretes.Person;
import financeProjects.fastSystem.entities.concretes.PersonAccount;
import lombok.AllArgsConstructor;
import lombok.Data;

@Service
@Data
@AllArgsConstructor
public class PersonAccountManager implements PersonAccountService {

	private PersonAccountRepository personAccountRepository;
	private ModelMapperService modelMapperService;
	private PersonAccountBusinessRules personAccountBusinessRules;
	private BankRepository bankRepository;
	private PersonRepository personRepository;
	private PersonBusinessRules personBusinessRules;
	private BankBusinessRules bankBusinessRules;
	
	@Override
	public List<GetAllPersonAccountsResponse> getAll() {
		List<PersonAccount> personAccounts=this.personAccountRepository.findAll();
		List<GetAllPersonAccountsResponse> personAccountList=personAccounts.stream().
				map(account->{
					//this.modelMapperService.forResponse().map(account, GetAllPersonAccountsResponse.class)
					GetAllPersonAccountsResponse getAllPersonAccountResponse=new GetAllPersonAccountsResponse();
					//getname'si null olan bir veri satırı vardı silinince düzeldi pointer oluşturuluyor
					System.out.println(account.getBank().getBankPojo().getName());
					getAllPersonAccountResponse.setBankName(account.getBank().getBankPojo().getName());
					getAllPersonAccountResponse.setHesapNo(account.getPersonAccountPojo().getHesapNo());
					getAllPersonAccountResponse.setPersonFirstName(account.getPerson().getPersonPojo().getFirstName());
					getAllPersonAccountResponse.setPersonTcKimlikNo(account.getPerson().getPersonPojo().getTcKimlikNo());
					getAllPersonAccountResponse.setAccountCurrency(account.getPersonAccountPojo().getAccountCurrency());
				return getAllPersonAccountResponse;
				}).collect(Collectors.toList());
				
		
		return personAccountList;
	}

	@Override
	public GetPersonAccountByIdResponse getById(int id) {
		// TODO Auto-generated method stub
		this.personAccountBusinessRules.checkIfPersonAccountIdExists(id);
		PersonAccount personAccount=this.personAccountRepository.findById(id).orElseThrow();
		GetPersonAccountByIdResponse getPersonAccountResponse=this.modelMapperService.forResponse().map(personAccount, GetPersonAccountByIdResponse.class);
		//pojolar olduğu için mapperlar set etmez çünkü adını bulamazlar
		getPersonAccountResponse.setBankName(personAccount.getBank().getBankPojo().getName());
		getPersonAccountResponse.setHesapNo(personAccount.getPersonAccountPojo().getHesapNo());
		getPersonAccountResponse.setPersonFirstName(personAccount.getPerson().getPersonPojo().getFirstName());
		getPersonAccountResponse.setPersonTcKimlikNo(personAccount.getPerson().getPersonPojo().getTcKimlikNo());
		return getPersonAccountResponse;
	}

	@Override
	public void add(CreatePersonAccountRequest createPersonAccountRequest) {
		// TODO Auto-generated method stub
		
		//çalışmıyor PersonAccount tablosunda sorgu yapıyor
		/*
		 * this.personAccountBusinessRules.checkIfBankIdExists(
		 * createPersonAccountRequest.getBankId());
		 * this.personAccountBusinessRules.checkIfPersonIdExists(
		 * createPersonAccountRequest.getPersonId());
		 */
		this.bankBusinessRules.checkIfBankIdExists(createPersonAccountRequest.getBankId());
		this.personBusinessRules.checkIfPersonIdExists(createPersonAccountRequest.getPersonId());
		LocalDateTime createdDate= 	LocalDateTime.now();
		PersonAccount personAccount=this.modelMapperService.forRequest().map(createPersonAccountRequest, PersonAccount.class);
	    
		Bank bank=this.bankRepository.findById(createPersonAccountRequest.getBankId()).orElseThrow();
		Person person=this.personRepository.findById(createPersonAccountRequest.getPersonId()).orElseThrow();
	    PersonAccountPojo personAccountPojo=new PersonAccountPojo();
	    personAccount.setPersonAccountPojo(personAccountPojo);
		personAccount.getPersonAccountPojo().setCreatedDate(createdDate);
		personAccount.getPersonAccountPojo().setAccountCurrency(createPersonAccountRequest.getAccountCurrency());
		personAccount.getPersonAccountPojo().setHesapNo(createPersonAccountRequest.getHesapNo());
		personAccount.setBank(bank);
		personAccount.setPerson(person);
		this.personAccountRepository.save(personAccount);
	}

	@Override
	public void update(UpdatePersonAccountRequest updatePersonAccountRequest) {
	this.personAccountBusinessRules.checkIfPersonAccountIdExists(updatePersonAccountRequest.getId());
	PersonAccount personAccountOld=this.personAccountRepository.findById(updatePersonAccountRequest.getId()).orElseThrow();
		//PersonAccount personAccount=this.modelMapperService.forRequest().map(updatePersonAccountRequest, PersonAccount.class);
		/*
		 * personAccount.setBank(personAccountOld.getBank());
		 * personAccount.setPerson(personAccountOld.getPerson());
		 */
		personAccountOld.getPersonAccountPojo().setHesapNo(updatePersonAccountRequest.getHesapNo());
		//personAccount.setIbanList(personAccountOld.getIbanList());
		this.personAccountRepository.save(personAccountOld);
	}

	@Override
	public void delete(int id) {
		this.personAccountBusinessRules.checkIfPersonAccountIdExists(id);
		this.personAccountRepository.deleteById(id);
	}

}
