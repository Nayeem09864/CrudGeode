package com.example.demo.region;

import org.springframework.data.gemfire.mapping.annotation.Indexed;
import org.springframework.data.gemfire.mapping.annotation.Region;

@Region("SalaryStatement")
public class SalaryStatement {
  @Indexed
  private Integer basicSalary;
  private Integer houseRent;
  private Integer medicalFee;
  private Integer educationFee;
  private Integer washingFee;
  private Integer incomeTax;

  public SalaryStatement(Integer basicSalary, Integer houseRent, Integer medicalFee,
                         Integer educationFee, Integer washingFee, Integer incomeTax) {
    this.basicSalary = basicSalary;
    this.houseRent = houseRent;
    this.medicalFee = medicalFee;
    this.educationFee = educationFee;
    this.washingFee = washingFee;
    this.incomeTax = incomeTax;
  }

  public Integer getBasicSalary() {
    return basicSalary;
  }

  public void setBasicSalary(Integer basicSalary) {
    this.basicSalary = basicSalary;
  }

  public Integer getHouseRent() {
    return houseRent;
  }

  public void setHouseRent(Integer houseRent) {
    this.houseRent = houseRent;
  }

  public Integer getMedicalFee() {
    return medicalFee;
  }

  public void setMedicalFee(Integer medicalFee) {
    this.medicalFee = medicalFee;
  }

  public Integer getEducationFee() {
    return educationFee;
  }

  public void setEducationFee(Integer educationFee) {
    this.educationFee = educationFee;
  }

  public Integer getWashingFee() {
    return washingFee;
  }

  public void setWashingFee(Integer washingFee) {
    this.washingFee = washingFee;
  }

  public Integer getIncomeTax() {
    return incomeTax;
  }

  public void setIncomeTax(Integer incomeTax) {
    this.incomeTax = incomeTax;
  }
}
