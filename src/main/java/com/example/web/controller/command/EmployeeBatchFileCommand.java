package com.example.web.controller.command;

import com.example.web.vo.EmployeeBatchFile;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter @Setter
public class EmployeeBatchFileCommand {

    private String title;
    private MultipartFile multipartFile;

    public EmployeeBatchFile toEmployeeBatchFile() {

        if (!multipartFile.isEmpty()) {
            return new EmployeeBatchFile(title, getMultipartFile().getOriginalFilename());
        }

        return null;
    }
}
