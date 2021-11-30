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

import co.edu.icesi.tintegracion.model.hr.Employeedepartmenthistory;
import co.edu.icesi.tintegracion.services.interfaces.EmployeeDepartmentHistoryService;
import co.edu.icesi.tintegracion.services.interfaces.EmployeeService;

@Controller
public class DepartmentsHistoryController {

    private EmployeeDepartmentHistoryService employeeDepartmentHistoryService;

    private EmployeeService employeeService;

    @Autowired
    public DepartmentsHistoryController(EmployeeDepartmentHistoryService employeeDepartmentHistoryService,
            EmployeeService employeeService) {
        this.employeeDepartmentHistoryService = employeeDepartmentHistoryService;
        this.employeeService = employeeService;
    }

    @GetMapping("/departmentsHistory/")
    public String getDepartmentsHistory(Model model) {

        Iterable<Employeedepartmenthistory> t = employeeDepartmentHistoryService.findAll();
        t.iterator();
        model.addAttribute("dHistories", employeeDepartmentHistoryService.findAll());
        return "department-history/index";
    }

    @GetMapping("/departmentsHistory/addDepartmentHistory")
    public String addDepartmentsHistory(Model model) {
        model.addAttribute("dHistory", new Employeedepartmenthistory());
        model.addAttribute("employees", employeeService.findAll());
        return "department-history/addDepartmentH";
    }

    @PostMapping("/departmentsHistory/addDepartmentHistory")
    public String addDepartmentsHistory(@Validated @ModelAttribute Employeedepartmenthistory dHistory,
            BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) {

        if (!action.equals("Cancel")) {
            if (bindingResult.hasErrors()) {
                model.addAttribute("dHistory", dHistory);
                model.addAttribute("employees", employeeService.findAll());

                return "department-history/addDepartmentH";
            }
            dHistory.setBusinessentityid(dHistory.getEmployee().getBusinessentityid());
            employeeDepartmentHistoryService.save(dHistory);

        }
        return "redirect:/departmentsHistory/";
    }

    @GetMapping("/departmentsHistory/edit/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        Optional<Employeedepartmenthistory> dHistory = employeeDepartmentHistoryService.findById(id);
        if (dHistory == null)
            throw new IllegalArgumentException("Invalid person Id:" + id);
        model.addAttribute("dHistory", dHistory.get());
        model.addAttribute("employees", employeeService.findAll());
        return "department-history/update-departmentH";
    }

    @PostMapping("/departmentsHistory/edit/{id}")
    public String updateDepartmentH(@Validated @ModelAttribute Employeedepartmenthistory dHistory,
            BindingResult bindingResult, Model model, @PathVariable("id") int id,
            @RequestParam(value = "action", required = true) String action) {
        if (action != null && !action.equals("Cancel")) {

            if (bindingResult.hasErrors()) {
                model.addAttribute("dHistory", dHistory);
                return "departmentsHistory/edit/" + dHistory.getBusinessentityid();
            }
            dHistory.setBusinessentityid(id);
            employeeDepartmentHistoryService.save(dHistory);
        }
        return "redirect:/departmentsHistory/";
    }

    @GetMapping("/departmentsHistory/showDetails/{id}")
    public String showDetails(@PathVariable("id") int id, Model model) {
        model.addAttribute("dHistory", employeeDepartmentHistoryService.findById(id).get());
        return "department-history/show-details";
    }
}