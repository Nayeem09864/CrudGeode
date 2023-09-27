package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class BookController {
  @Autowired
  BookRepository bookRepository;

  @PostMapping(path = "/postBook")
  public ResponseEntity<String> postBook(@RequestBody Book book) {
    bookRepository.save(book);
    System.out.println("-----+-+-+Post Book----+-+-+-+-: "+book.getBookName());
    return new ResponseEntity<>("OK", HttpStatus.OK);
  }

  @GetMapping("/getBooks")
  public Iterable<Book> getBooks() {
    Iterable<Book> books;
    books = bookRepository.findAll();
    return books;
  }

  @DeleteMapping("/deleteBookById/{id}")
  public String deleteBook(@PathVariable Long id) {
    if (this.bookRepository.findById(id) != null) {
      bookRepository.deleteById((id));
      return "Deteted Successfully";
    } else {
      return "Error";
    }
  }

}
