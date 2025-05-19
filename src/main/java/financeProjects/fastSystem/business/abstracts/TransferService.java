package financeProjects.fastSystem.business.abstracts;

import java.util.List;

import financeProjects.fastSystem.business.requests.CreateTransferRequest;
import financeProjects.fastSystem.business.requests.UpdateTransferRequest;
import financeProjects.fastSystem.business.responses.AfterCreatingTransferResponse;
import financeProjects.fastSystem.business.responses.GetAllTransfersResponse;
import financeProjects.fastSystem.business.responses.GetTransferByIdResponse;

public interface TransferService {

	
	List<GetAllTransfersResponse> getAll();
	GetTransferByIdResponse getById(int id);
	AfterCreatingTransferResponse add (CreateTransferRequest createTransferRequest);
	void update(UpdateTransferRequest updateTransferRequest);
	void delete(int id);
}
