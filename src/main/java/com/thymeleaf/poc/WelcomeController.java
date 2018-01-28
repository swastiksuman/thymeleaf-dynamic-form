package com.thymeleaf.poc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WelcomeController {
	private Person person;

	public WelcomeController() {
		person = new Person();
		person.setFirstName("Soumya");
		person.setLastName("Parida");
	}

	@GetMapping("/home")
	public String welcome(Model model) {
		model.addAttribute("person", person);
		return "welcome";
	}

	@PostMapping("/saveUpdateData")
	public String displayName(@ModelAttribute Person person, Model model) {
		this.person = person;
		model.addAttribute("savedPerson", this.person);
		return "result";
	}

	@PostMapping("/addRowData")
	public String addRowData(@ModelAttribute Person person, Model model) {
		this.person = person;
		System.out.println(person.getPhones());
		if (person.getPhones() == null) {
			Phone phone = new Phone();
			List<Phone> phones = new ArrayList<Phone>();
			phones.add(new Phone());
			person.setPhones(phones);
		} else {
			this.person.getPhones().add(new Phone());
		}
		return "welcome";
	}
}
