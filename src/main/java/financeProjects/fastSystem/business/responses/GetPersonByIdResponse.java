package financeProjects.fastSystem.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetPersonByIdResponse {

    private String firstName;
    private String lastName;
    
	private String tcKimlikNo;
}
