package financeProjects.fastSystem.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import financeProjects.fastSystem.business.abstracts.BankService;
import financeProjects.fastSystem.business.requests.CreateBankRequest;
import financeProjects.fastSystem.business.requests.UpdateBankRequest;
import financeProjects.fastSystem.business.responses.GetAllBanksResponse;
import financeProjects.fastSystem.business.responses.GetBankByIdResponse;
import financeProjects.fastSystem.business.rules.BankBusinessRules;
import financeProjects.fastSystem.core.utilities.mappers.ModelMapperService;
import financeProjects.fastSystem.dataAcces.abstracts.BankRepository;
import financeProjects.fastSystem.entities.concretes.Bank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Service
@Data
@AllArgsConstructor
public class BankManager implements BankService {
	
	private BankRepository bankRepository;
	private ModelMapperService modelMapperService;
	private BankBusinessRules bankBusinessRules;

	@Override
	public List<GetAllBanksResponse> getAll() {
		List<Bank> banks=this.bankRepository.findAll();
		List<GetAllBanksResponse> banksResponseList =banks.stream().map(bank->this.modelMapperService.forResponse().map(bank,GetAllBanksResponse.class ))
				.collect(Collectors.toList());
		
		return banksResponseList;
	}
	
	@Override
	public GetBankByIdResponse getById(int id) { 
		this.bankBusinessRules.checkIfBankIdExists(id);
		Bank bank=this.bankRepository.findById(id).orElseThrow();
		GetBankByIdResponse bankResponse=this.modelMapperService.forResponse().map(bank, GetBankByIdResponse.class);
		return bankResponse;
	}
	
	@Override
	public void add(CreateBankRequest createBankRequest) {
		//businesRules
		
		this.bankBusinessRules.checIfBankNameExists(createBankRequest.getBankPojoName());
		this.bankBusinessRules.checkIfVKimlikNumberExists(createBankRequest.getBankPojoVKimlikNumber());
		//this.bankBusinessRules.checkIfBankCodeExists(createBankRequest.getBankPojoBankCode());
		//vkn ve bankCode'yi requestte kontrol ediyoruz
		
		Bank bank=this.modelMapperService.forRequest().map(createBankRequest, Bank.class);
		this.bankRepository.save(bank);
	}

	@Override
	public void update(UpdateBankRequest updateBankRequest) {
		//businesrules
		this.bankBusinessRules.checkIfBankIdExists(updateBankRequest.getId());
		this.bankBusinessRules.checIfBankNameExists(updateBankRequest.getName());
		Bank oldBank=this.bankRepository.findById(updateBankRequest.getId()).orElseThrow();
		//mapper verilmeyen fieldları null setliyor bu yüzden onları elle set etmek lazım
		Bank bank=this.modelMapperService.forRequest().map(updateBankRequest, Bank.class);
		/*
		 * bank.getBankPojo().setBankCode(oldBank.getBankPojo().getBankCode());
		 * bank.setAccounts(oldBank.getAccounts());
		 * bank.getBankPojo().setvKimlikNumber(oldBank.getBankPojo().getvKimlikNumber())
		 * ;
		 */
		oldBank.getBankPojo().setName(updateBankRequest.getName());
		oldBank.setTotalMoney(updateBankRequest.getTotalMoney());
		this.bankRepository.save(oldBank);
		
	}

	@Override
	public void deleteById(int id) {
		this.bankBusinessRules.checkIfBankIdExists(id);
		this.bankRepository.deleteById(id);
	}



}
