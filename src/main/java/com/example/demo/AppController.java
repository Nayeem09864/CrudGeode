package com.example.demo;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class AppController {
  @Autowired
  private CustomerRepository customerRepository;

//  @PostMapping(path = "/customer" )
  @PostMapping(path = "/postCustomer")
  public ResponseEntity<String> addCustomer(@RequestBody Customer customer) throws Exception {
    customerRepository.save(customer);
    System.out.println("----===-+++++ customer : "+customer);
    return new ResponseEntity<>("OK", HttpStatus.OK);
  }

  @GetMapping("/getCustomer")
  public ResponseEntity<Customer> getCustomer(@RequestParam("id") String id) {
    Optional<Customer> customer = customerRepository.findById(Long.parseLong(id));
    return new ResponseEntity<>(customer.isPresent() ? customer.get() : null, HttpStatus.OK);
  }

  @DeleteMapping("/deleteCustomer")
  public void deleteCustomer(@RequestParam("id") String id) {
    if(this.customerRepository.findById(Long.parseLong(id))!=null) {
      customerRepository.deleteById(Long.parseLong(id));
    }
    else {
      System.out.println("Id not found+++++----");
    }

  }

    @PutMapping("/putCustomer")
    public ResponseEntity<String> putCustomer(@RequestBody Customer customer) throws Exception {
      System.out.println("----===-+++++ customer : "+customer);
      return new ResponseEntity<>("OK", HttpStatus.OK);
    }

  //    @PutMapping("/{id}")
//    public Book updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
//        return bookService.updateBook(id,updatedBook);
//    }

//  @GetMapping(path = "/getCustomer")
//  public ResponseEntity<Customer> (@RequestParam("id") Long id) throws Exception {
//
//  }

}
