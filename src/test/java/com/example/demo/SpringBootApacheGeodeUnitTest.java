package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Date;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
public class SpringBootApacheGeodeUnitTest{

  @Autowired
  AuthorRepository authorRepository;

  @Test
  public void whenfindAuthorById_thenOk() {
    Author authorExpected = new Author(1L, "firstName", "One", "Book One", new Date(), "2020-05-05",
        LocalDate.now(), 300, 9.5);

    assertThat(this.authorRepository.save(authorExpected)).isNotNull();

    Author authorActual = authorRepository.findAuthorById();

    assertThat(authorActual).isNotNull();
    Assertions.assertEquals(authorExpected.getBookName(), authorActual.getBookName());

  }

}
