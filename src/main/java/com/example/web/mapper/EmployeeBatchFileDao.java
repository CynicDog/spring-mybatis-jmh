package com.example.web.mapper;

import com.example.web.vo.EmployeeBatchFile;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmployeeBatchFileDao {

    void insertEmployeeBatchFile(EmployeeBatchFile employeeBatchFile);
    List<EmployeeBatchFile> getEmployeeBatchFiles();
    EmployeeBatchFile getEmployeeBatchFileById(int id);
    void updateEmployeeBatchFile(EmployeeBatchFile employeeBatchFile);
}
