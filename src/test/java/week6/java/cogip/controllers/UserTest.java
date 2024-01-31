package week6.java.cogip.controllers;

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
import org.springframework.security.core.Authentication;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import week6.java.cogip.CogipApplicationTests;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserTest {
  
  @Autowired
  private MockMvc mockMvc;
  static private int id;

  private final Authentication auth = CogipApplicationTests.createAuth("ROLE_ADMIN");
  
  @Test
  @Order(1)
  void GetTest() throws Exception {
    MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.get("/api/user")
                    .with(SecurityMockMvcRequestPostProcessors.authentication(auth)))
            .andExpect(status().isOk())
            .andReturn();
    String response = result.getResponse().getContentAsString();
    id = JsonPath.parse(response).read("$[0].id");
  }
  
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
                    .with(SecurityMockMvcRequestPostProcessors.authentication(auth))
            )
            .andExpect(status().isOk());
  }
  
  @Test
  @Order(3)
  void GetByIdTest() throws Exception {
    this.mockMvc.perform(MockMvcRequestBuilders.get("/api/user/" + id)
                    .contentType(MediaType.APPLICATION_JSON)
                    .with(SecurityMockMvcRequestPostProcessors.authentication(auth))
            )
            .andExpect(status().isOk());
  }
  
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
                    .with(SecurityMockMvcRequestPostProcessors.authentication(auth))
            )
            .andExpect(status().isOk());
  }
  
  @Test
  @Order(5)
  void DeleteTest() throws Exception {
    this.mockMvc.perform(MockMvcRequestBuilders.delete("/api/user/" + id)
                    .contentType(MediaType.APPLICATION_JSON)
                    .with(SecurityMockMvcRequestPostProcessors.authentication(auth))
            )
            .andExpect(status().isOk());
  }
  
}
