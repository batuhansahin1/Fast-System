package financeProjects.fastSystem.core.utilities.Helpers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class HelperFunctions {
   private final WebClient webClient; 
   
    public Map<String,Object> getResponse(String url){
         System.out.println(url);
        Map<String,Object> responseObject=webClient.get().uri(url).retrieve().bodyToMono(new ParameterizedTypeReference<Map<String,Object>>() {
        }).block();
        return  responseObject;
    }

    public Map<String,Object> addRequest(String url,Map<String,Object> requestObject) throws JsonProcessingException{
      ObjectMapper mapper=new ObjectMapper();
      String json=mapper.writeValueAsString(requestObject);

      Map<String,Object> responseObject=webClient.post().uri(url).bodyValue(json).retrieve()
      .bodyToMono(new ParameterizedTypeReference<Map<String,Object>>() {}).block();

     return responseObject;
    }


    	public String generateIban(String countryCode,String bankCode,String accountNo) {
		
		long controlCode=Math.round(Math.random()*99);
		//0-99 arası random sayı
		String reservedField="0";
		
		return countryCode+controlCode+bankCode+reservedField+accountNo;
	}

	public String generateTransferNumber(){
		
		return "";
	}
}
