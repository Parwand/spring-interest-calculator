package net.parwand.springregister;

import net.parwand.springregister.infrastructure.app.UserDetailsServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest
@AutoConfigureMockMvc
public class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserDetailsServiceImpl userDetailsService;

    MockHttpSession buildSession(String role, String name, Integer id) {
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("login", name);
        attributes.put("id", id);

        List<GrantedAuthority> authorities = Collections.singletonList(
                new OAuth2UserAuthority("ROLE_" + role.toUpperCase(), attributes));
        OAuth2User user = new DefaultOAuth2User(authorities, attributes, "login");
        OAuth2AuthenticationToken authenticationToken =
                new OAuth2AuthenticationToken(user, authorities, "whatever");
        MockHttpSession session = new MockHttpSession();
        session.setAttribute(
                HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                new SecurityContextImpl(authenticationToken));
        return session;
    }

    @Test
    @DisplayName("should get the view of admin")
    @WithMockUser
    void test_1() throws Exception {
        mockMvc.perform(get("/admin")
                        .session(buildSession("ADMIN", "test", 2)))
                .andExpect(status().isOk())
                .andExpect(view().name("admin"));
    }
}
