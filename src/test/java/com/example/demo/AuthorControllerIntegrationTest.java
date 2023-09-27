package com.example.demo;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.springframework.test.util.AssertionErrors.assertEquals;

import java.time.LocalDate;
import java.util.Date;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthorControllerIntegrationTest {
  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  public void testPostAuthor() {
    Author author = new Author(1L, "firstName", "One", "Book One", new Date(), "2020-05-05",
        LocalDate.now(), 300, 9.5);

    ResponseEntity<String> responseEntity =
        restTemplate.postForEntity("/postAuthor", author, String.class);

    assertEquals("assertion", HttpStatus.OK, responseEntity.getStatusCode());
  }

  @Test
  public void whenGetAuthors_thenOk() {
    ResponseEntity<Author[]> responseEntity = restTemplate.getForEntity("/getAuthors", Author[].class);

    Author[] authors = responseEntity.getBody();

    assertNotNull(authors);

    assertTrue(authors.length > 0);
  }

  @Test
  public void whenGetAuthorById_thenOk() {
    ResponseEntity<Author> responseEntity = restTemplate.getForEntity("/getAuthorById", Author.class);
    Author author = responseEntity.getBody();
    assertNotNull(author);

  }

  @Test
  void whengetAuthorByPriceWherePriceIsHigherThanThreeHundred_thenOk() {
    ResponseEntity<Author[]> responseEntity = restTemplate.getForEntity("/getAuthorByPriceWherePriceIsHigherThanThreeHundred", Author[].class);
    Author[] author = responseEntity.getBody();
    assertNotNull(responseEntity);
    assertNotNull(author);
  }

  @Test
  void whengetAuthorByDiscountRate_thenOk() {
    ResponseEntity<Author[]> responseEntity = restTemplate.getForEntity("/getAuthorByDiscountRate", Author[].class);
    Author[] author = responseEntity.getBody();
    assertNotNull(responseEntity);
    assertNotNull(author);
  }

  @Test
  public void whenDeleteAuthorById_thenOk() {
    Long authorIdToDelete = 1L;

    ResponseEntity<String> responseEntity = restTemplate.exchange(
        "/deleteAuthorById/{authorIdToDelete}",
        HttpMethod.DELETE,
        null,
        String.class,
        authorIdToDelete
    );
    assertEquals("Delete", "Deleted Successfully", responseEntity.getBody());

  }

  @Test
  public void testPutAuthor() {
    Author updatedAuthor = new Author(1L, "firstName", "One", "Book One", new Date(), "2020-05-05",
        LocalDate.now(), 300, 9.5);

    updatedAuthor.setId(1L);
    updatedAuthor.setFirstName("UpdatedFirstName");
    updatedAuthor.setLastName("UpdatedLastName");

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<Author> requestEntity = new HttpEntity<>(updatedAuthor, headers);

    ResponseEntity<String> responseEntity = restTemplate.exchange(
        "/putAuthor",
        HttpMethod.PUT,
        requestEntity,
        String.class
    );

    assertEquals("PutMethod", updatedAuthor, requestEntity.getBody());

  }

//  @GetMapping("/getSum")
//  public Integer getSum() {
//    return authorRepository.findSum();
//  }

  @Test
  void whenGetSum_thenOk() {
    ResponseEntity<Integer> responseEntity = restTemplate.getForEntity("/getSum", Integer.class);
    assertNotNull(responseEntity.getBody());
  }

}
