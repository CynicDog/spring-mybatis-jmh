package com.example.web.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Data @Alias("Employee")
@NoArgsConstructor
public class Employee {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Date hireDate;
    private Job job;
    private Double salary;
    private Double commissionPct;
    private Employee manager;
    private Department department;

    public Employee(int id) {
        this.id = id;
    }

    public Employee(String firstName, String lastName, String email, String phoneNumber, Date hireDate, Double salary, Double commissionPct) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.hireDate = hireDate;
        this.salary = salary;
        this.commissionPct = commissionPct;
    }
}
