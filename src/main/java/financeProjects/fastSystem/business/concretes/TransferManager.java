package financeProjects.fastSystem.business.concretes;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;

import financeProjects.fastSystem.business.abstracts.TransferService;
import financeProjects.fastSystem.business.requests.CreateTransferRequest;
import financeProjects.fastSystem.business.requests.UpdateTransferRequest;
import financeProjects.fastSystem.business.responses.AfterCreatingTransferResponse;
import financeProjects.fastSystem.business.responses.GetAllTransfersResponse;
import financeProjects.fastSystem.business.responses.GetTransferByIdResponse;
import financeProjects.fastSystem.business.rules.BankBusinessRules;
import financeProjects.fastSystem.business.rules.IbanBusinessRules;
import financeProjects.fastSystem.business.rules.PersonAccountBusinessRules;
import financeProjects.fastSystem.business.rules.TransferBusinessRules;
import financeProjects.fastSystem.core.utilities.Helpers.HelperFunctions;
import financeProjects.fastSystem.core.utilities.POJO.TransferPojo;
import financeProjects.fastSystem.core.utilities.exceptions.BusinessException;
import financeProjects.fastSystem.core.utilities.mappers.ModelMapperService;
import financeProjects.fastSystem.dataAcces.abstracts.BankRepository;
import financeProjects.fastSystem.dataAcces.abstracts.IbanRepository;
import financeProjects.fastSystem.dataAcces.abstracts.PersonAccountRepository;
import financeProjects.fastSystem.dataAcces.abstracts.TransferRepository;
import financeProjects.fastSystem.entities.concretes.Bank;
import financeProjects.fastSystem.entities.concretes.PersonAccount;
import financeProjects.fastSystem.entities.concretes.Transfer;
import lombok.AllArgsConstructor;
import lombok.Data;


@Service
@Data
@AllArgsConstructor
public class TransferManager implements TransferService  {
      
	private final TransferRepository transferRepository;
	private final TransferBusinessRules transferBusinessRules;
    private final ModelMapperService modelMapperService;
    private final IbanRepository ibanRepository;
    private final IbanBusinessRules ibanBusinessRules;
	private final PersonAccountRepository personAccountRepository;
	private final PersonAccountBusinessRules personAccountBusinessRules;
	private final BankRepository bankRepository;
	private final BankBusinessRules bankBusinessRules;
	private final HelperFunctions helperFunctions;
	
	@Override
	public List<GetAllTransfersResponse> getAll() {
		//mapper yerine manuel set örneği
		List<GetAllTransfersResponse> allTransfersResponse=this.transferRepository.findAll().stream()
				.map(transfer->{
					//this.modelMapperService.forResponse().map(transfer, GetAllTransfersResponse.class)
				    GetAllTransfersResponse transferResponse=new GetAllTransfersResponse();
				
					transferResponse.setAliciIbanNumber(transfer.getTransferPojo().getReceiverIban());
					transferResponse.setGondericiIbanNumber(transfer.getTransferPojo().getSenderIban());
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
	// en son responseda kaldık  burada uygulama derlemedi
	public AfterCreatingTransferResponse add(CreateTransferRequest createTransferRequest) {
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

		//this.ibanBusinessRules.checkIfIbanIdExists(createTransferRequest.getAlici_id());
		//this.ibanBusinessRules.checkIfIbanIdExists(createTransferRequest.getGonderici_id());
        //transfer içinde yazılacak
		//ikisini de checkIf iban exists ile kontrol edebiliriz
	

		//sistemde böyle bir iban var mı diye kayıt açıcaz bu businessRules gereksiz

		// this.transferBusinessRules.checkIfIbanExists(createTransferRequest.getSenderIban());
        // this.transferBusinessRules.checkIfIbanExists(createTransferRequest.getReceiverIban());
		 try {
		this.personAccountBusinessRules.checkIfIbanExists(createTransferRequest.getSenderIban());
		this.personAccountBusinessRules.checkIfIbanExists(createTransferRequest.getReceiverIban());
            //list bütün ilişkilitablolardaki list verilerini getiriyor 16.05.2025 çözüleccek 
        this.bankBusinessRules.checkIfVKimlikNoExists(createTransferRequest.getSenderBankVkn());
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
		//gerek yok iban tablosu kaldırıldı
		// Iban gonderenIban=this.ibanRepository.findById(createTransferRequest.getGonderici_id()).orElseThrow();
		// Iban alıcıIban=this.ibanRepository.findById(createTransferRequest.getAlici_id()).orElseThrow();
		Transfer transfer=this.modelMapperService.forRequest().map(createTransferRequest, Transfer.class);
		      //transferNumber(transferReferance )creator
			  //daha sonra da bunu response'a döndürücez
		TransferPojo transferPojo=new TransferPojo();
                transfer.setTransferPojo(transferPojo);
          PersonAccount receiverPersonAccount=personAccountRepository
		  .findByPersonAccountPojoIbanNumber(createTransferRequest.getReceiverIban());

		  System.out.println(Boolean.toString(receiverPersonAccount.getPerson()
		  .getPersonPojo().getFirstName().equals(createTransferRequest.getReceiverFirstName()))+
		  Boolean.toString(
			  receiverPersonAccount.getPerson().getPersonPojo().getLastName().equals(createTransferRequest.getReceiverLastName())
			  )
		  
		  );
		  if(!receiverPersonAccount.getPerson().getPersonPojo().getFirstName().equals(createTransferRequest.getReceiverFirstName())
		  || !receiverPersonAccount.getPerson().getPersonPojo().getLastName().equals(createTransferRequest.getReceiverLastName())){
			  //response da döndürebiliriz
			  AfterCreatingTransferResponse afterCreatingTransferResponse=new AfterCreatingTransferResponse();
			  afterCreatingTransferResponse.setStatus("not sended");
			  afterCreatingTransferResponse.setErrorCode("400");
			  afterCreatingTransferResponse.setErrorMessage("Information of receiver person is not valid");
			 // return afterCreatingTransferResponse;
			 throw new Exception("Gönderilen veriler alıcı bilgisiyle uyuşmamaktadır");

		  }
		  PersonAccount	senderPersonAccount=personAccountRepository
		  .findByPersonAccountPojoIbanNumber(createTransferRequest.getSenderIban())	;
		  //veritabanında hangisi varsa onu ekleyeceğiz  ikisi de varsa sender'ı ekleyeceğiz
		  //receiver için merkez bankası bize request atıcak onu da ayırt etmek için burada şart yapmamız lazım
		  //aslında mantık şu olmalı sender ya da receiverdan biri nul dönerse diğerini set etmemiz lazım
		  //ikisi de dönerse ikisini de set etmemiz lazım ama şöyle bir şey var receiver için merkez bankası bize request
		  //attığında set edicez benim her zaman hangisi benim veritabanımdaysa onu setlemem lazım amaşöyle de bir şey var 
		  //transferrequest'i front-enden aldıysam sender benim ama merkez bankası bana request atarsa receiver benim
		  //bankaların yaptığı şöyle bir şey var aslında kendi banka içinden ise alıcıdirekt adını otomatik dolduruyorlar
		  //buradan olayı farklılaştırabiliriz o zaman bu fonksiyonun içinden mi api request yapıcam kendi bankama
		  //hem sender hem de receiver benim bankamdansa hepsi banka bakış açısı için fast sistemi için bir hata yok
		  transfer.getTransferPojo().setTransferAmount(createTransferRequest.getTransferAmount());
		  transfer.getTransferPojo().setTransferDescription(createTransferRequest.getDescription());
		  transfer.setReceiverPersonAccount(receiverPersonAccount);
		  transfer.setSenderPersonAccount(senderPersonAccount);
		  transfer.getTransferPojo().setSenderIban(createTransferRequest.getSenderIban());
				transfer.getTransferPojo().setReceiverIban(createTransferRequest.getReceiverIban());
				//hata çıktı pojo ile ilgili
				//hatanın çözümü pojo üretip ona attributeleri set edip sonra da bunu transfer'e kaydedicez
				System.out.println(transfer.getTransferPojo());
				transfer.getTransferPojo().setTransferDate(transferDateTime);
		this.transferRepository.save(transfer);
		this.bankBusinessRules.checkIfVKimlikNoExists(createTransferRequest.getSenderBankVkn());
		Bank bank=this.bankRepository.findByBankPojo_VKimlikNumber(createTransferRequest.getSenderBankVkn());

		//receiver bankaya api request lazım
		//hatanın nedeni gönderen ve alıcı banka aynı olduğu zaman olay akışı yazdığım koda göre şöyle oluyor
		//gönderici banka mb'ye request yaptı daha sonra mb bu verileri işledi ve alıcı bankaya request attı eğer alıcı bankadan da response gelirse 
		//göndericiye öyle response döndürüyoruz halbuki burada karar mercii mb alıcı banka değil beni alıcı bankadan gelen response ilgilendirmiyor
		//gönderici ve alıcı banka aynıysa program bir api request'in kodunu tamamladan diğerine geçiyor o yüzden ben  response nesnesini alıcı bankadan gelen cevaba göre set etmemeliyim
		// alıcı bankaya request kodunu yazmadan önce kod çalışıyordu
		//hatanın sebebi transfer_ref  unique olarak gönderilmemesi bunun için hata yazmıştım  banka bu 
		// unique olmazsa bu kodu kabul etmiyor  (unique olunca ekledi) ondan önceki hata stringleri != ile karşılaştırıp
		//doğru sonuç çıkmasını bekledim yanlış sonuç verdi .equals() daha doğru sonuç veriyor
		String transferReferance=helperFunctions.generateTransferReferance();
		String uri=UriComponentsBuilder.fromHttpUrl("http://localhost:8080/api/centralbank/transaction/add")
	    		.queryParam("transactionReferance", transferReferance)
				.queryParam("senderFirstName", senderPersonAccount.getPerson().getPersonPojo().getFirstName())
				.queryParam("senderLastName",senderPersonAccount.getPerson().getPersonPojo().getLastName())
				.queryParam("senderIban",senderPersonAccount.getPersonAccountPojo().getIbanNumber())
	    		.queryParam("receiverIban",receiverPersonAccount.getPersonAccountPojo().getIbanNumber())
	    		.queryParam("receiverFirstName",receiverPersonAccount.getPerson().getPersonPojo().getFirstName())
	    		.queryParam("receiverLastName",receiverPersonAccount.getPerson().getPersonPojo().getLastName())
	    		.queryParam("description",createTransferRequest.getDescription())
				.queryParam("currency",receiverPersonAccount.getPersonAccountPojo().getAccountCurrency())
	    		.queryParam("transferAmount", createTransferRequest.getTransferAmount())
				.queryParam("senderBankName",bank.getBankPojo().getName())
	    		.toUriString();
                    
				System.out.println(uri); 

               //alıcı bankaya api request
				Map<String,Object> receiverResponse=helperFunctions.addRequest(uri);
				System.out.println(receiverResponse);
				//if(receiverResponse.get("status").toString()=="ok"){
				AfterCreatingTransferResponse afterCreatingTransferResponse=new AfterCreatingTransferResponse();
			     afterCreatingTransferResponse.setReceiverBankName(receiverPersonAccount.getBank().getBankPojo().getName());
				 afterCreatingTransferResponse.setTransferReferance(transferReferance);
				 afterCreatingTransferResponse.setCurrency(senderPersonAccount.getPersonAccountPojo().getAccountCurrency());
				 afterCreatingTransferResponse.setStatus("OK");
				 //bunu sender banka işlerken kontrol etsin
				 afterCreatingTransferResponse.setErrorCode("200");
				 afterCreatingTransferResponse.setErrorMessage("OK");
				return afterCreatingTransferResponse;
				//}
				//else{
				//	throw new Error("receiver bank has no  response this process cannot complete");
				//}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				throw new Error(e.getMessage());
				//throw new BusinessException(e.getMessage());
			}

                //gönderici bankaya response

	}

	@Override
	public void update(UpdateTransferRequest updateTransferRequest) {
		this.transferBusinessRules.checkIfTransferIdExists(updateTransferRequest.getId());
		
		Transfer transfer=this.transferRepository.findById(updateTransferRequest.getId()).orElseThrow();
		// Iban gonderenIban=transfer.getGonderici();
		// Iban alıcıIban=transfer.getAlici();
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

	//merkez bankasından neden transfer kaydı silinsin
	@Override
	public void delete(int id) {
		this.transferBusinessRules.checkIfTransferIdExists(id);
	this.transferRepository.deleteById(id);
		
	}
	
}
