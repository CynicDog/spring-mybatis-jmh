package com.example.web.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Alias("Job")
@Data @NoArgsConstructor @AllArgsConstructor
public class Job {

    private String id;
    private String title;
    private Integer minSalary;
    private Integer maxSalary;

    public Job(String id) {
        this.id = id;
    }
}