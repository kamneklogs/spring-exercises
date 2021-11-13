package co.edu.icesi.tintegracion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import co.edu.icesi.tintegracion.services.interfaces.EmployeePayHistoryService;

@Controller
public class PayHistoryController {

    private EmployeePayHistoryService employeePayHistoryService;

    @Autowired
    public PayHistoryController(EmployeePayHistoryService employeePayHistoryService) {
        this.employeePayHistoryService = employeePayHistoryService;
    }

    @GetMapping("/payment")
    public String payment(Model model) {
        model.addAttribute("payment", employeePayHistoryService.findAll());
        return "/payment/index";
    }

}
