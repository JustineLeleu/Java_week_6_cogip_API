package week6.java.cogip.service;

import org.springframework.stereotype.Service;
import week6.java.cogip.entities.User;
import week6.java.cogip.repository.UserRepository;

import java.util.List;

@Service
public class UserService {
  
  private final UserRepository userRepository;
  
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }
  
  public List<User> getAllUsers() {
    return (List<User>) userRepository.findAll();
  }
  
  public void createUser(User user) {
    userRepository.save(user);
  }
  
  public void deleteUser(Short id) {
    userRepository.deleteById(id);
  }
  
  public User getUser(Short id) {
    return userRepository.findById(id).orElseThrow();
  }
}