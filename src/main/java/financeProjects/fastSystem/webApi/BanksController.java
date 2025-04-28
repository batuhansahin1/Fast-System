package financeProjects.fastSystem.webApi;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import financeProjects.fastSystem.business.abstracts.BankService;
import financeProjects.fastSystem.business.requests.CreateBankRequest;
import financeProjects.fastSystem.business.requests.UpdateBankRequest;
import financeProjects.fastSystem.business.responses.GetAllBanksResponse;
import financeProjects.fastSystem.business.responses.GetBankByIdResponse;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/banks")
@AllArgsConstructor
public class BanksController {

    private BankService bankService;
    
    @GetMapping(name = "/getall")
    List<GetAllBanksResponse> getAll(){
    	return this.bankService.getAll();
    }
    
    @GetMapping("/{id}")
    GetBankByIdResponse getBankById(@PathVariable int id) {
    	return this.bankService.getById(id);
    }
    @PostMapping("/add")
    void add(CreateBankRequest createBankRequest) {
    	this.bankService.add(createBankRequest);
    }
    
    @PutMapping
    void update (UpdateBankRequest updateBankRequest) {
    	this.bankService.update(updateBankRequest);
    }
    
    @DeleteMapping
    void delete (int id) {
    	this.bankService.deleteById(id);
    }
}
