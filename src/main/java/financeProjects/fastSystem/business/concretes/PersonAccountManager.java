package financeProjects.fastSystem.business.concretes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.cglib.beans.BeanCopier.Generator;
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
import financeProjects.fastSystem.core.utilities.generators.Generators;

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
					getAllPersonAccountResponse.setAccountNo(account.getPersonAccountPojo().getAccountNumber());
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
		getPersonAccountResponse.setAccountNo(personAccount.getPersonAccountPojo().getAccountNumber());
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
		
		LocalDate createdDate= 	LocalDate.now();
		PersonAccount personAccount=this.modelMapperService.forRequest().map(createPersonAccountRequest, PersonAccount.class);
	    
		//yukarıdakiyle aynı şekil bunun için de geçerli banka ve person varsa eklemeyip bilgileri person accountla
		//ilişkilendiriyoruz ama yoksa oluşturup personAccount'u kaydediyoruz bu banka için geçerli değil aslında
		//banka kaydı merkez bankasında yoksa zaten bu veriyi kaydetmemem lazım hata fırlatmam lazım böyle bir banka
		//sistemde yok bu yüzden kayıt oluşturulamadı diye
		//aradan sonra müşteri oluşturup, o müşteriye hesap açıcağız ve bu hesap açma işleminde civil sistemden gelen
		//veri ile doğrulama yapıcaz onun bilgilerini fast sistemine kaydedicez person tablosuna 1 requestte 3 farklı
		// tabloya kayır yapıcaz bunların hepsi fast sitemine personAccount oluştur requesti ile mümkün olucak
		this.bankBusinessRules.checkIfBankCodeExists(createPersonAccountRequest.getBankCode());
		//bunda optional'a gerek yok yoksa zaten hata fırlatıcaz
        Bank bank= this.bankRepository.findByBankPojo_VKimlikNumber(createPersonAccountRequest.getVergiKimlikNo());
		Optional<Person> optionalPerson=this.personRepository.findByPersonPojo_TcKimlikNo(createPersonAccountRequest.getPersonTcKimlikNo());
	    if(optionalPerson.isPresent()){
			Person person=optionalPerson.get();
             personAccount.setPerson(person);
		}
		else {
			//burada bilgileri sorgulamak için api request atıcaz doğruysa aşağıdaki gibi set edicez
			//yanlışsa error vericez validationError 07.05.2025 görevi
			
			String url=UriComponentsBuilder.fromHttpUrl("http://localhost:9091/api/person/getPerson")
			.queryParam("tcKimlikNo",createPersonAccountRequest.getPersonTcKimlikNo()).toUriString();
			
			Map<String,Object> responseObj= helperFunctions.getResponse(url);
			System.out.println(responseObj);
			//veri sıkıntısız geliyor sıkıntıyı hallettik şimdi validation yapmamız lazım
            if(responseObj.equals(null)){
				//veri yok hata döndür
			}
			Person person=new Person();
		    PersonPojo personPojo=new PersonPojo();
			personPojo.setTcKimlikNo(responseObj.get("tcKimlikNo").toString());
			personPojo.setFirstName(responseObj.get("firstName").toString());
			personPojo.setLastName(responseObj.get("lastName").toString());
			LocalDate date=LocalDate.parse( responseObj.get("birthDate").toString());
			personPojo.setBirthDate(date);
			personPojo.setBirthPlace(responseObj.get("birthPlace").toString());
			person.setPersonPojo(personPojo);
			personRepository.save(person);
			personAccount.setPerson(person);
            
		}
		
		// if(optionalBank.isPresent()){
        //     //bank zaten oluşturulmuş olan bir banka olucak sistemde kaydı olmayan banka request atamaz 
		// 	//alttaki kod gereksiz
		// 	personAccount.setBank(optionalBank.get());
		// }
		// else{
		// 	Bank bank=new Bank();
		// 	BankPojo bankPojo=new BankPojo();
		// 	bank.setBankPojo(bankPojo);
		// 	bank.getBankPojo().setName(createPersonAccountRequest.getBankName());
		// 	bank.getBankPojo().setBankCode(createPersonAccountRequest.getBankCode());
		// 	bank.getBankPojo().setvKimlikNumber(createPersonAccountRequest.getVergiKimlikNo());
			
		// 	bankRepository.save(bank);
		// 	personAccount.setBank(bank);
		// }
		//ortak olanlar http request yapsan da yapmasan da eğer bir hata yoksa bankayı 
		//ve personAccountPojo yu ikisinde de set etmek zorundasın sadece veritabanında person var mı
		//diye kontrol ediyoruz varsa civil sisteme boş yere request atmıyoruz
		//istersen atıp güncel verileri de çekebilirsin 
		//ileride zaten biri ikametghını değiştirirse ona sinyal gönderen 
		//bir microservis tasarlayabiliriz
		personAccount.setBank(bank);
		PersonAccountPojo personAccountPojo=new PersonAccountPojo();
	    personAccount.setPersonAccountPojo(personAccountPojo);
		personAccount.getPersonAccountPojo().setCreatedDate(createdDate);
		personAccount.getPersonAccountPojo().setAccountCurrency(createPersonAccountRequest.getAccountCurrency());
		personAccount.getPersonAccountPojo().setAccountNumber(createPersonAccountRequest.getAccountNo());

		personAccount.getPersonAccountPojo().
		setIbanNumber(helperFunctions.generateIban("TR",createPersonAccountRequest.getBankCode()
		, createPersonAccountRequest.getAccountNo()));
		this.personAccountRepository.save(personAccount);

		AfterCreatingAccountResponse afterCreatingAccountResponse=new AfterCreatingAccountResponse();
		
		afterCreatingAccountResponse.setIbanNumber(personAccount.getPersonAccountPojo().getIbanNumber());
		afterCreatingAccountResponse.setResponseMessage("Başarılı bir şekilde kaydedilmiştir");
		afterCreatingAccountResponse.setResponseCode("200");
		afterCreatingAccountResponse.setCreatedDate(createdDate);
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
		personAccountOld.getPersonAccountPojo().setAccountNumber(updatePersonAccountRequest.getAccountNo());
		//personAccount.setIbanList(personAccountOld.getIbanList());
		this.personAccountRepository.save(personAccountOld);
	}

	@Override
	public void delete(int id) {
		this.personAccountBusinessRules.checkIfPersonAccountIdExists(id);
		this.personAccountRepository.deleteById(id);
	}
  
}
