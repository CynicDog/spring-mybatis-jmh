package com.example.web.service;

import com.example.web.mapper.DepartmentDao;
import com.example.web.mapper.EmployeeDao;
import com.example.web.mapper.JobDao;
import com.example.web.util.FetchType;
import com.example.web.vo.Employee;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityService implements UserDetailsService {

    private final DepartmentDao departmentDao;
    private final EmployeeDao employeeDao;
    private final JobDao jobDao;

    public SecurityService(DepartmentDao departmentDao, EmployeeDao employeeDao, JobDao jobDao) {
        this.employeeDao = employeeDao;
        this.departmentDao = departmentDao;
        this.jobDao = jobDao;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Employee employee = employeeDao.getEmployeeByEmail(email);

        if (employee == null) {
            throw new UsernameNotFoundException("User not found.");
        }

        if (employee.getJob() != null) {
            employee.setJob(jobDao.getJobById(employee.getJob().getId()));
        }

        if (employee.getManager() != null) {
            employee.setManager(employeeDao.getEmployeeById(employee.getManager().getId()));
        }

        if (employee.getDepartment() != null) {
            employee.setDepartment(departmentDao.getDepartmentById(employee.getDepartment().getId()));
        }

        return employee;
    }
}
