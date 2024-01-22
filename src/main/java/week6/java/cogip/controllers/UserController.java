package week6.java.cogip.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import week6.java.cogip.entities.User;
import week6.java.cogip.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserService userService;
  
  @GetMapping
  public ResponseEntity<?> getAllUsers() {
    return ResponseEntity.ok(userService.getAllUsers());
  }
  @PostMapping
  public ResponseEntity<Object> createUser(@RequestParam String username, @RequestParam String password, @RequestParam String role) {
    User user = new User(username, password, role);
    userService.createUser(user);
    return ResponseEntity.ok(user);
  }

}
