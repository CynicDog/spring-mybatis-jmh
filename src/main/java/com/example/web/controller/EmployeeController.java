package com.example.web.controller;

import com.example.web.service.HumanResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class EmployeeController {

    @Autowired
    private HumanResourceService humanResourceService;
}
