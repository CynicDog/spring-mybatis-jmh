package com.example.web.controller;

import com.example.web.controller.command.EmployeeBatchFileCommand;
import com.example.web.controller.command.EmployeeCommand;
import com.example.web.model.EmployeeResponse;
import com.example.web.service.HumanResourceService;
import com.example.web.util.FetchType;
import com.example.web.view.EmployeesExcelView;
import com.example.web.view.FileDownloadView;
import com.example.web.vo.Department;
import com.example.web.vo.Employee;
import com.example.web.vo.EmployeeBatchFile;
import com.example.web.vo.Job;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.AbstractView;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/emp")
public class EmployeeController {

    @Value("${hr.employee.xls.save-directory}")
    private String batchFileDirectory;

    private final Logger logger = Logger.getLogger(EmployeeController.class);
    private final FileDownloadView fileDownloadView;
    private final HumanResourceService humanResourceService;
    private final EmployeesExcelView employeesExcelView;

    public EmployeeController(FileDownloadView fileDownloadView, HumanResourceService humanResourceService, EmployeesExcelView employeesExcelView) {
        this.fileDownloadView = fileDownloadView;
        this.humanResourceService = humanResourceService;
        this.employeesExcelView = employeesExcelView;
    }

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

        // List<Employee> findByActive(Pageable pageable);
        // List<Employee> employees = userRepository.getEmployeesPaginated(PageRequest.of(1, 10, Sort.by("id")));
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

        return "redirect:/";
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

    @PostMapping("/batch-upload")
    public String fileUpload(EmployeeBatchFileCommand employeeBatchFileCommand) throws Exception {

        humanResourceService.insertBatchFile(employeeBatchFileCommand);

        return "redirect:files";
    }

    @GetMapping("/files")
    public String files(Model model) {

        List<EmployeeBatchFile> files = humanResourceService.getAllEmployeeFiles();

        model.addAttribute("files", files);

        return "employees/files";
    }

    @GetMapping("/batch-register")
    public String addInBatch(@RequestParam("id") int fileId) throws Exception {
        humanResourceService.registerEmployeeInBatch(fileId);

        return "redirect:files";
    }

    @GetMapping("/download")
    public ModelAndView download(@RequestParam("id") int fileId) throws Exception {

        EmployeeBatchFile employeeBatchFile = humanResourceService.getEmployeeBatchFileById(fileId);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setView(fileDownloadView);
        modelAndView.addObject("directory", batchFileDirectory);
        modelAndView.addObject("filename", employeeBatchFile.getName());

        return modelAndView;
    }

    @GetMapping("/fetch-in-xls")
    public ModelAndView fetchInXls() {
        List<Employee> employees = humanResourceService.getAllEmployees(FetchType.LAZY, FetchType.EAGER, FetchType.EAGER);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setView(employeesExcelView);
        modelAndView.addObject("entities", employees);

        return modelAndView;
    }

    @GetMapping("/login")
    public String loginPage() {

        return "employees/login-form";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/info")
    public String info(Authentication authentication, Model model) {

        Employee employee = (Employee) authentication.getPrincipal();

        model.addAttribute("employee", employee);

        return "employees/info";
    }


//    @PreAuthorize("isAuthenticated()")
//    @GetMapping("/info")
//    public String info(@AuthenticationPrincipal Employee employee, Model model) {
//
//        model.addAttribute("employee", employee);
//
//        return "employees/info";
//    }
}
