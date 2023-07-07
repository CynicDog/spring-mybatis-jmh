package com.example.web.service;

import com.example.web.controller.command.EmployeeCommand;
import com.example.web.controller.command.JobCommand;
import com.example.web.mapper.DepartmentDao;
import com.example.web.mapper.EmployeeDao;
import com.example.web.mapper.JobDao;
import com.example.web.util.FetchType;
import com.example.web.vo.Department;
import com.example.web.vo.Employee;
import com.example.web.vo.Job;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.web.util.FetchType.matchesFetchType;

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

    /**
     * Retrieves a list of employees by job ID, with options to fetch associated properties.
     *
     * @param jobId       The ID of the job.
     * @param fetchTypes  The fetch types for associated properties.
     *                    Use `FetchType.EAGER` to fetch the associated property on the spot,
     *                    or `FetchType.LAZY` to not fetch the associated property immediately.
     *                    You can pass multiple fetch types for different properties,
     *                    but be carefully aware of the order of parameters when passing in arguments.
     *
     * @return The list of employees with fully populated associated properties based on the fetch types.
     */
    public List<Employee> getEmployeesByJobId(String jobId, FetchType... fetchTypes) {
        List<Employee> employees = employeeDao.getEmployeesByJobId(jobId);

        List<Employee> employeesPopulated = employees.stream()
                .map(employee -> {
                    if (employee.getJob() != null  && matchesFetchType(fetchTypes, FetchType.EAGER)) {
                        employee.setJob(jobDao.getJobById(employee.getJob().getId()));
                    }
                    return employee;
                }).map(employee -> {
                    if (employee.getManager() != null && matchesFetchType(fetchTypes, FetchType.EAGER)) {
                        employee.setManager(employeeDao.getEmployeeById(employee.getManager().getId()));
                    }
                    return employee;
                }).map(employee -> {
                    if (employee.getDepartment() != null && matchesFetchType(fetchTypes, FetchType.EAGER)) {
                        employee.setDepartment(departmentDao.getDepartmentById(employee.getDepartment().getId()));
                    }
                    return employee;
                }).collect(Collectors.toList());

        return employeesPopulated;
    }

    public void registerJob(JobCommand jobCommand) {
//        Job job = new Job();
//        BeanUtils.copyProperties(jobCommand, job);

        Job job = jobCommand.toJob();

        jobDao.insertJob(job);
    }

    public List<Employee> getAllEmployees(FetchType... fetchTypes) {

        List<Employee> employees = employeeDao.getAllEmployees();

        List<Employee> employeesPopulated = employees.stream()
                .map(employee -> {
                    if (employee.getJob() != null  && matchesFetchType(fetchTypes, FetchType.EAGER)) {
                        employee.setJob(jobDao.getJobById(employee.getJob().getId()));
                    }
                    return employee;
                }).map(employee -> {
                    if (employee.getManager() != null && matchesFetchType(fetchTypes, FetchType.EAGER)) {
                        employee.setManager(employeeDao.getEmployeeById(employee.getManager().getId()));
                    }
                    return employee;
                }).map(employee -> {
                    if (employee.getDepartment() != null && matchesFetchType(fetchTypes, FetchType.EAGER)) {
                        employee.setDepartment(departmentDao.getDepartmentById(employee.getDepartment().getId()));
                    }
                    return employee;
                })
                .collect(Collectors.toList());

        return employeesPopulated;
    }

    public List<Employee> getEmployeesByDepartmentId(int department_id, FetchType... fetchTypes) {

        List<Employee> employees = employeeDao.getEmployeesByDepartmentId(department_id);

        List<Employee> employeesPopulated = employees.stream()
                .map(employee -> {
                    if (employee.getJob() != null && matchesFetchType(fetchTypes, FetchType.EAGER)) {
                        employee.setJob(jobDao.getJobById(employee.getJob().getId()));
                    }
                    return employee;
                }).map(employee -> {
                    if (employee.getManager() != null && matchesFetchType(fetchTypes, FetchType.EAGER)) {
                        employee.setManager(employeeDao.getEmployeeById(employee.getManager().getId()));
                    }
                    return employee;
                }).map(employee -> {
                    if (employee.getDepartment() != null && matchesFetchType(fetchTypes, FetchType.EAGER)) {
                        employee.setDepartment(departmentDao.getDepartmentById(employee.getDepartment().getId()));
                    }
                    return employee;
                }).collect(Collectors.toList());

        return employeesPopulated;
    }

    public void registerEmployee(EmployeeCommand employeeCommand) {

//        Employee employee = new Employee();
//        BeanUtils.copyProperties(employeeCommand, employee);
//        employee.setJob();
//        employee.setManager();
//        employee.setDepartment();

//        employeeDao.insertEmployee(employee);
    }
}
