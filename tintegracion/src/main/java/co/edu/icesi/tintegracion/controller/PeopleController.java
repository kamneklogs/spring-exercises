package co.edu.icesi.tintegracion.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @PostMapping("/people/addPerson")
    public String addPerson(@Validated @ModelAttribute Person person, BindingResult bindingResult, Model model,
            @RequestParam(value = "action", required = true) String action) {

        if (!action.equals("cancel"))

            if (bindingResult.hasErrors()) {
                model.addAttribute("person", person);

                return "/people/addPerson";
            }

        personService.save(person);
        return "redirect:/people/";
    }

    @GetMapping("/people/edit/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        Optional<Person> person = personService.findById(id);
        if (person == null)
            throw new IllegalArgumentException("Invalid person Id:" + id);
        model.addAttribute("person", person.get());

        return "people/update-person";
    }

    @PostMapping("/people/edit/{id}")
    public String updateUser(@PathVariable("id") int id, @RequestParam(value = "action", required = true) String action,
            Person person, Model model) {
        if (action != null && !action.equals("Cancel")) {
            personService.save(person);
            model.addAttribute("users", personService.findAll());
        }
        return "redirect:/people/";
    }
}
