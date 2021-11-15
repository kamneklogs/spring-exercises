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

import co.edu.icesi.tintegracion.model.hr.Employeedepartmenthistory;
import co.edu.icesi.tintegracion.services.interfaces.DepartmentService;
import co.edu.icesi.tintegracion.services.interfaces.EmployeeDepartmentHistoryService;
import co.edu.icesi.tintegracion.services.interfaces.EmployeeService;
import co.edu.icesi.tintegracion.services.interfaces.PersonService;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class DepartmentsHistoryController {

    private EmployeeDepartmentHistoryService employeeDepartmentHistoryService;

    private EmployeeService employeeService;
    private DepartmentService departmentService;

    @Autowired
    public DepartmentsHistoryController(EmployeeDepartmentHistoryService employeeDepartmentHistoryService,
            EmployeeService employeeService, DepartmentService departmentService) {
        this.employeeDepartmentHistoryService = employeeDepartmentHistoryService;
        this.employeeService = employeeService;
        this.departmentService = departmentService;
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
                log.info(bindingResult.getFieldErrors().get(0).getDefaultMessage()
                        + "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");

                return "department-history/addDepartmentH";
            }
            dHistory.setBusinessentityid(dHistory.getEmployee().getBusinessentityid());
            employeeDepartmentHistoryService.save(dHistory);

        }
        return "redirect:/departmentsHistory/";
    }
}