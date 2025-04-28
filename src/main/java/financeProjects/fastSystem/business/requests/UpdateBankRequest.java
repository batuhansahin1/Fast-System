package financeProjects.fastSystem.business.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBankRequest {

	private int id;
	private String name;
	private double totalMoney;
}
