package com.example.web.controller;

import com.example.web.service.HumanResourceService;
import com.example.web.util.FetchType;
import com.example.web.vo.Department;
import com.example.web.vo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/dept")
public class DepartmentController {

    @Autowired private HumanResourceService humanResourceService;

    @GetMapping("/list")
    public String list(Model model) {

        List<Department> departments = humanResourceService.getAllDepartments();
        model.addAttribute("departments", departments);

        return "departments/list";
    }

    @GetMapping("/register")
    public String registerView() {
        return "";
    }

    @PostMapping("/register")
    public String register() {
        return "";
    }

    @GetMapping("/modify")
    public String modifyView() {
        return "";
    }

    @PostMapping("/modify")
    public String modify() {
        return "";
    }

    @GetMapping("/detail")
    @ResponseBody
    public Map<String, Object> detail(@RequestParam("id") int departmentId) {
        // http get 'http://localhost:8081/dept/detail?id=20'

        Department department = humanResourceService.getDepartmentById(departmentId, FetchType.EAGER);
        List<Employee> employees = humanResourceService.getEmployeesByDepartmentId(departmentId, FetchType.EAGER, FetchType.EAGER, FetchType.EAGER);

        return Map.of(
                "department", department,
                "employees", employees
        );
    }
}