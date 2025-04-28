package financeProjects.fastSystem.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetPersonAccountByIdResponse {

	private String hesapNo;
	private String bankName;
	private String personFirstName;
	private String personTcKimlikNo;
}
