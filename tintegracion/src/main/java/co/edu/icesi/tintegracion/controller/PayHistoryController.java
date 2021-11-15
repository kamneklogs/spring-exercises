package co.edu.icesi.tintegracion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.icesi.tintegracion.model.hr.Employeepayhistory;
import co.edu.icesi.tintegracion.services.interfaces.EmployeePayHistoryService;
import co.edu.icesi.tintegracion.services.interfaces.EmployeeService;
import co.edu.icesi.tintegracion.services.interfaces.PersonService;

@Controller
public class PayHistoryController {

    private EmployeePayHistoryService employeePayHistoryService;
    private EmployeeService employeeService;

    @Autowired
    public PayHistoryController(EmployeePayHistoryService employeePayHistoryService, EmployeeService employeeService) {
        this.employeePayHistoryService = employeePayHistoryService;
        this.employeeService = employeeService;
    }

    @GetMapping("/payment/")
    public String payment(Model model) {
        model.addAttribute("payments", employeePayHistoryService.findAll());
        return "/payment/index";
    }

    @GetMapping("/payment/addEmployeepayhistory")
    public String addEmployeepayhistory(Model model) {

        model.addAttribute("employeepayhistory", new Employeepayhistory());
        model.addAttribute("employees", employeeService.findAll());

        return "/payment/addPayhistory";
    }

    @PostMapping("/payment/addEmployeepayhistory")
    public String addEmployeepayhistory(@Validated @ModelAttribute Employeepayhistory employeepayhistory,
            BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) {
        if (!action.equals("cancel")) {
            if (bindingResult.hasErrors()) {
                model.addAttribute("people", employeeService.findAll());

                return "payment/addEmployeepayhistory";
            }
            employeePayHistoryService.save(employeepayhistory, employeepayhistory.getBusinessentityid());
        }
        return "redirect:/payment/";
    }

}
