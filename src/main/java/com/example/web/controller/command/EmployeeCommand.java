package com.example.web.controller.command;

import com.example.web.vo.Employee;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;

@Getter @Setter @Component
public class EmployeeCommand {

    private String firstName;

    private String lastName;

    private String password;

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
        return new Employee(firstName, lastName, password, email, phoneNumber, hireDate, salary, commissionPct);
    }
}
