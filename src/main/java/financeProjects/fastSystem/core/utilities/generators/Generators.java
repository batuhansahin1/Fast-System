package financeProjects.fastSystem.core.utilities.generators;

public class Generators {

	
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
