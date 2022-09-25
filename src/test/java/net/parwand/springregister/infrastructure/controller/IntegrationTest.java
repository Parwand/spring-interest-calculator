package net.parwand.springregister.infrastructure.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.SecurityFilterChain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTest {

    @Autowired
    private TestRestTemplate template;

    @MockBean
    SecurityFilterChain filterChain;

    @LocalServerPort
    private int port;

    /**
     * TODO: Integration Tests
     * TODO: The tests are here defect
     */
    @Test
    public void givenAuthRequestOnPrivateService_shouldSucceedWith200() throws Exception {
        ResponseEntity<String> result = template.withBasicAuth("foo", "admin")
                .getForEntity("/admin", String.class);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @Test
    public void test(){
        String response = template
                .withBasicAuth("admin", "admin")
                .getForObject("http://localhost:" + port + "/admin", String.class);
        assertThat(response).contains("Login");
    }

}
