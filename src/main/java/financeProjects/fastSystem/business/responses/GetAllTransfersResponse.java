package financeProjects.fastSystem.business.responses;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllTransfersResponse {

	private String gondericiIbanNumber;
	private String aliciIbanNumber;
	private String amount;
	private String description;
}
