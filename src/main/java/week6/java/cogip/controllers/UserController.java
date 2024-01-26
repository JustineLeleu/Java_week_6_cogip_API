package week6.java.cogip.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import week6.java.cogip.dtos.UserDto;
import week6.java.cogip.entities.User;
import week6.java.cogip.service.UserService;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {
  
  // final + method above = @Autowired
  private final UserService userService;
  
  public UserController(UserService userService) {
    this.userService = userService;
  }
  
  @GetMapping
  public ResponseEntity<?> getAllUsers() {
    return ResponseEntity.ok(userService.getAllUsers());
  }
  @GetMapping("/{id}")
  public ResponseEntity<?> getUser(@PathVariable Short id) {
    try {
      if (userService.getUser(id).isPresent()) {
        return ResponseEntity.ok(userService.getUser(id));
      } else {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
      }
    }
    catch (ResponseStatusException e) {
      return new ResponseEntity<>(id + " User not found", HttpStatus.NOT_FOUND);
    }
  }
  @PostMapping
  public ResponseEntity<Object> createUser(@RequestBody @Valid UserDto userDto,
                                           @RequestParam(required = false, defaultValue = "USER") String role) {
    try {
      User user = userDto.dtoUser(new User());
      user.setRole(role);
      User createdUser = userService.createUser(user);
      return ResponseEntity.ok(createdUser);
    }
    catch (ResponseStatusException e) {
      return new ResponseEntity<>("User can't be created", HttpStatus.NOT_FOUND);
    }
  }
  @PutMapping("/{id}")
  public ResponseEntity<Object> updateUser(@PathVariable Short id,
                                           @RequestParam(required = false) String username,
                                           @RequestParam(required = false) String password,
                                           @RequestParam(required = false) String role) {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    Optional<User> optionalUser = userService.getUser(id);
    try{
    if (optionalUser.isPresent()) {
      User user = optionalUser.get();
      if (username != null) user.setUsername(username);
      if (password != null) user.setPassword(encoder.encode(password));
      if (role != null) user.setRole(role);
      userService.createUser(user);
      return ResponseEntity.ok(user);
    } else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    }
    catch (ResponseStatusException e) {
      return new ResponseEntity<>("User with the id " + id + " not found/can't be edited", HttpStatus.NOT_FOUND);
    }
  }
  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteUser(@PathVariable Short id) {
    try {
      if (userService.getUser(id).isPresent()) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User with the id " + id +  " as been deleted");
      } else {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
      }
    }
    catch (ResponseStatusException e) {
      return new ResponseEntity<>("User with the id " + id + " can't be deleted / do not exist", HttpStatus.NOT_FOUND);
    }
  }
}
