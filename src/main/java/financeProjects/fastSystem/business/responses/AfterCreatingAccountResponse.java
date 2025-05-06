package financeProjects.fastSystem.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AfterCreatingAccountResponse {
    
    private String ibanNumber;
    private String errorCode;
    private String errorMessage;
    private String responseCode;
    private String responseMessage;
}
