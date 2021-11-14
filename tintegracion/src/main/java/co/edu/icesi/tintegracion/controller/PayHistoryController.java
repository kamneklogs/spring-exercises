package co.edu.icesi.tintegracion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.icesi.tintegracion.model.hr.Employeepayhistory;
import co.edu.icesi.tintegracion.services.interfaces.EmployeePayHistoryService;
import co.edu.icesi.tintegracion.services.interfaces.EmployeeService;
import co.edu.icesi.tintegracion.services.interfaces.PersonService;

@Controller
public class PayHistoryController {

    private EmployeePayHistoryService employeePayHistoryService;
    private PersonService personService;

    @Autowired
    public PayHistoryController(EmployeePayHistoryService employeePayHistoryService, PersonService personService) {
        this.employeePayHistoryService = employeePayHistoryService;
        this.personService = personService;
    }

    @GetMapping("/payment/")
    public String payment(Model model) {
        model.addAttribute("payments", employeePayHistoryService.findAll());
        return "/payment/index";
    }

    @GetMapping("/payment/addEmployeepayhistory")
    public String addEmployeepayhistory(Model model) {

        model.addAttribute("employeepayhistory", new Employeepayhistory());
        model.addAttribute("people", personService.findAll());

        return "/payment/addPayhistory";
    }

    @PostMapping("/payment/addEmployeepayhistory")
    public String addEmployeepayhistory(Employeepayhistory employeepayhistory, Model model,
            @RequestParam(value = "action", required = true) String action) {
        if (!action.equals("cancel")) {

            employeePayHistoryService.save(employeepayhistory, employeepayhistory.getBusinessentityid());
        }
        return "redirect:/payment/";
    }

}
