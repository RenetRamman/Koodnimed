package com.example.demo;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<Contact, Integer> {
  
  @Query(value = "select * from contact fetch first ?1 rows only", nativeQuery = true)
  ArrayList<Contact> findN(int n);

  @Query(value = "select * from contact offset ?2 rows fetch first ?1 rows only", nativeQuery = true)
  ArrayList<Contact> findNafterM(int n, int m);

  // See query töötab otse andmebaasis, aga siin ei sorteeri
  @Query(value = "select * from contact order by ?1 offset ?2 rows fetch first ?3 rows only", nativeQuery = true)
  ArrayList<Contact> findNafterMsorted(int n, int m, String by);



  // select * from contact order by name asc offset 0 rows fetch first 2 rows only;
  @Query(value = "select * from contact order by name asc offset ?2 rows fetch first ?1 rows only", nativeQuery = true)
  ArrayList<Contact> findNafterMbyNameAsc(int n, int m);

  @Query(value = "select * from contact order by name desc offset ?2 rows fetch first ?1 rows only", nativeQuery = true)
  ArrayList<Contact> findNafterMbyNameDesc(int n, int m);
  
  @Query(value = "select * from contact order by codename asc offset ?2 rows fetch first ?1 rows only", nativeQuery = true)
  ArrayList<Contact> findNafterMbyCodenameAsc(int n, int m);

  @Query(value = "select * from contact order by codename desc offset ?2 rows fetch first ?1 rows only", nativeQuery = true)
  ArrayList<Contact> findNafterMbyCodenameDesc(int n, int m);

  @Query(value = "select * from contact order by phone asc offset ?2 rows fetch first ?1 rows only", nativeQuery = true)
  ArrayList<Contact> findNafterMbyPhoneAsc(int n, int m);

  @Query(value = "select * from contact order by phone desc offset ?2 rows fetch first ?1 rows only", nativeQuery = true)
  ArrayList<Contact> findNafterMbyPhoneDesc(int n, int m);
}
