package financeProjects.fastSystem.business.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateIbanRequest {

	private int id;
	private String ibanNumber; 
}
