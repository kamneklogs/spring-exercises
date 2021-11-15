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

import co.edu.icesi.tintegracion.model.hr.Employee;
import co.edu.icesi.tintegracion.services.interfaces.EmployeeService;
import co.edu.icesi.tintegracion.services.interfaces.PersonService;

@Controller
public class EmployeesController {

    private EmployeeService employeeService;
    private PersonService personService;

    @Autowired
    public EmployeesController(EmployeeService employeeService, PersonService personService) {
        this.employeeService = employeeService;
        this.personService = personService;
    }

    @GetMapping("/employees/")
    public String index(Model model) {
        model.addAttribute("employees", employeeService.findAll());
        return "employees/index";
    }

    @GetMapping("/employees/addEmployee")
    public String addEmployee(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("people", personService.findAll());
        return "employees/addEmployee";
    }

    @PostMapping("/employees/addEmployee")
    public String addEmployee(@Validated @ModelAttribute Employee employee, BindingResult bindingResult, Model model,
            @RequestParam(value = "action", required = true) String action) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("employee", employee);
            model.addAttribute("people", personService.findAll());
            return "employees/addEmployee";
        }

        if (!action.equals("cancel"))
            employeeService.save(employee);

        return "redirect:/employees/";
    }
}
