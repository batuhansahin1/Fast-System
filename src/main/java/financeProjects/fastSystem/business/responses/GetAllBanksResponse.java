package financeProjects.fastSystem.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllBanksResponse {

	
	private int id;
	//bankPojoName yapınca mapper bankPojo değişkenin name attributesini çekiyor
	private String bankPojoName;
	private double bankPojoTotalMoney;
	private String bankPojoVKimlikNumber;
	private String bankPojoBankCode;
	
}
