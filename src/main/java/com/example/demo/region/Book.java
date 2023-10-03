package com.example.demo.region;

import org.springframework.data.annotation.Id;
import org.springframework.data.gemfire.mapping.annotation.Indexed;
import org.springframework.data.gemfire.mapping.annotation.Region;

@Region("Books")
public class Book {
  @Id
  private  Long id;
  @Indexed
  private String bookName;
  private String coAuthor;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getBookName() {
    return bookName;
  }

  public void setBookName(String bookName) {
    this.bookName = bookName;
  }

  public String getCoAuthor() {
    return coAuthor;
  }

  public void setCoAuthor(String coAuthor) {
    this.coAuthor = coAuthor;
  }

  public Book(Long id, String bookName, String coAuthor) {
    this.id = id;
    this.bookName = bookName;
    this.coAuthor = coAuthor;
  }
}
