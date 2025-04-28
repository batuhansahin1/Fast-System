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
	@NotBlank
	@NotNull
	private int gonderici_id;
	
	@NotBlank
	@NotNull
	private int alici_id;
	
	
	  @NotNull
	  @NotBlank private String transferPojoTransferDescription;
	 
}
