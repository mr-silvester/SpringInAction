package tacos.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class HomeControllerTests {
    @Autowired
    private MockMvc mockMvc ;

    @Test
    @WithMockUser(roles = "USER")
    public void homeTest() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"))
                .andDo(print());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void 권한검사() throws Exception {
        //Given

        //When

        //Then
        mockMvc.perform(get("/test"))
                .andExpect(status().isOk())
                .andExpect(view().name("test"))
                .andDo(print());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void 에러() throws Exception {
        // Given

        // When

        // Then
        mockMvc.perform(get("/admin"))
                .andExpect(status().isForbidden())
                .andExpect(view().name("error403"))
                .andDo(print());
    }
}
