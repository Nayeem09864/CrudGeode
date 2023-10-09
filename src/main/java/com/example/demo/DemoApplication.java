package com.example.demo;

import com.example.demo.region.Author;
import com.example.demo.region.Book;
import com.example.demo.region.Customer;
import com.example.demo.region.ForeignStudent;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.CustomerRepository;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import org.apache.geode.cache.CacheTransactionManager;
import org.apache.geode.cache.CommitConflictException;
import org.apache.geode.cache.GemFireCache;
import org.apache.geode.cache.execute.FunctionContext;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.gemfire.config.annotation.EnableEntityDefinedRegions;
import org.springframework.geode.config.annotation.EnableClusterAware;
import org.springframework.geode.config.annotation.UseMemberName;

@SpringBootApplication
@EnableClusterAware
@EnableEntityDefinedRegions(basePackageClasses = Customer.class)
@UseMemberName("DemoApplication")
public class DemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

  @Bean
  ApplicationRunner runner(GemFireCache cache, CustomerRepository customerRepository,
                           AuthorRepository authorRepository, BookRepository bookRepository) {
    return args -> {
      Customer jonDoe = new Customer(1L, "Jon Doe");

      customerRepository.save(jonDoe);

      Author author = new Author(1L, "firstName", "One", "Book One", new Date(), "2020-05-05",
          LocalDate.now(), 300, 9.5);

      authorRepository.save(author);
      author = new Author(2L, "firstName", "Two", "Book Two", new Date(), "2020-15-02",
          LocalDate.now(), 320, 8.5);

      authorRepository.save(author);

      author = new Author(3L, "firstName", "Three", "Book Three", new Date(), "2020-15-03",
          LocalDate.now(), 323, 8.3);

      authorRepository.save(author);

      Book book = new Book(1L,"Book One", "Coauthor one");
      bookRepository.save(book);


      CacheTransactionManager txManager =
          cache.getCacheTransactionManager();

      try {
        txManager.begin();
        author = new Author(5L, "firstName", "Five", "Book Five", new Date(), "2020-15-03",
            LocalDate.now(), 340, 11.5);

        authorRepository.save(author);

        Book book2 = new Book(2L, "Book Two", "Coauthor Two");
        bookRepository.save(book2);

        Book book3 = new Book(3L, "Book Three", "Coauthor Three");
        bookRepository.save(book3);

        txManager.commit();
      } catch (CommitConflictException conflict) {
        System.out.println("Transaction Failed");
      } finally {
        System.out.println("In finally block");
        if (txManager.exists()) {
          System.out.println("txManager exists");
          txManager.rollback();
        }
      }
    };
  }
}