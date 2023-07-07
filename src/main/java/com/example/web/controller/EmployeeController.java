package com.example.web.controller;

import com.example.web.controller.command.EmployeeCommand;
import com.example.web.service.HumanResourceService;
import com.example.web.util.FetchType;
import com.example.web.vo.Department;
import com.example.web.vo.Employee;
import com.example.web.vo.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/emp")
public class EmployeeController {

    @Autowired
    private HumanResourceService humanResourceService;

    @GetMapping("/list")
    public String list(Model model) {

        // TODO: Pagination
        List<Employee> employees = humanResourceService.getAllEmployees(FetchType.EAGER, FetchType.EAGER, FetchType.EAGER);

        model.addAttribute("employees", employees);

        return "employees/list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {

        List<Department> departments = humanResourceService.getAllDepartments();
        List<Job> jobs = humanResourceService.getAllJobs();

        model.addAttribute("departments", departments);
        model.addAttribute("jobs", jobs);

        return "employees/add-form";
    }

    @PostMapping("/add")
    public String add(EmployeeCommand employeeCommand) {

        humanResourceService.registerEmployee(employeeCommand);

        return "redirect:list";
    }

    // 직원 상세 정보화면 요청과 매핑되는 요청 핸들러 메소드
    // 직원 수정 폼화면 요청과 매칭되는 요청 핸들러 메소드
    // 직원 정보 수정 요청과 매핑되는 요청 핸들러 메소드

    @GetMapping("/by-department")
    @ResponseBody
    // http 'http://localhost:8081/emp/by-department?department=80'
    public List<Employee> fetchByDepartment(@RequestParam("department") int departmentId) {

        List<Employee> employees = humanResourceService.getEmployeesByDepartmentId(departmentId, FetchType.EAGER, FetchType.EAGER, FetchType.EAGER);

        if (!employees.isEmpty()) {
            return employees;
        }
        return null;
    }
}
