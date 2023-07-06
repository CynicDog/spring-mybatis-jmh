package com.example.web.vo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Data @Alias("Employee")
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
}
