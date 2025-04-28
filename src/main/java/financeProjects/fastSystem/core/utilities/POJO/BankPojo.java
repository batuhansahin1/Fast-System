package financeProjects.fastSystem.core.utilities.POJO;

import jakarta.persistence.Embeddable;

@Embeddable
public class BankPojo {

	
	private String name;
	private String bankCode;
	private String vKimlikNumber;
	
	
	public BankPojo(String name,String bankCode,String vKimlik) {
		this.name=name;
		this.bankCode=bankCode;
		this.vKimlikNumber=vKimlik;
	}
	public BankPojo() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	} 

	public String getvKimlikNumber() {
		return vKimlikNumber;
	}

	public void setvKimlikNumber(String vKimlikNumber) {
		this.vKimlikNumber = vKimlikNumber;
	}
	
}
