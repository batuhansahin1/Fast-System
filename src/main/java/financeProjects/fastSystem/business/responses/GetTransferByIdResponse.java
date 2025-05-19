package financeProjects.fastSystem.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetTransferByIdResponse {

	
	private String gondericiIbanNumber;
	private String aliciIbanNumber;
	private String miktar;
	private String description;
}
