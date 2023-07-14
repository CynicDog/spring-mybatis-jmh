package com.example.web.mapper;

import com.example.web.vo.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Mapper
public interface EmployeeDao {

    List<Employee> getEmployeesByJobId(@Param("job_id") String jobId);

    List<Employee> getEmployeesByDepartmentId(@Param("department_id") int departmentId);

    Employee getEmployeeById(@Param("employee_id") int id);

    List<Employee> getAllEmployees();

    void insertEmployee(Employee employee);

    int getTotalRows(Map<String, Object> params);
    List<Employee> getEmployeesPaginated(Map<String, Object> params);

    List<Employee> getEmployeesPaginatedByJoin(Map<String, Object> params);

    Employee getEmployeeByEmail(String email);
}
