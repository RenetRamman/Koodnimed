package com.example.demo;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<Contact, Integer> {
  
  @Query(value = "select * from contact fetch first ?1 rows only", nativeQuery = true)
  ArrayList<Contact> findN(int n);

  @Query(value = "select * from contact offset ?2 rows fetch first ?1 rows only", nativeQuery = true)
  ArrayList<Contact> findNafterM(int n, int m);

  @Query(value = "select * from contact order by ?3 ?4 offset ?2 rows fetch first ?1 rows only", nativeQuery = true)
  ArrayList<Contact> findNafterMsorted(int n, int m, String by, String asc);

  // select * from contact offset 1 rows fetch first 10 rows only;
  
}
