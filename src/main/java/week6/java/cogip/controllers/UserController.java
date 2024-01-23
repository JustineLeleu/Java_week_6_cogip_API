package week6.java.cogip.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import week6.java.cogip.entities.User;
import week6.java.cogip.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

  @Autowired
  private UserService userService;
  
  @GetMapping
  public ResponseEntity<?> getAllUsers() {
    return ResponseEntity.ok(userService.getAllUsers());
  }
  @GetMapping("/{id}")
  public ResponseEntity<?> getUser(@PathVariable Short id) {
    return ResponseEntity.ok(userService.getUser(id));
  }
  @PostMapping
  public ResponseEntity<Object> createUser(@RequestParam String username, @RequestParam String password, @RequestParam(required = false, defaultValue = "USER") String role) {
    User user = new User(username, password, role);
    userService.createUser(user);
    return ResponseEntity.ok(user);
  }
  @PutMapping("/{id}")
  public ResponseEntity<Object> updateUser(@PathVariable Short id, @RequestParam String username, @RequestParam String password, @RequestParam String role) {
    User user = new User(username, password, role);
    user.setId(id);
    userService.createUser(user);
    return ResponseEntity.ok(user);
  }
  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteUser(@PathVariable Short id) {
    userService.deleteUser(id);
    return ResponseEntity.ok("User deleted");
  }

}
