package co.edu.icesi.tintegracion.controller;

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

import co.edu.icesi.tintegracion.model.hr.Employeepayhistory;
import co.edu.icesi.tintegracion.services.interfaces.EmployeePayHistoryService;
import co.edu.icesi.tintegracion.services.interfaces.EmployeeService;

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
        if (!action.equals("Cancel")) {
            if (bindingResult.hasErrors()) {
                model.addAttribute("people", employeeService.findAll());

                return "payment/addEmployeepayhistory";
            }
            employeePayHistoryService.save(employeepayhistory, employeepayhistory.getBusinessentityid());
        }
        return "redirect:/payment/";
    }

    @GetMapping("/payment/edit/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        Employeepayhistory pHistory = employeePayHistoryService.findById(id);
        if (pHistory == null)
            throw new IllegalArgumentException("Invalid person Id:" + id);
        model.addAttribute("employees", employeeService.findAll());
        model.addAttribute("pHistory", pHistory);

        return "payment/update-Payhistory";
    }

    @PostMapping("/payment/edit/{id}")
    public String updatePayHistory(@Validated @ModelAttribute Employeepayhistory pHistory, BindingResult bindingResult,
            Model model, @PathVariable("id") int id, @RequestParam(value = "action", required = true) String action) {
        if (action != null && !action.equals("Cancel")) {

            if (bindingResult.hasErrors()) {
                model.addAttribute("pHistory", pHistory);
                model.addAttribute("employees", employeeService.findAll());
                return "employees/edit/" + pHistory.getBusinessentityid();
            }
            employeePayHistoryService.save(pHistory, pHistory.getBusinessentityid());
        }
        return "redirect:/payment/";
    }

    @GetMapping("/payment/showDetails/{id}")
    public String showDetails(@PathVariable("id") int id, Model model) {
        model.addAttribute("pHistory", employeePayHistoryService.findById(id));
        return "payment/show-details";
    }

}
