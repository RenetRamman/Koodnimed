package com.example.demo;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<Contact, Integer> {

  /**
   * Kliendi poolel 10 kontakti kuvamiseks.
   * 
   * @param n Mitu kontakti maksimaalselt tagastada.
   * @param m Mitmendast kontaktist tagastamist alustada (Kasutatud kontaktide
   *          lehitsemisel)
   * @return n kontakti, alustades m kohal järgnevast kontaktist
   */
  @Query(value = "select * from contact offset ?2 rows fetch first ?1 rows only", nativeQuery = true)
  ArrayList<Contact> findNafterM(int n, int m);

  // See query töötab otse andmebaasis, aga siin ei saa seda sorteerima
  /**
   * Sama mis findNafterM() aga sorteerib tulemuse
   * 
   * @param n  Mitu kontakti maksimaalselt tagastada.
   * @param m  Mitmendast kontaktist tagastamist alustada (Kasutatud kontaktide
   *           lehitsemisel)
   * @param by Millise tulba järgi ja mis suunas sorteerida
   * @return by järgi sorteeritud n kontakti, alustades m kohal järgnevast
   *         kontaktist
   */
  @Query(value = "select * from contact order by ?1 offset ?2 rows fetch first ?3 rows only", nativeQuery = true)
  ArrayList<Contact> findNafterMsorted(int n, int m, String by);

  /**
   * Sama mis findNafterM() aga sorteeritud nime järgi kasvavalt
   * 
   * @param n Mitu kontakti maksimaalselt tagastada.
   * @param m Mitmendast kontaktist tagastamist alustada (Kasutatud kontaktide
   *          lehitsemisel)
   * @return nime järgi kasvavas järjekorras sorteeritud n kontakti, alustades m
   *         kohal järgnevast kontaktist
   */
  @Query(value = "select * from contact order by name asc offset ?2 rows fetch first ?1 rows only", nativeQuery = true)
  ArrayList<Contact> findNafterMbyNameAsc(int n, int m);

  // Sama, mis eelmine, aga sorteeritud nime järgi kahanevas järjekorras
  @Query(value = "select * from contact order by name desc offset ?2 rows fetch first ?1 rows only", nativeQuery = true)
  ArrayList<Contact> findNafterMbyNameDesc(int n, int m);

  // Sama, mis eelmine, aga sorteeritud kood-nime järgi kasvavas järjekorras
  @Query(value = "select * from contact order by codename asc offset ?2 rows fetch first ?1 rows only", nativeQuery = true)
  ArrayList<Contact> findNafterMbyCodenameAsc(int n, int m);

  // Sama, mis eelmine, aga sorteeritud kood-nime järgi kahanevas järjekorras
  @Query(value = "select * from contact order by codename desc offset ?2 rows fetch first ?1 rows only", nativeQuery = true)
  ArrayList<Contact> findNafterMbyCodenameDesc(int n, int m);

  // Sama, mis eelmine, aga sorteeritud telefoni järgi kasvavas järjekorras
  @Query(value = "select * from contact order by phone asc offset ?2 rows fetch first ?1 rows only", nativeQuery = true)
  ArrayList<Contact> findNafterMbyPhoneAsc(int n, int m);

  // Sama, mis eelmine, aga sorteeritud telefoni järgi kahanevas järjekorras
  @Query(value = "select * from contact order by phone desc offset ?2 rows fetch first ?1 rows only", nativeQuery = true)
  ArrayList<Contact> findNafterMbyPhoneDesc(int n, int m);

  /**
   * Kontaktide otsimine märksõna või -numbri järgi
   * 
   * @param search Otsingu märksõna, mille järgi andmebaasist kontakte otsitakse
   * @return Kontaktide list, milles iga kontakti nimes, koodnimes või
   *         telefoninumbris on otsingusõnale võrdne alamsõna (substring)
   */
  @Query(value = "select * from contact where name like %?1% or codename like %?1% or cast(phone as text) like %?1%", nativeQuery = true)
  ArrayList<Contact> searchContacts(String search);
}
