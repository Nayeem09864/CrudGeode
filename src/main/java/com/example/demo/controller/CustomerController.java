package com.example.demo.controller;

import com.example.demo.repository.CustomerRepository;
import com.example.demo.region.Customer;
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
public class CustomerController {
  @Autowired
  private CustomerRepository customerRepository;

  @PostMapping(path = "/postCustomer")
  public ResponseEntity<String> postCustomer(@RequestBody Customer customer) throws Exception {
    customerRepository.save(customer);

    System.out.println("----===-+++++ customer : " + customer);

    return new ResponseEntity<>("OK", HttpStatus.OK);
  }

//  @GetMapping("/getCustomerById")
//  public ResponseEntity<Customer> getCustomerById(@RequestParam("id") String id) {
//    Optional<Customer> customer = customerRepository.findById(Long.parseLong(id));
//    return new ResponseEntity<>(customer.isPresent() ? customer.get() : null, HttpStatus.OK);
//  }

  @GetMapping("/getCustomer")
  public Iterable<Customer> getCustomer() {
    Iterable<Customer> customerList;
    customerList = customerRepository.findAll();
    if (customerList != null) {
      return customerList;
    } else {
      return null;
    }
  }

  @GetMapping("/getCustomerById/{id}")
  public Customer getCustomer(@PathVariable Long id) {
    Customer customer;
    customer = customerRepository.findCustomerById();
    return customer;
  }

  @DeleteMapping("/deleteCustomerById/{id}")
  public String deleteCustomer(@PathVariable Long id) {
    if (this.customerRepository.findById(id) != null) {
      customerRepository.deleteById((id));
      return "Deteted Successfully";
    } else {
      return "Error";
    }
  }

  @PutMapping("/putCustomer")
  public String putCustomer(@RequestBody Customer customer) {
    System.out.println("----===-+++++ customer : " + customer.getName());
    Long id = customer.getId();
    boolean isPresent = customerRepository.existsById(id);
    if (isPresent == false) {
      return "Id not exists";
    }
    customerRepository.deleteById(id);
    customerRepository.save(customer);
    return "Updated Successfully";
  }
}
