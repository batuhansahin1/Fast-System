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
public class UpdatePersonAccountRequest {

	@NotBlank
	@NotEmpty
	private int id;
	
	@NotBlank
	@NotEmpty
	@Size(min = 16,max = 16)// string ve kolleksiyonlar (list,set,map)gibi ve diziler üzerinde kullanılabilir
	private String accountNo;
	
}
