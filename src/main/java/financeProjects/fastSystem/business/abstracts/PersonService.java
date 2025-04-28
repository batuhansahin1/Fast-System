package financeProjects.fastSystem.business.abstracts;

import java.util.List;

import financeProjects.fastSystem.business.requests.CreatePersonRequest;
import financeProjects.fastSystem.business.requests.UpdatePersonRequest;
import financeProjects.fastSystem.business.responses.GetAllPersonsResponse;
import financeProjects.fastSystem.business.responses.GetPersonByIdResponse;



public interface PersonService {

	List<GetAllPersonsResponse> getAll();
	GetPersonByIdResponse getById(int id);
	void add(CreatePersonRequest createPersonRequest);
	void update(UpdatePersonRequest updatePersonRequest);
	void delete(int id);
}
