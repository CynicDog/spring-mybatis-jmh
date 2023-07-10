package com.example.web.model;

import com.example.web.util.Pagination;
import com.example.web.vo.Employee;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter @Setter
public class EmployeeResponse {

    private Pagination pagination;
    private List<Employee> employees;

}
