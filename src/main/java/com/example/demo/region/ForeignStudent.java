package com.example.demo.region;

import org.springframework.data.gemfire.mapping.annotation.Indexed;
import org.springframework.data.gemfire.mapping.annotation.Region;

@Region("ForeignStudents")
public class ForeignStudent {
  Integer id;
  String Name;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return Name;
  }

  public void setName(String name) {
    Name = name;
  }

  public ForeignStudent(Integer id, String name) {
    this.id = id;
    Name = name;
  }
}
