package week6.java.cogip.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import week6.java.cogip.entities.User;
import week6.java.cogip.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
  
  private final UserRepository userRepository;
  
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }
  
  public List<User> getAllUsers() {
    return (List<User>) userRepository.findAll();
  }
  
  public User createUser(User user) {
    return userRepository.save(user);
  }
  
  public void deleteUser(Short id) {
    userRepository.deleteById(id);
  }
  
  public Optional<User> getUser(Short id) {
    return userRepository.findById(id);
  }
}