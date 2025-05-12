package financeProjects.fastSystem.business.responses;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AfterCreatingAccountResponse {
    
    private LocalDate createdDate;
    private String ibanNumber;
    private String errorCode;
    private String errorMessage;
    private String responseCode;
    private String responseMessage;
}
