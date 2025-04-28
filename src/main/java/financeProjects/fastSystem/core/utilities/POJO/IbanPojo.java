package financeProjects.fastSystem.core.utilities.POJO;

import jakarta.persistence.Embeddable;

@Embeddable
public class IbanPojo {

	//bunda tek bir alan var bunun için POJO'ya gerek duymuyorum

	private String ibanNumber;
	//ekledim çünkü başka özellikler de eklenebilir

	public IbanPojo(String ibanNumber) {
		this.ibanNumber = ibanNumber;
	}
	
	public IbanPojo() {
		
	}

	public String getIbanNumber() {
		return ibanNumber;
	}

	public void setIbanNumber(String ibanNumber) {
		this.ibanNumber = ibanNumber;
	}

	
	
}
