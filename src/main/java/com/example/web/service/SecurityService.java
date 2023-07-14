package com.example.web.service;

import com.example.web.mapper.EmployeeDao;
import com.example.web.vo.Employee;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityService implements UserDetailsService {

    private final EmployeeDao employeeDao;

    public SecurityService(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Employee employee = employeeDao.getEmployeeByEmail(email);

        if (employee == null) {
            throw new UsernameNotFoundException("User not found.");
        }

        return employee;
    }
}
