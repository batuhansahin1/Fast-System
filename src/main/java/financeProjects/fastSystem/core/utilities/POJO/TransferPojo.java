package financeProjects.fastSystem.core.utilities.POJO;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransferPojo {

	private Long transferNumber;
	private String transferDescription;
	private int transferAmount;
	private Date transferDate;
    private String senderIban;
    private String receiverIban;



	
	// public TransferPojo() {}
	
	// public TransferPojo(Long transferNumber, String transferDescription, int transferAmount, Date transferDate) {
	// 	//querylere bakılacakbazılarıçalışıyo bazıları çalışmıyor bazıları çalışıyo
	// 	//querylere bakılacakbazılarıçalışıyo bazıları çalışmıyor
	// 	this.transferNumber = transferNumber;
	// 	this.transferDescription = transferDescription;
	// 	this.transferAmount = transferAmount;
	// 	this.transferDate = transferDate;
	// }

	// public Long getTransferNumber() {
	// 	return transferNumber;
	// }
	// public void setTransferNumber(Long transferNumber) {
	// 	this.transferNumber = transferNumber;
	// }
	// public String getTransferDescription() {
	// 	return transferDescription;
	// }
	// public void setTransferDescription(String transferDescription) {
	// 	this.transferDescription = transferDescription;
	// }
	// public int getTransferAmount() {
	// 	return transferAmount;
	// }
	// public void setTransferAmount(int transferAmount) {
	// 	this.transferAmount = transferAmount;
	// }
	// public Date getTransferDate() {
	// 	return transferDate;
	// }
	// public void setTransferDate(Date transferDate) {
	// 	this.transferDate = transferDate;
	// }
	
}
