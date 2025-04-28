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
public class CreatePersonRequest {

	@NotBlank
	@NotEmpty
	private String firstName;
	
	@NotEmpty
	@NotBlank
	private String lastName;
	
	
	@NotBlank
	@NotEmpty
	@Size(min = 11,max = 11)
	private String tcKimlikNo;
	
	@NotBlank
	@NotEmpty
	//@DateTimeFormat(pattern = "yyyy/MM/dd hh:mm:ss a")
	//hata var birthDate ile requestteki birthDate eşleşemiyor
	private LocalDate birthDate;
}
