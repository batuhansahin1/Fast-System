package financeProjects.fastSystem.business.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTransferRequest {

	 
	private int id;
	private String transferStatus;
	private String transferAmount;
}
