package com.example.web.service;

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

import static com.example.web.util.FetchType.containsFetchType;

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
     *                    but pay attention to the order of arguments.
     *
     * @return The list of employees with fully populated associated properties based on the fetch types.
     */
    public List<Employee> getEmployeesByJobId(String jobId, FetchType... fetchTypes) {
        List<Employee> employees = employeeDao.getEmployeesByJobId(jobId);

        List<Employee> employeesFullyPopulated = employees.stream()
                .map(employee -> {
                    if (employee.getDepartment() != null && containsFetchType(fetchTypes, FetchType.EAGER)) {
                        employee.setDepartment(departmentDao.getDepartmentById(employee.getDepartment().getId()));
                    }
                    return employee;
                }).map(employee -> {
                    if (employee.getJob() != null  && containsFetchType(fetchTypes, FetchType.EAGER)) {
                        employee.setJob(jobDao.getJobById(employee.getJob().getId()));
                    }
                    return employee;
                }).map(employee -> {
                    if (employee.getManager() != null && containsFetchType(fetchTypes, FetchType.EAGER)) {
                        employee.setManager(employeeDao.getEmployeeById(employee.getManager().getId()));
                    }
                    return employee;
                }).collect(Collectors.toList());

        return employeesFullyPopulated;
    }

    public void registerJob(JobCommand jobCommand) {
//        Job job = new Job();
//        BeanUtils.copyProperties(jobCommand, job);

        Job job = jobCommand.toJob();

        jobDao.insertJob(job);
    }
}
