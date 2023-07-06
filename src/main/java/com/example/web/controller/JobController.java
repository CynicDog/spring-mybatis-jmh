package com.example.web.controller;

import com.example.web.service.HumanResourceService;
import com.example.web.vo.Employee;
import com.example.web.vo.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/job")
public class JobController {

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

        List<Employee> employees = humanResourceService.getEmployeesByJobId(jobId);
        model.addAttribute("employees", employees);

        return "jobs/details";
    }

    @GetMapping("/add")
    public String add() {

        return "jobs/registration-form";
    }
}
