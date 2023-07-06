package com.example.web.mapper;

import com.example.web.vo.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface DepartmentDao {

    List<Department> getAllDepartments();

    Department getDepartmentById(@Param("department_id") int id);
}
