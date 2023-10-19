package com.example.demo.controller;

import com.example.demo.region.Author;
import com.example.demo.region.Teacher;
import com.example.demo.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class TeacherController {
  @Autowired
  TeacherRepository teacherRepository;
  @GetMapping("/getTeachers")
  public Iterable<Teacher> getTeachers() {
    Iterable<Teacher> teachers;
    teachers = teacherRepository.findAll();
    return teachers;
  }

  @GetMapping("/getTopHundredTeachers")
  public Iterable<Teacher> getTopHundredTeachers() {
    Iterable<Teacher> teachers;
    teachers = teacherRepository.getTopHundredReacher();
    return teachers;
  }
}
