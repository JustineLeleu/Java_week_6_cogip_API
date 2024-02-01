package week6.java.cogip.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import week6.java.cogip.entities.Company;
import week6.java.cogip.repository.CompanyRepository;

import java.util.Optional;

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
	
	public Iterable<Company> getCompaniesByType(String type){
		return companyRepository.findByType(type);
	}

	public Optional<Company> getCompany(Short companyId) {
		return companyRepository.findById(companyId);
	}

	public Company saveCompany(Company company) {
		return companyRepository.save(company);
	}

	public void delete(Short companyId) {
		companyRepository.deleteById(companyId);
	}

}
