package com.example.web.service;

import com.example.web.mapper.DepartmentDao;
import com.example.web.mapper.EmployeeDao;
import com.example.web.mapper.JobDao;
import com.example.web.vo.Department;
import com.example.web.vo.Employee;
import com.example.web.vo.Job;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class HumanResourceService {

    private final DepartmentDao departmentDao;
    private final EmployeeDao employeeDao;
    private final JobDao jobDao;

    public HumanResourceService(DepartmentDao departmentDao, EmployeeDao employeeDao, JobDao jobDao) {
        this.departmentDao = departmentDao;
        this.employeeDao = employeeDao;
        this.jobDao = jobDao;
    }

    public List<Department> getAllDepartments() {
        return departmentDao.getAllDepartments();
    }

    public List<Job> getAllJobs() {
        return jobDao.getAllJobs();
    }

    public List<Employee> getEmployeesByJobId(String jobId) {
        List<Employee> employees = employeeDao.getEmployeesByJobId(jobId);

        List<Employee> employeesFullyPopulated = employees.stream()
                .map(employee -> {
                    if (employee.getDepartment() != null) {
                        employee.setDepartment(departmentDao.getDepartmentById(employee.getDepartment().getId()));
                    }
                    return employee;
                }).map(employee -> {
                    if (employee.getJob() != null) {
                        employee.setJob(jobDao.getJobById(employee.getJob().getId()));
                    }
                    return employee;
                }).map(employee -> {
                    if (employee.getManager() != null) {
                        employee.setManager(employeeDao.getEmployeeById(employee.getManager().getId()));
                    }
                    return employee;
                }).collect(Collectors.toList());

        return employeesFullyPopulated;
    }
}
