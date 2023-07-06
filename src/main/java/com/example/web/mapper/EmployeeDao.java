package com.example.web.mapper;

import com.example.web.vo.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface EmployeeDao {

    List<Employee> getEmployeesByJobId(@Param("job_id") String jobId);

    Employee getEmployeeById(@Param("employee_id") int id);
}
