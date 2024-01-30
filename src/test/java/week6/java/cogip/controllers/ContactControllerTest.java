package week6.java.cogip.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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

import java.util.HashMap;
import java.util.Map;

// Tests for the contact controller
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ContactControllerTest {
    @Autowired
    private MockMvc mockMvc;

    static private int id;

    // Test 1
    // Create a contact and must return a status 200 created
    // Stock the id created to use it in other tests
    @Test
    @Order(1)
    void postContactTest() throws Exception {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("firstname", "test");
        requestBody.put("lastname", "testing");
        requestBody.put("phone", "0123456789");
        requestBody.put("email", "test@gmail.com");

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRequestBody = objectMapper.writeValueAsString(requestBody);

        String response = this.mockMvc.perform(post("/api/contact")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequestBody)
                        .param("companyId", "1")
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        id = JsonPath.parse(response).read("id");
    }

    // Test 2
    // Get all contacts and must return status 200 ok
    @Test
    @Order(2)
    void getContactTest() throws Exception {
        this.mockMvc.perform(get("/api/contact"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    // Test 3
    // Get contact by id and must return status 200 ok
    // Use the var id as the id to get
    @Test
    @Order(3)
    void getContactByIdTest() throws Exception {
        System.out.println("use id");
        System.out.println(id);
        this.mockMvc.perform(get("/api/contact/" + id))
                .andDo(print())
                .andExpect(status().isOk());
    }

    // Test 4
    // Update contact by id and must return status 200 ok
    // Use the var id as the id to modify
    @Test
    @Order(4)
    void putContactByIdTest() throws Exception {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("firstname", "testing");
        requestBody.put("lastname", "test");

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRequestBody = objectMapper.writeValueAsString(requestBody);

        this.mockMvc.perform(put("/api/contact/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequestBody)
                )
                .andExpect(status().isOk());
    }

    // Test 5
    // Delete a contact by id and must return status 200 ok
    // Use the var id as the id to delete
    @Test
    @Order(5)
    void deleteContactByIdTest() throws Exception {
        this.mockMvc.perform(delete("/api/contact/" + id))
                .andExpect(status().isOk());
    }
}