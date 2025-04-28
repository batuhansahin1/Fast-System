package financeProjects.fastSystem.business.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBankRequest {

	//bankPojoNameyapınca mapper bankPojo değişkenin altındaki name attributune set ediyor
	@NotBlank
	@NotNull
	private String bankPojoName;
	
	@NotBlank
	@NotNull
	private double totalMoney;
	
	@NotBlank
	@NotEmpty
	@Size(min = 5,max = 5)
	private String bankPojoBankCode;
	
	@NotBlank
	@NotEmpty
	@Size(min = 10,max = 10)
	private String bankPojoVKimlikNumber;
}
