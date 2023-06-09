package tacos.controller;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import tacos.security.CustomPermissionEvaluator;

import java.util.HashSet;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class HomeControllerTests {
    @Autowired
    private MockMvc mockMvc ;

    @InjectMocks
    private CustomPermissionEvaluator customPermissionEvaluator;

    @Mock
    private Authentication authentication;

    @Test
    @WithMockUser(roles = "USER")
    public void homeTest() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"))
                .andDo(print());
    }

    @Test
    @WithMockUser
    public void 권한검사() throws Exception {
        //Given
        String permission = "isAdmin";
        HashSet<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        //When
        doReturn(authorities).when(authentication).getAuthorities();
        System.out.println("권한검사 결과 : " + customPermissionEvaluator.hasPermission(authentication, null, permission));

        //Then
        mockMvc.perform(get("/test"))
                .andExpect(status().isOk())
                .andExpect(view().name("test"))
                .andDo(print());
    }
}
