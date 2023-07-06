package com.example.web.vo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data @Alias("Department")
public class Department {

    private int id;
    private String name;
    private Employee manager;
    private Integer loc;
}
