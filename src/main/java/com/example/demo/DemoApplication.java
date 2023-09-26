package com.example.demo;

import java.time.LocalDate;
import java.util.Date;
import org.apache.geode.cache.GemFireCache;
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
                           AuthorRepository authorRepository) {
    return args -> {
      Customer jonDoe = new Customer(1L, "Jon Doe");

      System.out.println("----+++++------: John Doe: " + jonDoe);
      System.out.println("--------saving data---------");
      customerRepository.save(jonDoe);

      Author author = new Author(1L, "firstName", "One", "Book One", new Date(), "2020-05-05",
          LocalDate.now(), 300, 9.5);

      authorRepository.save(author);
    };
  }
}