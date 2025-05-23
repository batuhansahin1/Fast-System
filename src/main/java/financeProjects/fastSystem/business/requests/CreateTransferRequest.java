package financeProjects.fastSystem.business.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTransferRequest {

	@NotBlank
	@NotNull
	@Size(min = 10,max = 10)
	private String senderBankVkn;

	@NotBlank
	@NotNull
	private String transferAmount;
	
	//bunlar iban olucak tabiiki de ibandan bulucaz kişileri
	//buralar düzenlenecek
	

	@NotBlank
	@NotNull
	@Size(min = 26,max = 26)
	private String senderIban;
	
	@NotBlank
	@NotNull
	@Size(min = 26,max = 26)
	private String receiverIban;
	@NotBlank
	@NotNull
	private String receiverFirstName;
	@NotBlank
	@NotNull
	private String receiverLastName;
	
	  @NotNull
	  @NotBlank 
	  private String description;
	 
}
