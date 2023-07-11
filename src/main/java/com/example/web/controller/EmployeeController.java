package com.example.web.controller;

import com.example.web.controller.command.EmployeeCommand;
import com.example.web.model.EmployeeResponse;
import com.example.web.service.HumanResourceService;
import com.example.web.util.FetchType;
import com.example.web.vo.Department;
import com.example.web.vo.Employee;
import com.example.web.vo.Job;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/emp")
public class EmployeeController {

    private final Logger logger = Logger.getLogger(EmployeeController.class);

    @Autowired
    private HumanResourceService humanResourceService;

    @GetMapping("/list")
    public String list(@RequestParam(name = "sort", required = false, defaultValue = "id") String sort,
                       @RequestParam(name = "rows", required = false, defaultValue = "10") int rows,
                       @RequestParam(name = "page", required = false, defaultValue = "1") int page,
                       @RequestParam(name = "opt", required = false, defaultValue = "") String opt,
                       @RequestParam(name = "keyword", required = false, defaultValue = "") String keyword,
                       Model model) {

        Map<String, Object> params = new HashMap<>();
        params.put("sort", sort);
        params.put("rows", rows);
        params.put("page", page);
        params.put("opt", opt);
        params.put("keyword", keyword);

        EmployeeResponse response = humanResourceService.getEmployeesPaginated(params, FetchType.EAGER, FetchType.LAZY, FetchType.EAGER);

        model.addAttribute("response", response);

        return "employees/list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {

        List<Department> departments = humanResourceService.getAllDepartments(FetchType.EAGER);
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

    @GetMapping("/detail")
    @ResponseBody
    public Employee fetchEmployeeDetails(@RequestParam("id") int employeeId) {
        // http 'http://localhost:8081/emp/detail?id=100'
        Employee employee = humanResourceService.getEmployeeById(employeeId, FetchType.EAGER, FetchType.EAGER, FetchType.EAGER);

        return employee;
    }
}
