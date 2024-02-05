package week6.java.cogip.controllers;

// All the imports needed for the controller
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import week6.java.cogip.dtos.UserDto;
import week6.java.cogip.entities.User;
import week6.java.cogip.service.UserService;

import java.util.Optional;

// Controller for user entity with (C)reate (R)ead (U)pdate (D)elete methods and mapping to /api/user
// @RestController is used to create RESTful web services using Spring MVC
// @RequestMapping is used to map web requests to Spring Controller methods
@RestController
public class UserController {
  
  // final variable + controller below replace @Autowired from Spring ( not enough secure )
  // UserService is used to call the methods from the service
  // UserService is injected in the controller with the constructor
  private final UserService userService;
  
  public UserController(UserService userService) {
    this.userService = userService;
  }
  
  // Get "all" users from the database
  // If there is no user, a 404 error is returned
  // If there is users, a 200 status is returned with the users
  @RequestMapping(value = "/api/user", method = RequestMethod.GET)
  public ResponseEntity<?> getAllUsers() {
    return ResponseEntity.ok(userService.getAllUsers());
  }

    // Get a specific user by ID from the database
    // If the user doesn't exist, a 404 error is returned with the message (User with the id : + id + not found )
    // If the user exist, a 200 status is returned with the user
  @RequestMapping(value = "/api/user/{id}", method = RequestMethod.GET)
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

  // Create a new user in the database
  // Lombok is used to create a new user with the UserDto class
  // If the user can't be created, a 404 error is returned with the message (User can't be created)
  // If the user is created, a 200 status is returned with the created user
  @RequestMapping(value = "/api/user", method = RequestMethod.POST)
  public ResponseEntity<Object> createUser(@RequestBody @Valid UserDto userDto){
      try {
          Optional<User> existingUser = userService.getUserByUsername(userDto.getUsername());
          if (existingUser.isPresent()) {
              return new ResponseEntity<>("Username already exists", HttpStatus.BAD_REQUEST);
          }
          User user = userDto.dtoUser(new User());
          user.setRole("USER");
          User createdUser = userService.createUser(user);
          return ResponseEntity.ok("User has been created");
      }
      catch (ResponseStatusException e) {
          return new ResponseEntity<>("User can't be created", HttpStatus.NOT_FOUND);
      }
  }
  @RequestMapping(value = "/api/user", method = RequestMethod.POST, params = {"role"})
  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  public ResponseEntity<Object> createUser(@RequestBody @Valid UserDto userDto,
                                           @RequestParam String role) {
      try {
          Optional<User> existingUser = userService.getUserByUsername(userDto.getUsername());
          if (existingUser.isPresent()) {
              return new ResponseEntity<>("Username already exists", HttpStatus.BAD_REQUEST);
          }
          User user = userDto.dtoUser(new User());
          user.setRole(role);
          User createdUser = userService.createUser(user);
          return ResponseEntity.ok("User has been created");
      }
      catch (ResponseStatusException e) {
          return new ResponseEntity<>("User can't be created", HttpStatus.NOT_FOUND);
      }
  }

  // Update a user with a specific ID in the database
  // Spring Security is used to encode the password ( BCryptPasswordEncoder )
  // If the user doesn't exist, a 404 error is returned with the message (User with the id : + id + not found/can't be edited )
  // If the user exist, the user is updated and a 200 status is returned with the message (User with the id : + id + as been updated )
  @RequestMapping(value = "/api/user/{id}", method = RequestMethod.PUT)
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
      return ResponseEntity.ok(" User with the id : " + id + " has been updated");
    } else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    }
    catch (ResponseStatusException e) {
      return new ResponseEntity<>("User with the id : " + id + " not found/can't be edited", HttpStatus.NOT_FOUND);
    }
  }

  // Delete a user with a specific ID in the database
  // If the user doesn't exist, a 404 error is returned with the message (User with the id : + id + not found/can't be deleted )
  // If the user exist, the user is deleted and a 200 status is returned with the message (User with the id : + id + as been deleted )
  @RequestMapping(value = "/api/user/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<?> deleteUser(@PathVariable Short id) {
    try {
      if (userService.getUser(id).isPresent()) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User with the id " + id +  " has been deleted");
      } else {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
      }
    }
    catch (ResponseStatusException e) {
      return new ResponseEntity<>("User with the id " + id + " can't be deleted / do not exist", HttpStatus.NOT_FOUND);
    }
  }
}
