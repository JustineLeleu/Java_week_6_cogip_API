package week6.java.cogip.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import week6.java.cogip.entities.Company;
import week6.java.cogip.services.CompanyService;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

  @Autowired
  private CompanyService companyService;
  
  @GetMapping
  public ResponseEntity<List<Company>> getAllCompanies() {
    return ResponseEntity.ok(companyService.getAllCompanies());
  }
  
}
