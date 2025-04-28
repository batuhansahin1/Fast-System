package financeProjects.fastSystem.business.concretes;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import financeProjects.fastSystem.business.abstracts.TransferService;
import financeProjects.fastSystem.business.requests.CreateTransferRequest;
import financeProjects.fastSystem.business.requests.UpdateTransferRequest;
import financeProjects.fastSystem.business.responses.GetAllTransfersResponse;
import financeProjects.fastSystem.business.responses.GetTransferByIdResponse;
import financeProjects.fastSystem.business.rules.IbanBusinessRules;
import financeProjects.fastSystem.business.rules.TransferBusinessRules;
import financeProjects.fastSystem.core.utilities.mappers.ModelMapperService;
import financeProjects.fastSystem.dataAcces.abstracts.IbanRepository;
import financeProjects.fastSystem.dataAcces.abstracts.TransferRepository;
import financeProjects.fastSystem.entities.concretes.Iban;
import financeProjects.fastSystem.entities.concretes.Transfer;
import lombok.AllArgsConstructor;
import lombok.Data;


@Service
@Data
@AllArgsConstructor
public class TransferManager implements TransferService  {
      
	private TransferRepository transferRepository;
	private TransferBusinessRules transferBusinessRules;
    private ModelMapperService modelMapperService;
    private IbanRepository ibanRepository;
    private IbanBusinessRules ibanBusinessRules;
	
	@Override
	public List<GetAllTransfersResponse> getAll() {
		//mapper yerine manuel set örneği
		List<GetAllTransfersResponse> allTransfersResponse=this.transferRepository.findAll().stream()
				.map(transfer->{
					//this.modelMapperService.forResponse().map(transfer, GetAllTransfersResponse.class)
				    GetAllTransfersResponse transferResponse=new GetAllTransfersResponse();
					transferResponse.setAliciIbanNumber(transfer.getAlici().getIbanPojo().getIbanNumber());
					transferResponse.setGondericiIbanNumber(transfer.getGonderici().getIbanPojo().getIbanNumber());
					transferResponse.setAmount(transfer.getTransferPojo().getTransferAmount());
					transferResponse.setDescription(transfer.getTransferPojo().getTransferDescription());
					return transferResponse;
				  }).collect(Collectors.toList());
				
		return allTransfersResponse;
	}

	@Override
	public GetTransferByIdResponse getById(int id) {
		this.transferBusinessRules.checkIfTransferIdExists(id);
		
	     Transfer transfer=this.transferRepository.findById(id).orElseThrow();
	     GetTransferByIdResponse transferResponse =this.modelMapperService.forResponse().map(transfer, GetTransferByIdResponse.class);
		   transferResponse.setMiktar(transfer.getTransferPojo().getTransferAmount());
		   transferResponse.setDescription(transfer.getTransferPojo().getTransferDescription());
		return transferResponse;
	}

	@Override
	public void add(CreateTransferRequest createTransferRequest) {
		//iban_id alanı olmadığı için 2 farklı fonk tanımlamak zorundaydım 2 farklı alan için 2 farklı sorgu çünkü ikisi ne kadar da Iban olsa da ben 
		//boolean existsIbanByGonderici_Id(int id); gonderici_Id yerine Iban_Id dersem böyle bir alan transfer içinde yok diyor
		//çalışmıyor sadece transfer tablosunda arayabiliyor bu yüzden o kayıtla ildili id olmadığında böyle bir id yok diyor halbuki 
		//iban tablosunda öyle bir veri var bu yüzden bunları kaldırıyoruz 
		/*
		 * this.transferBusinessRules.checkIfGondericiIbanIdExists(createTransferRequest
		 * .getGonderici_id());
		 * this.transferBusinessRules.checkIfAlıcıIbanIdExists(createTransferRequest.
		 * getAlici_id());
		 */
		this.ibanBusinessRules.checkIfIbanIdExists(createTransferRequest.getAlici_id());
		this.ibanBusinessRules.checkIfIbanIdExists(createTransferRequest.getGonderici_id());
		//postgre ilgili bir hata var localDate ile ilgili
		Date transferDateTime= new Date();
		//buradakini de iban tablosundan çekmemiz lazım çünkü bulamayacak ve null döndürecek bu yüzden ibanRepositoryden çağırmamız lazım
		//birazdan bir kere transferRepository üzerinden eklicem bir de ibanRepository üzerinden eklicem
		//hata verdi dediğim gibi
		/*
		 * Iban gonderenIban=this.transferRepository.findIbanByGonderici_Id(
		 * createTransferRequest.getGonderici_id()); Iban
		 * alıcıIban=this.transferRepository.findIbanByAlici_Id(createTransferRequest.
		 * getAlici_id());
		 */
		Iban gonderenIban=this.ibanRepository.findById(createTransferRequest.getGonderici_id()).orElseThrow();
		Iban alıcıIban=this.ibanRepository.findById(createTransferRequest.getAlici_id()).orElseThrow();
		Transfer transfer=this.modelMapperService.forRequest().map(createTransferRequest, Transfer.class);
		      //transferNumber creator
		
				transfer.setGonderici(gonderenIban);
				transfer.setAlici(alıcıIban);
				//hata çıktı pojo ile ilgili
				System.out.println(transfer.getTransferPojo());
				transfer.getTransferPojo().setTransferDate(transferDateTime);
		this.transferRepository.save(transfer);
	}

	@Override
	public void update(UpdateTransferRequest updateTransferRequest) {
		this.transferBusinessRules.checkIfTransferIdExists(updateTransferRequest.getId());
		
		Transfer transfer=this.transferRepository.findById(updateTransferRequest.getId()).orElseThrow();
		Iban gonderenIban=transfer.getGonderici();
		Iban alıcıIban=transfer.getAlici();
		Transfer transferUpdated=this.modelMapperService.forRequest().map(updateTransferRequest, Transfer.class);
		/*
		 * transferUpdated.setGonderici(gonderenIban);
		 * transferUpdated.setAlici(alıcıIban);
		 * transferUpdated.getTransferPojo().setTransferAmount(transfer.getTransferPojo(
		 * ).getTransferAmount());
		 * transferUpdated.getTransferPojo().setTransferDate(transfer.getTransferPojo().
		 * getTransferDate());
		 * transferUpdated.getTransferPojo().setTransferNumber(transfer.getTransferPojo(
		 * ).getTransferNumber());
		 */
		transfer.getTransferPojo().setTransferAmount(updateTransferRequest.getTransferAmount());
		this.transferRepository.save(transfer);
		
	}

	@Override
	public void delete(int id) {
		this.transferBusinessRules.checkIfTransferIdExists(id);
	this.transferRepository.deleteById(id);
		
	}
	
}
