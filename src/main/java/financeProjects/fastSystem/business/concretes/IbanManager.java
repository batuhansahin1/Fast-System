package financeProjects.fastSystem.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import financeProjects.fastSystem.business.abstracts.IbanService;
import financeProjects.fastSystem.business.requests.CreateIbanRequest;
import financeProjects.fastSystem.business.requests.UpdateIbanRequest;
import financeProjects.fastSystem.business.responses.GetAllIbansResponse;
import financeProjects.fastSystem.business.responses.GetIbanByIdResponse;
import financeProjects.fastSystem.business.rules.IbanBusinessRules;
import financeProjects.fastSystem.business.rules.PersonAccountBusinessRules;
import financeProjects.fastSystem.core.utilities.POJO.IbanPojo;
import financeProjects.fastSystem.core.utilities.mappers.ModelMapperService;
import financeProjects.fastSystem.dataAcces.abstracts.IbanRepository;
import financeProjects.fastSystem.dataAcces.abstracts.PersonAccountRepository;
import financeProjects.fastSystem.entities.concretes.Iban;
import financeProjects.fastSystem.entities.concretes.PersonAccount;
import lombok.AllArgsConstructor;
import lombok.Data;

@Service 
@Data
@AllArgsConstructor
public class IbanManager implements IbanService {

    private IbanRepository ibanRepository;
    private ModelMapperService modelMapperService;
    private IbanBusinessRules ibanBusinessRules;
    private PersonAccountBusinessRules personAccountBusinessRules;
    private PersonAccountRepository personAccountRepository;
    

	@Override
	public List<GetAllIbansResponse> getAllIbans() {
		List<GetAllIbansResponse> allIbansResponseList=this.ibanRepository.findAll().stream()
				.map(iban->{
				    GetAllIbansResponse getAllIbanResponse=this.modelMapperService.forResponse().map(iban,GetAllIbansResponse.class);
					getAllIbanResponse.setIbanNumber(iban.getIbanPojo().getIbanNumber());
			
					return getAllIbanResponse;	
				}).collect(Collectors.toList());
		return allIbansResponseList;
	}

	@Override
	public GetIbanByIdResponse getById(int id) {
		ibanBusinessRules.checkIfIbanIdExists(id);
		Iban iban=this.ibanRepository.findById(id).orElseThrow();
		GetIbanByIdResponse ibanResponse =this.modelMapperService.forResponse().map(iban, GetIbanByIdResponse.class);
		//ibanResponse.setBankName(null); bunu mapper mapler
		ibanResponse.setIbanNumber(iban.getIbanPojo().getIbanNumber());
		return ibanResponse;
	}

	@Override
	public void add(CreateIbanRequest createIbanRequest) {
		this.personAccountBusinessRules.checkIfPersonAccountIdExists(createIbanRequest.getPerson_account_id());
	     //ibanBusinessRules.checkIfPersonAccountIdExists(createIbanRequest.getPerson_account_id());
		//ibanRepository içinde findPersonAccount'u veri yoksa bulamaz null döndürür ama bu veri personAccountRepositoryde varsa eklemek zorundayım
		
		//personAccount döndürmediği için hata aldım
	    //PersonAccount personAccount= ibanRepository.findPersonAccountByPersonAccount_Id(createIbanRequest.getPerson_account_id());
	
		PersonAccount personAccount=personAccountRepository.findById(createIbanRequest.getPerson_account_id()).orElseThrow();
		
		Iban iban=this.modelMapperService.forRequest().map(createIbanRequest, Iban.class);
		IbanPojo ibanPojo=new IbanPojo();
		ibanPojo.setIbanNumber(createIbanRequest.getIbanNumber());
		iban.setIbanPojo(ibanPojo); 
		iban.setPersonAccount(personAccount);
		
		this.ibanRepository.save(iban);
	}

	@Override
	public void update(UpdateIbanRequest updateIbanRequest) {
	    ibanBusinessRules.checkIfIbanIdExists(updateIbanRequest.getId());
	    Iban oldIban=ibanRepository.findById(updateIbanRequest.getId()).orElseThrow();
	    //bence yeni nesneye gerek yok bilmiyorum dinamiğibelki de gerek var 
		//Iban iban=this.modelMapperService.forRequest().map(updateIbanRequest, Iban.class);
		/*
		 * iban.setPersonAccount(oldIban.getPersonAccount());
		 * iban.setReceivedTransfers(oldIban.getReceivedTransfers());
		 * iban.setSendedTransfers(oldIban.getSendedTransfers());
		 */
		 oldIban.getIbanPojo().setIbanNumber(updateIbanRequest.getIbanNumber());
		this.ibanRepository.save(oldIban);
		
	}

	@Override
	public void delete(int id) {
		ibanBusinessRules.checkIfIbanIdExists(id);
		this.ibanRepository.deleteById(id);
		
	}
	
}
