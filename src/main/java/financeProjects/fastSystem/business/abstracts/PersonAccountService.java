package financeProjects.fastSystem.business.abstracts;

import java.util.List;

import financeProjects.fastSystem.business.requests.CreatePersonAccountRequest;
import financeProjects.fastSystem.business.requests.UpdatePersonAccountRequest;
import financeProjects.fastSystem.business.responses.AfterCreatingAccountResponse;
import financeProjects.fastSystem.business.responses.GetAllPersonAccountsResponse;
import financeProjects.fastSystem.business.responses.GetPersonAccountByIdResponse;


public interface PersonAccountService {

	
	List<GetAllPersonAccountsResponse> getAll();
	GetPersonAccountByIdResponse getById(int id);
	AfterCreatingAccountResponse add(CreatePersonAccountRequest createPersonAccountRequest); 
	void update(UpdatePersonAccountRequest updatePersonAccountRequest);
	void delete(int id);
	
}
