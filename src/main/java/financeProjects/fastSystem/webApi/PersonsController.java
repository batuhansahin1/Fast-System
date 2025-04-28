package financeProjects.fastSystem.webApi;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import financeProjects.fastSystem.business.abstracts.PersonService;
import financeProjects.fastSystem.business.requests.CreatePersonRequest;
import financeProjects.fastSystem.business.requests.UpdatePersonRequest;
import financeProjects.fastSystem.business.responses.GetAllPersonsResponse;
import financeProjects.fastSystem.business.responses.GetPersonByIdResponse;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/person")
@AllArgsConstructor
public class PersonsController {

	private PersonService personService;

	@GetMapping
	List<GetAllPersonsResponse> getAll(){
		//joinpoint
		
		System.out.println("afterdan önce mi?");
		return this.personService.getAll();
	}
	@GetMapping("/{id}")
	GetPersonByIdResponse getById(@PathVariable int id) {
		return this.personService.getById(id);
	}
	@PostMapping("/add")
	void add(CreatePersonRequest createPersonRequest) {
		this.personService.add(createPersonRequest);
	}
	@PutMapping("/update")
	void update(UpdatePersonRequest updatePersonRequest) {
		this.personService.update(updatePersonRequest);
	}
	@DeleteMapping("/delete")
	void delete(int id) {
		this.personService.delete(id);
	}
}

