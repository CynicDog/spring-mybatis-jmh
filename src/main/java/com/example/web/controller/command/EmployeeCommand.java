package com.example.web.controller.command;

import com.example.web.vo.Department;
import com.example.web.vo.Employee;
import com.example.web.vo.Job;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class EmployeeCommand {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Date hireDate;
    private String jobId;
    private Double salary;
    private Double commissionPct;
    private Integer managerId;
    private Integer departmentId;
}
