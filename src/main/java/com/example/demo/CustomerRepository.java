package com.example.demo;

import org.springframework.data.gemfire.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
  Customer findByNameLike(String name);

//  @Query("SELECT * FROM /People p WHERE p.firstname = $1")
//  Collection<Person> findByFirstnameAnnotated(String firstname);
  @Query("Select * from /Customers c where c.id = 1")
  Customer findCustomerById();
}
