package financeProjects.fastSystem.business.requests;

import java.time.LocalDate;

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

   //personPart
	@NotBlank
	@NotEmpty
	private String personTcKimlikNo;
	@NotBlank
	@NotEmpty
	private String personFirstName;

	@NotBlank
	@NotEmpty
	private String personLastName;

	@NotBlank
	@NotEmpty
	private LocalDate personBirthDate;

	@NotBlank
	@NotEmpty
	private String personBirthPlace;

	
	@NotBlank
	@NotEmpty
    //banka uygulaması zaten belli
	//banka kısmı
	private String bankName;
	
	@NotBlank
	@NotEmpty
	@Size(min = 10,max = 10)
	private String vKimlikNo;

	@NotBlank
	@NotEmpty
	@Size(min = 5,max = 5)
	private String bankCode;


	//account kısmı
	@NotBlank
	@NotEmpty
	@Size(min = 16,max = 16)
	private String accountNo;
	

	@NotBlank
	@NotEmpty
	@Size(min = 3,max = 3)
	private String accountCurrency;
}
 