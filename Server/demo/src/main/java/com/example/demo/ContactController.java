package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {

	@GetMapping("/kontakt")
	public Contact greeting(
    @RequestParam(value = "nimi", defaultValue = "World") String name,
    @RequestParam(value = "koodnimi", defaultValue = "World") String codename,
    @RequestParam(value = "telefon", defaultValue = "123") String phone) {
      Contact contact = new Contact();
      contact.name = name;
      contact.codename = codename;
      contact.phone = Integer.parseInt(phone);
      return contact;
	}
}