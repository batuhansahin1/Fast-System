package financeProjects.fastSystem.webApi;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import financeProjects.fastSystem.business.abstracts.TransferService;
import financeProjects.fastSystem.business.requests.CreateTransferRequest;
import financeProjects.fastSystem.business.requests.UpdateTransferRequest;
import financeProjects.fastSystem.business.responses.AfterCreatingTransferResponse;
import financeProjects.fastSystem.business.responses.GetAllTransfersResponse;
import financeProjects.fastSystem.business.responses.GetTransferByIdResponse;
import lombok.AllArgsConstructor;


@RestController
@RequestMapping("/api/transfers")
@AllArgsConstructor
public class TransfersController {

	private TransferService transferService;
	
	@GetMapping("/getall")
	List<GetAllTransfersResponse> getAll(){
		return this.transferService.getAll();
	}
	
	@GetMapping("/{id}")
	GetTransferByIdResponse getById(int id) {
		return this.transferService.getById(id);
	} 
	@PostMapping("/add")
	AfterCreatingTransferResponse add(CreateTransferRequest createTransferRequest) {
		return this.transferService.add(createTransferRequest);
	}
	@PutMapping
	void update(UpdateTransferRequest updateTransferRequest) {
		this.transferService.update(updateTransferRequest);
	}
	
	@DeleteMapping
	void delete(int id) {
	   this.transferService.delete(id);	
	}
}
