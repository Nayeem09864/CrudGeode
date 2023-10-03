package com.example.demo.repository;

import com.example.demo.region.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
//  @Query("SELECT TOP 3 * FROM /Authors")
//  Iterable<Book> getTopThree();

}
