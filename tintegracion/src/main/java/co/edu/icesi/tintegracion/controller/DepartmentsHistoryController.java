package co.edu.icesi.tintegracion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
        model.addAttribute("departments", departmentService.findAll());
        return "department-history/addDepartmentH";
    }

    @PostMapping("/departmentsHistory/addDepartmentHistory")
    public String addDepartmentsHistory(Model model, Employeedepartmenthistory dHistory,
            @RequestParam(value = "action", required = true) String action) {
        if (!action.equals("cancel")) {
            dHistory
                    .setBusinessentityid(dHistory.getEmployee().getBusinessentityid());
            dHistory.setDepartment(dHistory.getDepartment());
            employeeDepartmentHistoryService.save(dHistory, dHistory.getDepartment().getDepartmentid());

            log.info(dHistory.getDepartment().getName() + "  Holiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
        }
        return "redirect:/departmentsHistory/";
    }
}