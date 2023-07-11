package com.example.web.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Alias("EmployeeBatchFile")
@Getter @Setter @NoArgsConstructor 
public class EmployeeBatchFile {

    private int id;
    private String title;
    private String name;
    private String added;
    private Date createDate;

    public EmployeeBatchFile(String title, String name) {
        this.title = title;
        this.name = name;
    }
}
