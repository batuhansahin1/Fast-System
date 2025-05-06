package financeProjects.fastSystem.webApi;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import financeProjects.fastSystem.business.abstracts.PersonAccountService;
import financeProjects.fastSystem.business.requests.CreatePersonAccountRequest;
import financeProjects.fastSystem.business.requests.UpdatePersonAccountRequest;
import financeProjects.fastSystem.business.responses.AfterCreatingAccountResponse;
import financeProjects.fastSystem.business.responses.GetAllPersonAccountsResponse;
import financeProjects.fastSystem.business.responses.GetPersonAccountByIdResponse;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/personaccounts")
@AllArgsConstructor
public class PersonAccountsController {

	
	private PersonAccountService personAccountService;

	@GetMapping
	List<GetAllPersonAccountsResponse> getAll(){
		return this.personAccountService.getAll();
	}
	@GetMapping("/{id}")
	GetPersonAccountByIdResponse getById(@PathVariable int id) {
		return this.personAccountService.getById(id);
	}
	@PostMapping("/add")
	AfterCreatingAccountResponse add(CreatePersonAccountRequest createPersonAccountRequest) {
		return this.personAccountService.add(createPersonAccountRequest);
	}
	@PutMapping("/update")
	void update(UpdatePersonAccountRequest updatePersonAccountRequest) {
		this.personAccountService.update(updatePersonAccountRequest);
	}
	@DeleteMapping("/delete")
	void delete(int id) {
		this.personAccountService.delete(id);
	}
	
}
