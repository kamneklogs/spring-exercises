package co.edu.icesi.tintegracion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import co.edu.icesi.tintegracion.model.hr.Employeedepartmenthistory;
import co.edu.icesi.tintegracion.services.interfaces.DepartmentService;
import co.edu.icesi.tintegracion.services.interfaces.EmployeeDepartmentHistoryService;
import co.edu.icesi.tintegracion.services.interfaces.EmployeeService;
import co.edu.icesi.tintegracion.services.interfaces.PersonService;

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
        model.addAttribute("dHistories", employeeDepartmentHistoryService.findAll());
        return "department-history/index";
    }

    @GetMapping("/departmentsHistory/addDepartmentHistory")
    public String addDepartmentsHistory(Model model) {
        model.addAttribute("dHistory", new Employeedepartmenthistory());
        return "department-history/addDepartmentH";
    }
}
