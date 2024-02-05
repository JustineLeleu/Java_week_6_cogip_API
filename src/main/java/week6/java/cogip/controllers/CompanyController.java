package week6.java.cogip.controllers;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import week6.java.cogip.dtos.CompanyDto;
import week6.java.cogip.entities.Company;
import week6.java.cogip.service.CompanyService;

@RestController
@RequestMapping("/api/company")
public class CompanyController {
	
	private CompanyService companyService;
	
	@Autowired
	public CompanyController(CompanyService companyService) {
		this.companyService = companyService;
	}

	public CompanyService getCompanyService() {
		return companyService;
	}
	
	public void setCompanyService(CompanyService companyService) {
		this.companyService = companyService;
	}

	// GET method to get all companies
	@GetMapping
	public ResponseEntity<Object> listCompanies(){
		return new ResponseEntity<>(companyService.getCompanies(), HttpStatus.OK);
	}
	
	// GET method to get all companies of the specified type
	@GetMapping("/type/{type}")
	public ResponseEntity<Object> listCompaniesByType(@PathVariable("type") String type){
		return new ResponseEntity<>(companyService.getCompaniesByType(type), HttpStatus.OK);
	}
	
	// GET method to get a company based on the id
	@GetMapping("/{id}")
	public ResponseEntity<Company> company(@PathVariable("id") Short companyId){
		Company company = companyService.getCompany(companyId).orElseThrow(() -> new NoSuchElementException("No company with ID " + companyId));
		return new ResponseEntity<>(company, HttpStatus.OK);
	}
	
	// POST method to add a new company with name, country, tva and type (client or provider)
	@PostMapping
	public ResponseEntity<Object> create(@RequestBody @Valid CompanyDto companyDto) {
		Company company = companyService.saveCompany(companyDto.toCompany());
		return new ResponseEntity<>(company, HttpStatus.CREATED);
	}
	
	// PUT method to update a company with the name, country, tva and type (client or provider) in the body
	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@PathVariable Short id, @RequestBody @Valid CompanyDto companyDto) {
		Company company = companyDto.toCompany();
		company.setId(id);
		company = companyService.saveCompany(company);
		return new ResponseEntity<>(company, HttpStatus.OK);
	}
	
	// DELETE method to remove a company based on the id
	@DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteDoctor(@PathVariable Short id){
        companyService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
	
	
	
	

}

