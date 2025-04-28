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
public class UpdatePersonRequest {

	@NotBlank
	@NotEmpty
	private int id;
	
	@NotBlank
	@NotEmpty
	private String firstName;
	
	@NotBlank
	@NotEmpty
	private String lastName;
	
	@NotBlank
	@NotEmpty
	@Size(min = 11,max = 11)
	private String tcKimlikNo;
	
	@NotBlank
	@NotEmpty
	private LocalDate birthDate;
}
