package com.example.demo;

import java.time.LocalDate;
import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.gemfire.mapping.annotation.Region;

@Region("Authors")
public class Author {
  @Id
  private Long id;

  private String firstName;
  private  String lastName;
  private String bookName;
  private Date publishingDate;
  private String secondEdition;

  private LocalDate localDate;

  private Integer price;
  private Double discountRate;

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

  public String getBookName() {
    return bookName;
  }

  public void setBookName(String bookName) {
    this.bookName = bookName;
  }

  public Date getPublishingDate() {
    return publishingDate;
  }

  public void setPublishingDate(Date publishingDate) {
    this.publishingDate = publishingDate;
  }

  public String getSecondEdition() {
    return secondEdition;
  }

  public void setSecondEdition(String secondEdition) {
    this.secondEdition = secondEdition;
  }

  public LocalDate getLocalDate() {
    return localDate;
  }

  public void setLocalDate(LocalDate localDate) {
    this.localDate = localDate;
  }

  public Integer getPrice() {
    return price;
  }

  public void setPrice(Integer price) {
    this.price = price;
  }

  public Double getDiscountRate() {
    return discountRate;
  }

  public void setDiscountRate(Double discountRate) {
    this.discountRate = discountRate;
  }

  public Author(Long id, String firstName, String lastName, String bookName, Date publishingDate,
                String secondEdition, LocalDate localDate, Integer price, Double discountRate) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.bookName = bookName;
    this.publishingDate = publishingDate;
    this.secondEdition = secondEdition;
    this.localDate = localDate;
    this.price = price;
    this.discountRate = discountRate;
  }
}
