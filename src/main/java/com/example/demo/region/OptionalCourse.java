package com.example.demo.region;

import org.springframework.data.gemfire.mapping.annotation.Indexed;
import org.springframework.data.gemfire.mapping.annotation.Region;

@Region("OptionalCourse")
public class OptionalCourse {
  @Indexed
  private Integer CourseCode;
  private String CourseTitle;
  private Double Credit;

  public OptionalCourse(Integer courseCode, String courseTitle, Double credit) {
    CourseCode = courseCode;
    CourseTitle = courseTitle;
    Credit = credit;
  }

  public Integer getCourseCode() {
    return CourseCode;
  }

  public void setCourseCode(Integer courseCode) {
    CourseCode = courseCode;
  }

  public String getCourseTitle() {
    return CourseTitle;
  }

  public void setCourseTitle(String courseTitle) {
    CourseTitle = courseTitle;
  }

  public Double getCredit() {
    return Credit;
  }

  public void setCredit(Double credit) {
    Credit = credit;
  }
}
