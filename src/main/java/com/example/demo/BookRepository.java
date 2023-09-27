package com.example.demo;

import org.springframework.data.gemfire.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
//  @Query("SELECT TOP 3 * FROM /Authors")
//  Iterable<Book> getTopThree();

}
