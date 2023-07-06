package com.example.web.controller;

import com.example.web.service.HumanResourceService;
import com.example.web.vo.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.List;

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
    public String detail() {
        return "";
    }
}