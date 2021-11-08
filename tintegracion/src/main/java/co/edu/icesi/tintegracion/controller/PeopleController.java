package co.edu.icesi.tintegracion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import co.edu.icesi.tintegracion.model.person.Person;
import co.edu.icesi.tintegracion.services.interfaces.PersonService;

@Controller
public class PeopleController {

    private PersonService personService;

    @Autowired
    public PeopleController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/people/")
    public String index(Model model) {
        model.addAttribute("people", personService.findAll());

        return "people/index";
    }


    @GetMapping("/people/addPerson")
    public String addPerson(Model model) {
        model.addAttribute("person", new Person());
        return "people/addPerson";
    }


}
