package com.example.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
public class HomeController {

    @GetMapping({"/", "/home", "/main"})
    public String home(Model model) {

        model.addAttribute("message", "Holi, Cómo estás.");

        return "home";
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello/model-and-view")
    public ModelAndView modelAndView() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("hello");

        return modelAndView;
    }
}
