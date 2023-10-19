package com.example.demo.repository;

import com.example.demo.region.Teacher;
import org.springframework.data.gemfire.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends CrudRepository<Teacher, Long> {
  @Query("Select * from /Teacher where id <= 100")
  Iterable<Teacher> getTopHundredReacher();
}
