package week6.java.cogip.controllers;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import week6.java.cogip.CogipApplicationTests;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class InvoiceTest {
  
  @Autowired
  private MockMvc mockMvc;
  static private int id;

    private final Authentication auth = CogipApplicationTests.createAuth("ROLE_ADMIN");
  
  @Test
  @Order(1)
 void GetTest() throws Exception {
    MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.get("/api/invoice")
                    .with(SecurityMockMvcRequestPostProcessors.authentication(auth)))
            .andExpect(status().isOk())
            .andReturn();
    String response = result.getResponse().getContentAsString();
    id = JsonPath.parse(response).read("$[0].id");
  }
  @Test
  @Order(2)
  void PostTest() throws Exception {
    this.mockMvc.perform(MockMvcRequestBuilders.post("/api/invoice")
                    .param("companyId", "1")
                    .param("contactId", "1")
                    .with(SecurityMockMvcRequestPostProcessors.authentication(auth))
            )
            .andExpect(status().isCreated());
  }
  @Test
  @Order(3)
  void GetByIdTest() throws Exception {
    this.mockMvc.perform(MockMvcRequestBuilders.get("/api/invoice/" + id)
                    .with(SecurityMockMvcRequestPostProcessors.authentication(auth)))
            .andExpect(status().isOk());
  }
  
  @Test
  @Order(4)
  void PutTest() throws Exception {
    this.mockMvc.perform(MockMvcRequestBuilders.put("/api/invoice/" + id)
                    .param("companyId", "2")
                    .param("contactId", "1")
                    .with(SecurityMockMvcRequestPostProcessors.authentication(auth))
            )
            .andExpect(status().isOk());
  }
  
  @Test
  @Order(5)
  void DeleteTest() throws Exception {
    this.mockMvc.perform(MockMvcRequestBuilders.delete("/api/invoice/" + id)
                    .with(SecurityMockMvcRequestPostProcessors.authentication(auth)))
            .andExpect(status().isOk());
  }
  
}
