package net.parwand.springregister.infrastructure.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @Author Parwand Alsino
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class SecurityTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    ClientRegistrationRepository clientRegistrationRepository;

    /**
     * TODO: Testing with Global Method Security.
     * TODO: Testing {@link WithUserDetails}
     * TODO: Testing all Roles and permissions.
     */

    @Test
    @DisplayName("Redirection Without admin login")
    void test_1() throws Exception {
        mockMvc.perform(get("/admin"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    @DisplayName("Redirection Without student login")
    void test_2() throws Exception {
        mockMvc.perform(get("/student"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }


    @Test
    @DisplayName("Redirection Without user login")
    void test_3() throws Exception {
        mockMvc.perform(get("/user"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }


    @Test
    @DisplayName("Invalid CSRF_ATTACK_TOKEN is forbidden")
    void test_4() throws Exception {
        mockMvc.perform(post("/user").with(csrf().useInvalidToken()))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("with valid csrf_token get redirection to login")
    void test_5() throws Exception {
        mockMvc.perform(post("/").with(csrf())).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("http://localhost/login"));
    }

    /**
     * {@link WithUserDetails} It will be populated with an UsernamePasswordAuthenticationToken that uses the username of value().
     * The value (user) must absolutely exist in database or in UserDetails Bean
     */
    @Test
    @DisplayName("Administrator has access to the admin page")
    @WithUserDetails(value = "admin")
    void test_6() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/admin")).andExpect(status().isOk()).andReturn();
        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(200);
    }

    /**
     * {@link WithMockUser} creat the authentication in security.
     * It creates a user which is already authenticated.
     */
    @Test
    @DisplayName("Administrator has access to the student page")
    @WithUserDetails(value = "admin")
    void test_7() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/student")).andExpect(status().isOk()).andReturn();
        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(200);
    }

    @Test
    @DisplayName("Administrator has access to the user page")
    @WithUserDetails(value = "admin")
    void test_8() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/user")).andExpect(status().isOk()).andReturn();
        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(200);
    }


    /**
     * {@link WithUserDetails} default value = user
     */
    @Test
    @DisplayName("User has no access to the admin site")
    @WithUserDetails
    void test_9() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/admin")).andExpect(status().isForbidden()).andReturn();
        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(403);
    }

    /**
     * @throws Exception User Not Found if userDetails value not exist
     */

    @Test
    @DisplayName("User has no access to the student site")
    @WithUserDetails
    void test_10() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/student")).andExpect(status().isForbidden()).andReturn();
        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(403);
    }

    @Test
    @DisplayName("User has access to the user site")
    @WithUserDetails
    void test_11() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/user")).andExpect(status().isOk()).andReturn();
        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(200);
    }

    @Test
    @DisplayName("Student has no access to the admin site")
    @WithUserDetails(value = "student")
    void test_12() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/admin")).andExpect(status().isForbidden()).andReturn();
        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(403);
    }

    @Test
    @DisplayName("Student has access to his site")
    @WithUserDetails(value = "student")
    void test_13() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/student")).andExpect(status().isOk()).andReturn();
        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(200);
    }


}