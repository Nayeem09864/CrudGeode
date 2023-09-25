package com.example.demo;

import org.springframework.data.annotation.Id;
import org.springframework.data.gemfire.mapping.annotation.Region;

@Region("Customers")
public class Customer {
  @Id
  private Long id;

  private String name;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Customer() {
  }

  public Customer(Long id, String name) {
    this.id = id;
    this.name = name;
  }


}
