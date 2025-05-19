package financeProjects.fastSystem.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AfterCreatingTransferResponse {
    
    private String  transferReferance;

    private String currency;
    
    private String status;

    private String receiverBankName;
    
}
