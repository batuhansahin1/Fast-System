package financeProjects.fastSystem.webApi;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import financeProjects.fastSystem.business.abstracts.IbanService;
import financeProjects.fastSystem.business.requests.CreateIbanRequest;
import financeProjects.fastSystem.business.requests.UpdateIbanRequest;
import financeProjects.fastSystem.business.responses.GetAllIbansResponse;
import financeProjects.fastSystem.business.responses.GetIbanByIdResponse;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/iban")
@AllArgsConstructor
public class IbansController {

	private IbanService ibanService;
	
	@GetMapping("/getall")
	List<GetAllIbansResponse> getAllIbans(){
		return this.ibanService.getAllIbans();
	}
	@GetMapping("/{id}")
	GetIbanByIdResponse getById(@PathVariable int id) {
		return this.ibanService.getById(id);
	}
	@PostMapping
	void add( CreateIbanRequest createIbanRequest) {
		this.ibanService.add(createIbanRequest);
	}
	@PutMapping("/update")
	void update(UpdateIbanRequest updateIbanRequest) {
		this.ibanService.update(updateIbanRequest);
	}
	
	@DeleteMapping()
	void delete(int id) {
		this.ibanService.delete(id);
	}
}
