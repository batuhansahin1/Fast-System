package financeProjects.fastSystem.business.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePersonAccountRequest {

	@NotBlank
	@NotEmpty
	private int bankId;
	@NotBlank
	@NotEmpty
	private int personId;
	
	@NotBlank
	@NotEmpty
	@Size(min = 16,max = 16)
	private String hesapNo;
	
	@NotBlank
	@NotEmpty
	@Size(min = 3,max = 3)
	private String accountCurrency;
}
