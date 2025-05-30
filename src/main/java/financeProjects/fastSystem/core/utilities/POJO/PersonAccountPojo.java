package financeProjects.fastSystem.core.utilities.POJO;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonAccountPojo {


	
	private LocalDate createdDate;
	
    private String accountCurrency;

	private String ibanNumber;

	
}
