package week6.java.cogip.service;

import java.util.Optional;

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

	public Optional<Company> getCompany(Short companyId) {
		return companyRepository.findById(companyId);
	}

	public Company addNewCompany(Company company) {
		return companyRepository.save(company);
		
	}

}
