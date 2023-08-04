package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/demo") // URL alguses /demo
@CrossOrigin(origins = "http://localhost:8081") // Sellele APIle saab teha requeste aaressilt http://localhost:8081
public class MainController {
  @Autowired // contactRepository tegeleb andmete haldamisega
  private ContactRepository contactRepository;

  /**
   * Võtab vastu /add URLile tulnud POST päringu, parsib selle parameetrid ja
   * salvestab parameetritest tuleneva kontakti andmebaasi
   * 
   * @param name     Kontakti nimi URLi parameetrist
   * @param codename Kontakti koodnimi URLi parameetrist
   * @param phone    Kontakti telefoninumber URLi parameetrist
   * @return Sõne teatamaks, et uus kontakt on salvestatud
   */
  @PostMapping(path = "/add") // Ainult POST päringud /add URLil
  public @ResponseBody String addNewUser(
      @RequestParam(value = "nimi", defaultValue = "Anonüümne") String name,
      @RequestParam(value = "koodnimi", defaultValue = "_") String codename,
      @RequestParam(value = "telefon", defaultValue = "_") String phone) {

    // Võtame RequestParam käest andmed ja kasutame neid uue kontakti loomiseks.
    // Salvestame uue kontakti andmebaasi contactReopsitory kasutades.

    Contact n = new Contact();
    n.setName(name);
    n.setCodename(codename);
    n.setPhone(Integer.parseInt(phone));
    contactRepository.save(n);
    return "Saved";
  }

  /**
   * Võtab vastu /nafterm URLile saabunud GET päringu ja tagastab vastavalt
   * parameetritele sorteeritud kontaktid soovitud vahemikus
   * 
   * @param n  Mitu kontakti maksimaalselt tagastada.
   * @param m  Mitmendast kontaktist tagastamist alustada (Kasutatud kontaktide
   *           lehitsemisel)
   * @param by Mille järgi ja millises suunas kontaktid sorteeritakse
   * @return Sorteeritud list kontaktidega, mis jäävad vahemiku (m..m+n]
   */
  @GetMapping(path = "/nafterm")
  public @ResponseBody Iterable<Contact> getNafterMcontacts(
      @RequestParam(value = "n", defaultValue = "10") String n,
      @RequestParam(value = "m", defaultValue = "0") String m,
      @RequestParam(value = "by", defaultValue = "name_asc") String by) {
    List<Contact> c = new ArrayList<Contact>();
    // ContactRepository klassis ei saanud sql päring argumendina võetud
    // sorteerimiskriteeriumit kasutada
    // Seega on iga võimaliku sorteermis tulba ja suuna jaoks eraldi päring
    if (by.equals("name_asc")) {
      c = contactRepository.findNafterMbyNameAsc(Integer.parseInt(n), Integer.parseInt(m));
    } else if (by.equals("name_desc")) {
      c = contactRepository.findNafterMbyNameDesc(Integer.parseInt(n), Integer.parseInt(m));
    } else if (by.equals("codename_asc")) {
      c = contactRepository.findNafterMbyCodenameAsc(Integer.parseInt(n), Integer.parseInt(m));
    } else if (by.equals("codename_desc")) {
      c = contactRepository.findNafterMbyCodenameDesc(Integer.parseInt(n), Integer.parseInt(m));
    } else if (by.equals("phone_asc")) {
      c = contactRepository.findNafterMbyPhoneAsc(Integer.parseInt(n), Integer.parseInt(m));
    } else {
      c = contactRepository.findNafterMbyPhoneDesc(Integer.parseInt(n), Integer.parseInt(m));
    }
    return c;
  }

  /**
   * Võtab vastu /search URLile saabunud GET päringu ja tagastab need kontaktid,
   * mille nimi, koodnimi või telefoninumber sisaldab alamkirjet 'search'
   * parameetrist.
   * 
   * @param search Otsingusõna, mille järgi kontakte andmebaasist otsida
   * @return 'search' otsingsõna järgi otsides leitud kontaktidd
   */
  @GetMapping(path = "/search")
  public @ResponseBody Iterable<Contact> searchContacts(
      @RequestParam(value = "search", defaultValue = "") String search) {
    return contactRepository.searchContacts(search);
  }

}
