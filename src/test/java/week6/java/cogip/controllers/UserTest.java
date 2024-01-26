package week6.java.cogip.controllers;

// All imports for the class UserTest
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


// @SpringBootTest is used to specify that the context under test is a @SpringBootApplication
// @AutoConfigureMockMvc is used to configure MockMvc
// MockMvc is used to perform requests to the application without starting the server
// @TestMethodOrder is used to specify the order in which the tests are executed
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserTest {
  
  // @Autowired is used to inject the MockMvc object
  // MockMvc is used to perform requests to the application without starting the server
  // id variable is used to store the id of the user created in the PostTest() method
  @Autowired
  private MockMvc mockMvc;
  static private int id;
  
  // @Test is used to specify that the method is a test
  // @Order is used to specify the order in which the tests are executed
  // throws Exception is used to throw an exception if the test fails
  // MockMvcRequestBuilders is used to perform requests to the application without starting the server
  // status() is used to check if the status of the response is the same as the expected status
  // $[0].id is used to get the id of the first user in the response
  // Get "all" users from the database
  @Test
  @Order(1)
  void GetTest() throws Exception {
    MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.get("/api/user"))
            .andExpect(status().isOk())
            .andReturn();
    String response = result.getResponse().getContentAsString();
    id = JsonPath.parse(response).read("$[0].id");
  }
  
  // Post a new user in the database
  // Map is used to store the username and password of the user
  // ObjectMapper is used to convert the Map to a JSON string
  @Test
  @Order(2)
  void PostTest() throws Exception {
    Map<String, String> requestBody = new HashMap<>();
    requestBody.put("username", "Justine");
    requestBody.put("password", "Leleu12345#");
    
    ObjectMapper objectMapper = new ObjectMapper();
    String jsonRequestBody = objectMapper.writeValueAsString(requestBody);
    
    this.mockMvc.perform(MockMvcRequestBuilders.post("/api/user")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonRequestBody)
//                    .param("role", "USER/MODERATOR/ADMIN")
            )
            .andExpect(status().isOk());
  }
  
  // Get a specific user by ID from the database
  @Test
  @Order(3)
  void GetByIdTest() throws Exception {
    this.mockMvc.perform(MockMvcRequestBuilders.get("/api/user/" + id)
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isOk());
  }
  
  // Put (edit) a user with a specific ID in the database
  @Test
  @Order(4)
  void PutTest() throws Exception {
    Map<String, String> requestBody = new HashMap<>();
    requestBody.put("username", "Justine");
    requestBody.put("password", "Leleu12345#");
    
    ObjectMapper objectMapper = new ObjectMapper();
    String jsonRequestBody = objectMapper.writeValueAsString(requestBody);
    
    this.mockMvc.perform(MockMvcRequestBuilders.put("/api/user/" + id)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonRequestBody)
            )
            .andExpect(status().isOk());
  }
  
  // Delete a user with a specific ID in the database
  @Test
  @Order(5)
  void DeleteTest() throws Exception {
    this.mockMvc.perform(MockMvcRequestBuilders.delete("/api/user/" + id)
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isOk());
  }
  
}
