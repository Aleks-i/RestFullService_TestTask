package ru.testtask.sber.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {

    @GetMapping("/")
    public String root() {
        return "redirect:sber";
    }

    @GetMapping(value = "/departments")
    public String getDepartments() {
        return "departments";
    }

    @GetMapping("/employees")
    public String getEmployees() {
        return "employees";
    }

    @GetMapping("/sber")
    public String getSber() {
        return "sber";
    }
}
