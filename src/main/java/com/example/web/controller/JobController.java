package com.example.web.controller;

import com.example.web.controller.command.JobCommand;
import com.example.web.service.HumanResourceService;
import com.example.web.util.FetchType;
import com.example.web.vo.Employee;
import com.example.web.vo.Job;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/job")
@PreAuthorize("isAuthenticated()")
public class JobController {

    private final Logger logger = Logger.getLogger(JobController.class);

    @Autowired
    private HumanResourceService humanResourceService;

    @GetMapping("/list")
    public String list(Model model) {

        List<Job> jobs = humanResourceService.getAllJobs();
        model.addAttribute("jobs", jobs);

        return "jobs/list";
    }

    @GetMapping("/details")
    public String details(@RequestParam("id") String jobId, Model model) {

        List<Employee> employees = humanResourceService.getEmployeesByJobId(jobId, FetchType.EAGER, FetchType.EAGER, FetchType.EAGER);
        if (!employees.isEmpty()) {
            model.addAttribute("employees", employees);
        }

        return "jobs/details";
    }

    @GetMapping("/add")
    public String addForm() {

        return "jobs/add-form";
    }

    @PostMapping("/add")
    public String add(JobCommand jobCommand) {
        logger.info("Request for registration: [" + jobCommand.getId() + "]");

        humanResourceService.registerJob(jobCommand);
        return "redirect:list";
    }
}
