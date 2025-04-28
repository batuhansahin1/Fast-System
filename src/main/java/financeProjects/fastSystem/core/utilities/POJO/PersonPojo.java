package financeProjects.fastSystem.core.utilities.POJO;

import java.time.LocalDate;


import jakarta.persistence.Embeddable;

@Embeddable
public class PersonPojo {

	private String firstName;
	private String lastName;
	private String tcKimlikNo;
	private LocalDate birthDate;
	
	public PersonPojo() {}
	
	public PersonPojo(String firstName, String lastName, String tcKimlikNo, LocalDate birthDate) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.tcKimlikNo = tcKimlikNo;
		this.birthDate = birthDate;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getTcKimlikNo() {
		return tcKimlikNo;
	}
	public void setTcKimlikNo(String tcKimlikNo) {
		this.tcKimlikNo = tcKimlikNo;
	}
	public LocalDate getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	
}
