package week6.java.cogip.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import week6.java.cogip.entities.Company;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Short> {

	List<Company> findByType(String type);
	
}
