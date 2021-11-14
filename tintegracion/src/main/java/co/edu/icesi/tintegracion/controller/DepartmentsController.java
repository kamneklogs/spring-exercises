package co.edu.icesi.tintegracion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.icesi.tintegracion.model.hr.Department;
import co.edu.icesi.tintegracion.services.interfaces.DepartmentService;

@Controller
public class DepartmentsController {

    private DepartmentService departmentService;

    @Autowired
    public DepartmentsController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/departments/")
    public String departments(Model model) {
        model.addAttribute("departments", departmentService.findAll());
        return "departments/index";
    }

    @GetMapping("/departments/addDepartment")
    public String addDepartment(Model model) {

        model.addAttribute("department", new Department());
        return "departments/addDepartment";
    }

    @PostMapping("/departments/addDepartment")
    public String addEmployeepayhistory(Department department, Model model,
            @RequestParam(value = "action", required = true) String action) {
        if (!action.equals("cancel")) {

            departmentService.save(department);
        }
        return "redirect:/departments/";
    }

}
