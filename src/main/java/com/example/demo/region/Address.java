package com.example.demo.region;

import org.springframework.data.gemfire.mapping.annotation.Region;

@Region("Address")
public class Address {
  private String Division;
  private String District;
  private String Upazila;
  private String Village;

  public Address(String division, String district, String upazila, String village) {
    Division = division;
    District = district;
    Upazila = upazila;
    Village = village;
  }

  public String getDivision() {
    return Division;
  }

  public void setDivision(String division) {
    Division = division;
  }

  public String getDistrict() {
    return District;
  }

  public void setDistrict(String district) {
    District = district;
  }

  public String getUpazila() {
    return Upazila;
  }

  public void setUpazila(String upazila) {
    Upazila = upazila;
  }

  public String getVillage() {
    return Village;
  }

  public void setVillage(String village) {
    Village = village;
  }
}
