package net.parwand.springregister.infrastructure.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTest {

    @Autowired
    private TestRestTemplate template;

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
        System.out.println("Status of Http Response : "+ result.getStatusCode());
        System.out.println("Status value of Http Response (html site): "+ result.getStatusCodeValue());
        System.out.println("Body of Http Response (html site): "+ result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @Test
    public void test(){
        System.out.println("Local server Port: " + port);
        String response = template
                .withBasicAuth("admin", "admin")
                .getForObject("http://localhost:" + port + "/admin", String.class);
        assertThat(response).contains("Login");
    }

}
