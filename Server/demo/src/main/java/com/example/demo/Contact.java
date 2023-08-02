package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Contact {
  @Id
  @GeneratedValue
  int id;
  String name;
  String codename;
  int phone;


  public long getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCodename() {
    return this.codename;
  }

  public void setCodename(String codename) {
    this.codename = codename;
  }

  public int getPhone() {
    return this.phone;
  }

  public void setPhone(int phone) {
    this.phone = phone;
  }

}
