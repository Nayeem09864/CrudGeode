package com.example.demo.region;

import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.data.annotation.Id;
import org.springframework.data.gemfire.mapping.annotation.Region;

@Region("Teacher")
public class Teacher {
  @Id
  private Long id;
  private String firstName;
  private  String lastName;
  Address addressOfTeacher;
  BasicCourse basicCourse;
  OptionalCourse optionalCourse;
  SalaryStatement salaryStatement;

  public Teacher(Long id, String firstName, String lastName, Address addressOfTeacher,
                 BasicCourse basicCourse, OptionalCourse optionalCourse,
                 SalaryStatement salaryStatement) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.addressOfTeacher = addressOfTeacher;
    this.basicCourse = basicCourse;
    this.optionalCourse = optionalCourse;
    this.salaryStatement = salaryStatement;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Address getAddressOfTeacher() {
    return addressOfTeacher;
  }

  public void setAddressOfTeacher(Address addressOfTeacher) {
    this.addressOfTeacher = addressOfTeacher;
  }

  public BasicCourse getBasicCourse() {
    return basicCourse;
  }

  public void setBasicCourse(BasicCourse basicCourse) {
    this.basicCourse = basicCourse;
  }

  public OptionalCourse getOptionalCourse() {
    return optionalCourse;
  }

  public void setOptionalCourse(OptionalCourse optionalCourse) {
    this.optionalCourse = optionalCourse;
  }

  public SalaryStatement getSalaryStatement() {
    return salaryStatement;
  }

  public void setSalaryStatement(SalaryStatement salaryStatement) {
    this.salaryStatement = salaryStatement;
  }
}
