package week6.java.cogip.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import week6.java.cogip.entities.Company;
import week6.java.cogip.repository.CompanyRepository;

@Service
public class CompanyService {
	
	private CompanyRepository companyRepository;
	
	@Autowired
	public CompanyService(CompanyRepository companyRepository) {
		this.companyRepository = companyRepository;
	}

	public CompanyRepository getCompanyRepository() {
		return companyRepository;
	}

	public void setCompanyRepository(CompanyRepository companyRepository) {
		this.companyRepository = companyRepository;
	}

	public Iterable<Company> getCompanies() {
		return companyRepository.findAll();
	}

}
