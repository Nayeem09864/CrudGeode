package com.example.demo;

import static com.sun.corba.se.impl.util.RepositoryId.cache;

import org.apache.geode.cache.Cache;
import org.apache.geode.cache.query.FunctionDomainException;
import org.apache.geode.cache.query.NameResolutionException;
import org.apache.geode.cache.query.Query;
import org.apache.geode.cache.query.QueryInvocationTargetException;
import org.apache.geode.cache.query.QueryService;
import org.apache.geode.cache.query.SelectResults;
import org.apache.geode.cache.query.TypeMismatchException;
import org.apache.geode.internal.cache.xmlcache.CacheCreation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class AuthorController {
  @Autowired
  AuthorRepository authorRepository;

  @PostMapping(path = "/postAuthor")
  public ResponseEntity<String> postCustomer(@RequestBody Author author) {
    authorRepository.save(author);
//    System.out.println("----===-+++++ author : " + author);
//    String queryString = "SELECT * FROM /Authors";
//    System.out.println("-+-+-+-+ queryString: -+-+-+-+-"+queryString);
//    Cache cache1 = new CacheCreation();
//    // Get QueryService from Cache.
//    QueryService queryService = cache1.getQueryService();
//
//    // Create the Query Object.
//    Query query = queryService.newQuery(queryString);
//
//    // Execute Query locally. Returns results set.
//    SelectResults results = null;
//    try {
//      results = (SelectResults) query.execute();
//    } catch (FunctionDomainException e) {
//      throw new RuntimeException(e);
//    } catch (TypeMismatchException e) {
//      throw new RuntimeException(e);
//    } catch (NameResolutionException e) {
//      throw new RuntimeException(e);
//    } catch (QueryInvocationTargetException e) {
//      throw new RuntimeException(e);
//    }
//    System.out.println("+-+-+-+- results: +-+-+-+ \n"+results);
//    // Find the Size of the ResultSet.
//    int size = results.size();
//    System.out.println("+-+-++-Size:+-+-+-+ "+size);
    return new ResponseEntity<>("OK", HttpStatus.OK);
  }

  @GetMapping("/getAuthors")
  public Iterable<Author> getAuthors() {
    Iterable<Author> authors;
    authors = authorRepository.findAll();

//    String queryString = "SELECT * FROM /Authors";
//    System.out.println("-+-+-+-+ queryString: -+-+-+-+-\n"+queryString);
//    Cache cache1 = new CacheCreation();
//    // Get QueryService from Cache.
//    QueryService queryService = cache1.getQueryService();
//
//    // Create the Query Object.
//    Query query = queryService.newQuery(queryString);
//
//    // Execute Query locally. Returns results set.
//    SelectResults results = null;
//    try {
//      results = (SelectResults) query.execute();
//    } catch (FunctionDomainException e) {
//      throw new RuntimeException(e);
//    } catch (TypeMismatchException e) {
//      throw new RuntimeException(e);
//    } catch (NameResolutionException e) {
//      throw new RuntimeException(e);
//    } catch (QueryInvocationTargetException e) {
//      throw new RuntimeException(e);
//    }
//    System.out.println("+-+-+-+- results: +-+-+-+ \n"+results);
//    // Find the Size of the ResultSet.
//    int size = results.size();
//    System.out.println("+-+-++-Size:+-+-+-+ "+size);
    return authors;
  }

  @GetMapping("/getAuthorById")
  public Author getAuthorById() {
    Author author = authorRepository.findAuthorById();
    return author;
  }

  /***
   * Get the authors where the price is greater than 300
   * @return
   */
  @GetMapping("/getAuthorByPriceWherePriceIsHigherThanThreeHundred")
  public Iterable<Author> getAuthorByPriceWherePriceIsHigherThanThreeHundred() {
    Iterable<Author> authorIterable= authorRepository.findAuthorByBookPriceGreaterThanThreeHundred();
    return authorIterable;
  }

  @GetMapping("/getAuthorByDiscountRate")
  public Iterable<Author> getAuthorByDiscountRate() {
    Iterable<Author> authorIterable= authorRepository.findAuthorByDiscountRate();
    return authorIterable;
  }

  @DeleteMapping("/deleteAuthorById/{id}")
  public String deleteCustomer(@PathVariable Long id) {
    if (this.authorRepository.findById(id) != null) {
      authorRepository.deleteById((id));
      return "Deteted Successfully";
    } else {
      return "Error";
    }
  }

  @PutMapping("/putAuthor")
  public String putAuthor(@RequestBody Author author) {
    System.out.println("----===-+++++ author : " + author.getLastName());
    Long id = author.getId();
    boolean isPresent = authorRepository.existsById(id);
    if (isPresent == false) {
      return "Id not exists";
    }
    authorRepository.deleteById(id);
    authorRepository.save(author);
    return "Updated Successfully";
  }
}
