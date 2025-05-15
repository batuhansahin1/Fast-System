package financeProjects.fastSystem.business.responses;

import java.util.List;

import financeProjects.fastSystem.entities.concretes.Transfer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllPersonAccountsResponse {

	
	private String accountNo;
	private String bankName;
	private String personFirstName;
	private String personTcKimlikNo;
	private String accountCurrency;
	private String ibanNumber;
	private List<Transfer> receivedTransfers;
	private List<Transfer> sendedTransfers;
}
