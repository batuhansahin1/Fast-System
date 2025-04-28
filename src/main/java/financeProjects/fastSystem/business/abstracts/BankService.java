package financeProjects.fastSystem.business.abstracts;

import java.util.List;

import financeProjects.fastSystem.business.requests.CreateBankRequest;
import financeProjects.fastSystem.business.requests.UpdateBankRequest;
import financeProjects.fastSystem.business.responses.GetAllBanksResponse;
import financeProjects.fastSystem.business.responses.GetBankByIdResponse;

public interface BankService {

	
	List<GetAllBanksResponse> getAll();
	void add(CreateBankRequest createBankRequest);
	void update(UpdateBankRequest updateBankRequest);
	GetBankByIdResponse getById(int id); 
	void deleteById(int id);
}
