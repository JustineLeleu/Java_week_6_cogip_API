package week6.java.cogip.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import week6.java.cogip.CogipApplicationTests;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class LoginControllerTest {
    @Autowired
    private MockMvc mockMvc;
    static private int id;
    private final Authentication authUser = CogipApplicationTests.createAuth("ROLE_USER");
    private final Authentication authAdmin = CogipApplicationTests.createAuth("ROLE_ADMIN");

    // Test 1
    // Try to get all contacts when not authenticated
    // Expect error 403 forbidden
    @Test
    @Order(1)
    void getContactTestAndFail() throws Exception {
        mockMvc.perform(get("/api/contact"))
                .andDo(print())
                .andExpect(status().isExpectationFailed());
    }

    // Test 2
    // Create a user for testing authentication
    @Test
    @Order(2)
    void PostTest() throws Exception {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("username", "Testing");
        requestBody.put("password", "Password#123");

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRequestBody = objectMapper.writeValueAsString(requestBody);

        String response = mockMvc.perform(post("/api/user")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonRequestBody)
                                .with(SecurityMockMvcRequestPostProcessors.authentication(authAdmin))
                )
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        id = JsonPath.parse(response).read("id");
    }

    // Test 3
    // Post login to authenticate wrong user
    // Expect error 403
    @Test
    @Order(3)
    void loginUserAndFail() throws Exception {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("username", "WrongTest");
        requestBody.put("password", "WrongPassword#123");

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRequestBody = objectMapper.writeValueAsString(requestBody);

        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequestBody)
                )
                .andDo(print())
                .andExpect(status().isExpectationFailed());
    }

    // Test 4
    // Post login to authenticate correct user
    // Expect status 200 ok
    @Test
    @Order(4)
    void loginUserAndSucceed() throws Exception {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("username", "Testing");
        requestBody.put("password", "Password#123");

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRequestBody = objectMapper.writeValueAsString(requestBody);

        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequestBody)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    // Test 5
    // Try to get all contacts after authentication
    // Expect status 417 expectation failed
    @Test
    @Order(5)
    void getContactWhenNotAuthenticatedTestAndFail() throws Exception {
        mockMvc.perform(get("/api/contact"))
                .andDo(print())
                .andExpect(status().isExpectationFailed());
    }

    // Test 6
    // Try to get all contacts after authentication
    // Expect status 200 ok
    @Test
    @Order(6)
    void getContactTestAndSucceed() throws Exception {
        mockMvc.perform(get("/api/contact").with(SecurityMockMvcRequestPostProcessors.authentication(authUser)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    // Test 7
    // Try to delete a contact after authenticating as user
    // Expect status 403 forbidden
    @Test
    @Order(7)
    void deleteContactAsUserTestAndFail() throws Exception {
        this.mockMvc.perform(delete("/api/contact/" + id)
                        .with(SecurityMockMvcRequestPostProcessors.authentication(authUser)))
                .andExpect(status().isForbidden());
    }

    // Test 8
    // Try to delete a user after authenticating as admin
    // Expect status 200 ok
    @Test
    @Order(8)
    void deleteUserAsAdminTestAndFail() throws Exception {
        this.mockMvc.perform(delete("/api/user/" + id)
                        .with(SecurityMockMvcRequestPostProcessors.authentication(authAdmin)))
                .andExpect(status().isOk());
    }
}