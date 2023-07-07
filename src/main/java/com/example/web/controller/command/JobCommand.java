package com.example.web.controller.command;

import com.example.web.vo.Job;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter @Setter @ToString
public class JobCommand {

    private String id;
    private String title;
    private Integer minSalary;
    private Integer maxSalary;

    public Job toJob() {
        return new Job(id, title, minSalary, maxSalary);
    }
}
