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
public class CreateIbanRequest {

	
	@NotNull
	@NotBlank
	@Size(min =26,max = 26 )
	private String ibanNumber;
	private int person_account_id;
}
