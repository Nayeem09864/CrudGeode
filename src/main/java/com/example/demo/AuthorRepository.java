package com.example.demo;

import org.springframework.data.gemfire.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {
  @Query("Select * from /Authors a where a.id = 1")
  Author findAuthorById();

  @Query("Select * from /Authors a where a.price > 300")
  Iterable<Author> findAuthorByBookPriceGreaterThanThreeHundred();

  @Query("Select * from /Authors a where a.discountRate > 10")
  Iterable<Author> findAuthorByDiscountRate();
  @Query("select sum(price) from /Authors")
  Integer findSum();

}
