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

        if (!action.equals("Cancel")) {
            if (bindingResult.hasErrors()) {
                model.addAttribute("employee", employee);
                model.addAttribute("people", personService.findAll());
                return "employees/addEmployee";
            }
            employeeService.save(employee);
        }
        return "redirect:/employees/";
    }

    @GetMapping("/employees/edit/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        Employee employee = employeeService.findById(id);
        if (employee == null)
            throw new IllegalArgumentException("Invalid person Id:" + id);
        model.addAttribute("people", personService.findAll());
        model.addAttribute("employee", employee);

        return "employees/update-employee";
    }

    @PostMapping("/employees/edit/{id}")
    public String updateEmployee(@Validated @ModelAttribute Employee employee, BindingResult bindingResult, Model model,
            @PathVariable("id") int id, @RequestParam(value = "action", required = true) String action) {
        if (action != null && !action.equals("Cancel")) {

            if (bindingResult.hasErrors()) {
                model.addAttribute("employee", employee);
                model.addAttribute("people", personService.findAll());
                return "employees/edit/" + employee.getBusinessentityid();
            }
            employeeService.edit(employee, employee.getBusinessentityid(), employee.getBusinessentityid());
        }
        return "redirect:/employees/";
    }

    @GetMapping("/employees/showDetails/{id}")
    public String showDetails(@PathVariable("id") int id, Model model) {
        model.addAttribute("employee", employeeService.findById(id));
        return "employees/show-details";
    }
}
