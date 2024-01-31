package week6.java.cogip.service;

// All imports for the UserService class
import org.springframework.stereotype.Service;
import week6.java.cogip.entities.User;
import week6.java.cogip.repository.UserRepository;

import java.util.List;
import java.util.Optional;

// @Service annotation to indicate that this class is a service
@Service
public class UserService {
  
  // final variable + controller below replace @Autowired from Spring ( not enough secure )
  // UserRepository is used to call the methods from the repository
  // UserRepository is injected in the controller with the constructor
  private final UserRepository userRepository;
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }
  
  // Get "all" users from the database
  public List<User> getAllUsers() {
    return (List<User>) userRepository.findAll();
  }
  // Get a specific user by ID from the database
  public User createUser(User user) {
    return userRepository.save(user);
  }
  // Create a new user in the database
  public void deleteUser(Short id) {
    userRepository.deleteById(id);
  }
  // Update an user in the database
  public Optional<User> getUser(Short id) {
    return userRepository.findById(id);
  }
  
  public void saveUser(User user) {
    userRepository.save(user);
  }
}