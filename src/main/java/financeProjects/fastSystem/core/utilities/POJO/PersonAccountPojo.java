package financeProjects.fastSystem.core.utilities.POJO;

import java.time.LocalDateTime;


import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonAccountPojo {

	private String hesapNo;
	
	private LocalDateTime createdDate;
	
    private String accountCurrency;


	
}
