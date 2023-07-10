package com.example.web.controller.command;

import com.example.web.vo.Department;
import com.example.web.vo.Employee;
import com.example.web.vo.Job;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter @Setter
public class EmployeeCommand {

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date hireDate;

    private String jobId;

    private Double salary;

    private Double commissionPct;

    private Integer managerId;

    private Integer departmentId;

    public Employee toEmployee() {
        // Association properties are to be populated through setters in the service layer.
        return new Employee(firstName, lastName, email, phoneNumber, hireDate, salary, commissionPct);
    }
}
