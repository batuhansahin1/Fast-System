package financeProjects.fastSystem.business.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTransferRequest {

	@NotBlank
	@NotNull
	private int transferPojoTransferAmount;
	
	//bunlar iban olucak tabiiki de ibandan bulucaz kişileri
	//buralar düzenlenecek
	@NotBlank
	@NotNull
	private String senderIban;
	
	@NotBlank
	@NotNull
	private String receiverIban;
	@NotBlank
	@NotNull
	private String receiverFirstName;
	@NotBlank
	@NotNull
	private String receiverLastName;
	
	  @NotNull
	  @NotBlank 
	  private String transferPojoTransferDescription;
	 
}
