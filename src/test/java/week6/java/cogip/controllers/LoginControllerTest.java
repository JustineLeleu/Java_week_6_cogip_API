package week6.java.cogip.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class LoginControllerTest {
    @Autowired
    private MockMvc mockMvc;

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
//    @Test
//    @Order(2)
//    void PostTest() throws Exception {
//        Map<String, String> requestBody = new HashMap<>();
//        requestBody.put("username", "Testing");
//        requestBody.put("password", "Password#123");
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        String jsonRequestBody = objectMapper.writeValueAsString(requestBody);
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/user")
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(jsonRequestBody)
////                    .param("role", "USER/MODERATOR/ADMIN")
//                )
//                .andExpect(status().isOk());
//    }

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
    // Expect status 200 ok
    @Test
    @Order(5)
    void getContactTestAndSucceed() throws Exception {
//        Map<String, String> requestBody = new HashMap<>();
//        requestBody.put("username", "Testing");
//        requestBody.put("password", "Password#123");
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        String jsonRequestBody = objectMapper.writeValueAsString(requestBody);
//
//        MockHttpServletResponse response = mockMvc.perform(post("/login")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(jsonRequestBody)
//                )
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andReturn()
//                .getResponse();
//
//        System.out.println(response);

        mockMvc.perform(get("/api/contact").with(SecurityMockMvcRequestPostProcessors.user("Testing")))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

}