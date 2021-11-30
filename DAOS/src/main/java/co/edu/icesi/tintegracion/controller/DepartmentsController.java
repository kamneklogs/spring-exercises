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
    public String addEmployeepayhistory(@Validated @ModelAttribute Department department, BindingResult bindingResult,
            Model model, @RequestParam(value = "action", required = true) String action) {
        if (!action.equals("cancel")) {

            if (bindingResult.hasErrors()) {
                model.addAttribute("department", department);
                return "departments/addDepartment";
            }

            departmentService.save(department);
        }
        return "redirect:/departments/";
    }

    @GetMapping("/departments/edit/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        Department department = departmentService.findById(id);
        if (department == null)
            throw new IllegalArgumentException("Invalid person Id:" + id);
        model.addAttribute("department", department);

        return "departments/update-department";
    }

    @PostMapping("/departments/edit/{id}")
    public String updateDepartment(@Validated @ModelAttribute Department department, BindingResult bindingResult,
            Model model, @PathVariable("id") int id, @RequestParam(value = "action", required = true) String action) {
        if (action != null && !action.equals("Cancel")) {

            if (bindingResult.hasErrors()) {
                model.addAttribute("department", department);
                return "department/edit/" + department.getDepartmentid();
            }
            department.setDepartmentid(id);
            departmentService.edit(department);
        }
        return "redirect:/departments/";
    }

    @GetMapping("/departments/showDetails/{id}")
    public String showDetails(@PathVariable("id") int id, Model model) {
        model.addAttribute("department", departmentService.findById(id));
        return "departments/show-details";
    }
}
