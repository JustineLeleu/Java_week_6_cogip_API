package week6.java.cogip.controllers;

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

import week6.java.cogip.entities.Company;
import week6.java.cogip.service.CompanyService;

@RestController
@RequestMapping("/company")
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

	@GetMapping
	public ResponseEntity<Object> listCompanies(){
		return new ResponseEntity<>(companyService.getCompanies(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> company(@PathVariable("id") Short companyId){
		return new ResponseEntity<>(companyService.getCompany(companyId), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Integer> create(@RequestBody Company company) throws Exception {
		if ((company.getName() == null) || (company.getCountry() == null) || (company.getTva() == null) || (company.getType() == null)){
			throw new Exception("Post : some fields are null");
		} 
		else {
			companyService.saveCompany(company);
			return new ResponseEntity<Integer>((Integer) company.getId(), HttpStatus.CREATED);
		}
	}
	
	@PutMapping
	public ResponseEntity<Integer> update(@RequestBody Company company) throws Exception {
		if ((company.getName() == null) || (company.getCountry() == null) || (company.getTva() == null) || (company.getType() == null)){
			throw new Exception("Update : some fields are null");
		} 
		else {
			companyService.saveCompany(company);
			return new ResponseEntity<Integer>((Integer) company.getId(), HttpStatus.CREATED);
		}
	}
	
	@DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteDoctor(@PathVariable Short id){
        companyService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
	
	
	
	

}

