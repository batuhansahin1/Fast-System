package financeProjects.fastSystem.business.rules;

import org.springframework.stereotype.Service;

import financeProjects.fastSystem.core.utilities.exceptions.BusinessException;
import financeProjects.fastSystem.dataAcces.abstracts.TransferRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class TransferBusinessRules {

	
	private TransferRepository transferRepository;
	
	
	//update işlemi için
	public void checkIfTransferIdExists(int id) {
		if(!transferRepository.existsById(id))  throw new BusinessException("there is no transfer with that id please enter valid id");
	}
    public void checkIfIbanExists(String iban) {
        //bu sadece transfer tablosunda ibana ait gönderen ya da alıcı iban kaydı var mı diye bakar gereksiz
		//bir fonk transfer'in daha önce gerçekleşip gerçekleşmediğine de unique oalrak transfer_ref karar veriyor
		//onu da biz merkez bankasında üretiyoruz 
		if(!this.transferRepository.existsByTransferPojoReceiverIban(iban)
		&&!this.transferRepository.existsByTransferPojoSenderIban(iban))
        throw new BusinessException("there is no iban number in fast system pls check and try again");
    }
}

