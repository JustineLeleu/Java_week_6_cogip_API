package week6.java.cogip.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import week6.java.cogip.entities.Company;

@Data
public class CompanyDto {
	
	@NotBlank(message = "The name is required.")
	@Size(min = 2, max = 100, message = "The length of name must be between 2 and 100 characters.")
	private String name;
	
	@NotBlank(message = "The country is required.")
	private String country;
	
	@NotBlank(message = "The TVA is required.")
	@Size(min = 10, max = 10, message = "The TVA must have 10 digits")
	private String tva;
	
	@Pattern(regexp = "(^client$)|(^provider$)", message = "The type must be client or provider")
	private String type;
	
	public Company toCompany() {
		Company company = new Company();
		company.setName(name);
		company.setCountry(country);
		company.setTva(tva);
		company.setType(type);
		return company;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getTva() {
		return tva;
	}

	public void setTva(String tva) {
		this.tva = tva;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
