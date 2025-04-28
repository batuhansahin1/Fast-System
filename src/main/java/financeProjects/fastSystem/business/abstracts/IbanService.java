package financeProjects.fastSystem.business.abstracts;

import java.util.List;

import financeProjects.fastSystem.business.requests.CreateIbanRequest;
import financeProjects.fastSystem.business.requests.UpdateIbanRequest;
import financeProjects.fastSystem.business.responses.GetAllIbansResponse;
import financeProjects.fastSystem.business.responses.GetIbanByIdResponse;

public interface IbanService {

	List<GetAllIbansResponse> getAllIbans();
	GetIbanByIdResponse getById(int id);
	void add(CreateIbanRequest createIbanRequest);
	void update(UpdateIbanRequest updateIbanRequest);
	void delete(int id);
}
