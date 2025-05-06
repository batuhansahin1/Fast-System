package financeProjects.fastSystem.business.concretes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import financeProjects.fastSystem.business.abstracts.PersonAccountService;
import financeProjects.fastSystem.business.requests.CreatePersonAccountRequest;
import financeProjects.fastSystem.business.requests.UpdatePersonAccountRequest;
import financeProjects.fastSystem.business.responses.AfterCreatingAccountResponse;
import financeProjects.fastSystem.business.responses.GetAllPersonAccountsResponse;
import financeProjects.fastSystem.business.responses.GetPersonAccountByIdResponse;
import financeProjects.fastSystem.business.rules.BankBusinessRules;
import financeProjects.fastSystem.business.rules.PersonAccountBusinessRules;
import financeProjects.fastSystem.business.rules.PersonBusinessRules;
import financeProjects.fastSystem.core.utilities.Helpers.HelperFunctions;
import financeProjects.fastSystem.core.utilities.POJO.BankPojo;
import financeProjects.fastSystem.core.utilities.POJO.PersonAccountPojo;
import financeProjects.fastSystem.core.utilities.POJO.PersonPojo;
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

	private final PersonAccountRepository personAccountRepository;
	private final ModelMapperService modelMapperService;
	private final PersonAccountBusinessRules personAccountBusinessRules;
	private final BankRepository bankRepository;
	private final PersonRepository personRepository;
	private final PersonBusinessRules personBusinessRules;
	private final BankBusinessRules bankBusinessRules;
	private final HelperFunctions helperFunctions;
	
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
					getAllPersonAccountResponse.setAccountNo(account.getPersonAccountPojo().getAccountNo());
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
		getPersonAccountResponse.setAccountNo(personAccount.getPersonAccountPojo().getAccountNo());
		getPersonAccountResponse.setPersonFirstName(personAccount.getPerson().getPersonPojo().getFirstName());
		getPersonAccountResponse.setPersonTcKimlikNo(personAccount.getPerson().getPersonPojo().getTcKimlikNo());
		return getPersonAccountResponse;
	} 

	@Override
	//response nesnesi döndürmemiz lazım bundan sonra da banka bizim gönderdiğimiz verilerle hesap açma işlemlerini
	//tamamlayacak
	public AfterCreatingAccountResponse add(CreatePersonAccountRequest createPersonAccountRequest) {
		// TODO Auto-generated method stub
		//çalışmıyor PersonAccount tablosunda sorgu yapıyor
		/*
		* this.personAccountBusinessRules.checkIfBankIdExists(
		 * createPersonAccountRequest.getBankId());
		 * this.personAccountBusinessRules.checkIfPersonIdExists(
		 * createPersonAccountRequest.getPersonId());
		 */
		//gerek yok vKimlikNo olsa nolucak sadece onu eklemiyoruz ki eğer yoksa da error'a gerek yok zaten eklicez
		// this.bankBusinessRules.checkIfVKimlikNumberExists(createPersonAccountRequest.getVKimlikNo());
		//tcKimlikNoya sahip veri varsa onu çekicez ama veri yoksa bize gelen requestteki fieldları set etmemiz lazım
		//hata fırlatmaya ihtiyacımız yok findById null dönerse yeni bir Person oluşturucaz aşağıdaki ezbere yazılmış
		//bir kod
		//this.personBusinessRules.checkIfPersonTcKimlikNoExists(createPersonAccountRequest.getPersonTcKimlikNo());
		
		//personun bilgilerini civil sistemden sorgulamak lazım eğer burada varsa sorgulamaya gerek yok ama yoksa 
		//civil sistemden sorgulamamız lazım
		
		LocalDateTime createdDate= 	LocalDateTime.now();
		PersonAccount personAccount=this.modelMapperService.forRequest().map(createPersonAccountRequest, PersonAccount.class);
	    
		//yukarıdakiyle aynı şekil bunun için de geçerli
		Optional<Bank> optionalBank=this.bankRepository.findByBankPojo_VKimlikNumber(createPersonAccountRequest.getVKimlikNo());
         
		Optional<Person> optionalPerson=this.personRepository.findByPersonPojo_TcKimlikNo(createPersonAccountRequest.getPersonTcKimlikNo());
	    if(optionalPerson.isPresent()){
			Person person=optionalPerson.get();
             personAccount.setPerson(person);
		}
		else {
			//burada bilgileri sorgulamak için api request atıcaz doğruysa aşağıdaki gibi set edicez
			//yanlışsa error vericez validationError 07.05.2025 görevi
			String url=UriComponentsBuilder.fromHttpUrl("localhost:9091/api/person/getPerson")
			.queryParam("tcKimlikNo",createPersonAccountRequest.getPersonTcKimlikNo()).toUriString();
			Map<String,Object> responseObj= helperFunctions.getResponse(url);
			Person person=new Person();
		    PersonPojo personPojo=new PersonPojo();
			personPojo.setTcKimlikNo(createPersonAccountRequest.getPersonTcKimlikNo());
			personPojo.setFirstName(createPersonAccountRequest.getPersonFirstName());
			personPojo.setLastName(createPersonAccountRequest.getPersonLastName());
			personPojo.setBirthDate(createPersonAccountRequest.getPersonBirthDate());
			personPojo.setBirthPlace(createPersonAccountRequest.getPersonBirthPlace());
			person.setPersonPojo(personPojo);
			personRepository.save(person);
			personAccount.setPerson(person);
            
		}
		
		if(optionalBank.isPresent()){

			personAccount.setBank(optionalBank.get());
		}
		else{
			Bank bank=new Bank();
			bank.getBankPojo().setName(createPersonAccountRequest.getBankName());
			bank.getBankPojo().setBankCode(createPersonAccountRequest.getBankCode());
			bank.getBankPojo().setvKimlikNumber(createPersonAccountRequest.getVKimlikNo());
			
			bankRepository.save(bank);
			personAccount.setBank(bank);
		}
		PersonAccountPojo personAccountPojo=new PersonAccountPojo();
	    personAccount.setPersonAccountPojo(personAccountPojo);
		personAccount.getPersonAccountPojo().setCreatedDate(createdDate);
		personAccount.getPersonAccountPojo().setAccountCurrency(createPersonAccountRequest.getAccountCurrency());
		personAccount.getPersonAccountPojo().setAccountNo(createPersonAccountRequest.getAccountNo());
		
		this.personAccountRepository.save(personAccount);

		AfterCreatingAccountResponse afterCreatingAccountResponse=new AfterCreatingAccountResponse();
		afterCreatingAccountResponse.setIbanNumber(personAccount.getPersonAccountPojo().getIbanNumber());
		afterCreatingAccountResponse.setErrorCode("200");
		afterCreatingAccountResponse.setResponseMessage("Başarılı bir şekilde kaydedilmiştir");
		return afterCreatingAccountResponse;
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
		personAccountOld.getPersonAccountPojo().setAccountNo(updatePersonAccountRequest.getAccountNo());
		//personAccount.setIbanList(personAccountOld.getIbanList());
		this.personAccountRepository.save(personAccountOld);
	}

	@Override
	public void delete(int id) {
		this.personAccountBusinessRules.checkIfPersonAccountIdExists(id);
		this.personAccountRepository.deleteById(id);
	}

}
