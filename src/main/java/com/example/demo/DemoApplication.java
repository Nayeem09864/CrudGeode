package com.example.demo;

import com.example.demo.region.Address;
import com.example.demo.region.Author;
import com.example.demo.region.BasicCourse;
import com.example.demo.region.Book;
import com.example.demo.region.Customer;
import com.example.demo.region.ForeignStudent;
import com.example.demo.region.OptionalCourse;
import com.example.demo.region.SalaryStatement;
import com.example.demo.region.Teacher;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.TeacherRepository;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javax.swing.text.html.parser.Parser;
import org.apache.geode.cache.CacheTransactionManager;
import org.apache.geode.cache.CommitConflictException;
import org.apache.geode.cache.GemFireCache;
import org.apache.geode.cache.execute.FunctionContext;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.gemfire.GemfireTemplate;
import org.springframework.data.gemfire.config.annotation.EnableEntityDefinedRegions;
import org.springframework.geode.config.annotation.EnableClusterAware;
import org.springframework.geode.config.annotation.UseMemberName;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Configuration
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
                           AuthorRepository authorRepository, BookRepository bookRepository,
                           TeacherRepository teacherRepository) {
    return args -> {
      Customer customer = new Customer(1L, "CustomerOne");

      customerRepository.save(customer);

      customer = new Customer(2L, "CustomerTwo");
      customerRepository.save(customer);

      customer = new Customer(3L, "CustomerThree");
      customerRepository.save(customer);

      Author author = new Author(1L, "firstName", "One", "Book One", new Date(), "2020-05-05",
          LocalDate.now(), 300, 9.5);

      authorRepository.save(author);
      author = new Author(2L, "firstName", "Two", "Book Two", new Date(), "2020-15-02",
          LocalDate.now(), 320, 8.5);

      authorRepository.save(author);

      author = new Author(3L, "firstName", "Three", "Book Three", new Date(), "2020-15-03",
          LocalDate.now(), 323, 8.3);

      authorRepository.save(author);

      Book book = new Book(1L, "Book One", "Coauthor one");
      bookRepository.save(book);

      /***
       * Transaction
       */

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

        /***
         * For Teacher
         */
        Address address = new Address("DivisionOne", "DistrictOne", "UpazilaOne", "villageOne");
        BasicCourse basicCourse = new BasicCourse(1101, "CourseTitleOne", 19.5);
        OptionalCourse optionalCourse = new OptionalCourse(1102, "OptionalCourseOne", 21.5);
        SalaryStatement salaryStatement = new SalaryStatement(18000, 10000, 500, 500, 500, 250);
        Teacher teacher =
            new Teacher(1L, "FirstOne", "LastOne", address, basicCourse, optionalCourse,
                salaryStatement);
        teacherRepository.save(teacher);

        for (Long i = 0L; i < 10; i++) {
          String division = "Division" + (i+2);
          String district = "District" + (i+2);
          String upazilla = "Upazilla" + (i+2);
          String village = "Village" + (i+2);
          Address address1 = new Address(division, district, upazilla, village);
          String courseTitle = "CourseTitle" + (i+2);
          BasicCourse basicCourse1 = new BasicCourse(1101, courseTitle, 19.5);
          String optionalCourseTitle = "OptionalCourseTitle" + (i+2);
          OptionalCourse optionalCourse1 = new OptionalCourse(1102, optionalCourseTitle, 21.5);
          SalaryStatement salaryStatement1 = new SalaryStatement(18000, 10000, 500, 500, 500, 250);
          String firstName = "FirstName" + (i + 2);
          String lastName = "LastName" + (i + 2);
          Teacher teacher1 =
              new Teacher(i+2, firstName, lastName, address1, basicCourse1, optionalCourse1,
                  salaryStatement1);
          teacherRepository.save(teacher1);
        }

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