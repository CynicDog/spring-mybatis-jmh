package com.example.web.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@NoArgsConstructor
@Data @Alias("Department")
public class Department {

    private int id;
    private String name;
    private Employee manager;
    private Integer loc;

    public Department(int id) {
        this.id = id;
    }
}
