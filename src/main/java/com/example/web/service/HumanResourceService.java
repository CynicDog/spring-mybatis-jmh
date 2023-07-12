package com.example.web.service;

import com.example.web.controller.command.EmployeeBatchFileCommand;
import com.example.web.controller.command.EmployeeCommand;
import com.example.web.controller.command.JobCommand;
import com.example.web.mapper.DepartmentDao;
import com.example.web.mapper.EmployeeBatchFileDao;
import com.example.web.mapper.EmployeeDao;
import com.example.web.mapper.JobDao;
import com.example.web.model.EmployeeResponse;
import com.example.web.util.FetchType;
import com.example.web.util.Pagination;
import com.example.web.vo.Department;
import com.example.web.vo.Employee;
import com.example.web.vo.EmployeeBatchFile;
import com.example.web.vo.Job;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class HumanResourceService {

    @Value("${hr.employee.xls.save-directory}")
    private String batchFileDirectory;

    private final Logger logger = Logger.getLogger(HumanResourceService.class);

    private final DepartmentDao departmentDao;
    private final EmployeeDao employeeDao;
    private final JobDao jobDao;
    private final EmployeeBatchFileDao employeeBatchFileDao;

    public HumanResourceService(DepartmentDao departmentDao, EmployeeDao employeeDao, JobDao jobDao, EmployeeBatchFileDao employeeBatchFileDao) {
        this.departmentDao = departmentDao;
        this.employeeDao = employeeDao;
        this.jobDao = jobDao;
        this.employeeBatchFileDao = employeeBatchFileDao;
    }

    /**
     * Retrieves a list of employees by job ID, with options to fetch associated properties.
     *
     * @param jobId The ID of the job.
     * @param arg1, arg2, arg3
     *              The fetch types for associated properties.
     *              Use `FetchType.EAGER` to fetch the associated property on the spot,
     *              or `FetchType.LAZY` to not fetch the associated property immediately.
     *              You can pass multiple fetch types for different properties,
     *              but be carefully aware of the order of parameters when passing in arguments.
     * @return The list of employees with fully populated associated properties based on the fetch types.
     */
    public List<Employee> getEmployeesByJobId(String jobId, FetchType arg1, FetchType arg2, FetchType arg3) {
        List<Employee> employees = employeeDao.getEmployeesByJobId(jobId);

        List<Employee> employeesPopulated = employees.stream()
                .map(employee -> {
                    if (employee.getJob() != null && FetchType.EAGER.equals(arg1)) {
                        employee.setJob(jobDao.getJobById(employee.getJob().getId()));
                    }
                    return employee;
                }).map(employee -> {
                    if (employee.getManager() != null && FetchType.EAGER.equals(arg2)) {
                        employee.setManager(employeeDao.getEmployeeById(employee.getManager().getId()));
                    }
                    return employee;
                }).map(employee -> {
                    if (employee.getDepartment() != null && FetchType.EAGER.equals(arg3)) {
                        employee.setDepartment(departmentDao.getDepartmentById(employee.getDepartment().getId()));
                    }
                    return employee;
                }).collect(Collectors.toList());

        return employeesPopulated;
    }

    public List<Department> getAllDepartments(FetchType arg1) {
        List<Department> departments = departmentDao.getAllDepartments();

        List<Department> departmentPopulated = departments.stream()
                .map(department -> {
                    if (department.getManager() != null && FetchType.EAGER.equals(arg1)) {
                        department.setManager(employeeDao.getEmployeeById(department.getManager().getId()));
                    }
                    return department;
                }).collect(Collectors.toList());

        return departmentPopulated;
    }

    public List<Job> getAllJobs() {
        return jobDao.getAllJobs();
    }

    public void registerJob(JobCommand jobCommand) {
//        Job job = new Job();
//        BeanUtils.copyProperties(jobCommand, job);

        Job job = jobCommand.toJob();

        jobDao.insertJob(job);
    }

    public List<Employee> getAllEmployees(FetchType arg1, FetchType arg2, FetchType arg3) {

        List<Employee> employees = employeeDao.getAllEmployees();

        List<Employee> employeesPopulated = employees.stream()
                .map(employee -> {
                    if (employee.getJob() != null && FetchType.EAGER.equals(arg1)) {
                        employee.setJob(jobDao.getJobById(employee.getJob().getId()));
                    }
                    return employee;
                }).map(employee -> {
                    if (employee.getManager() != null && FetchType.EAGER.equals(arg2)) {
                        employee.setManager(employeeDao.getEmployeeById(employee.getManager().getId()));
                    }
                    return employee;
                }).map(employee -> {
                    if (employee.getDepartment() != null && FetchType.EAGER.equals(arg3)) {
                        employee.setDepartment(departmentDao.getDepartmentById(employee.getDepartment().getId()));
                    }
                    return employee;
                })
                .collect(Collectors.toList());

        return employeesPopulated;
    }

    public List<Employee> getEmployeesByDepartmentId(int department_id, FetchType arg1, FetchType arg2, FetchType arg3) {

        List<Employee> employees = employeeDao.getEmployeesByDepartmentId(department_id);

        List<Employee> employeesPopulated = employees.stream()
                .map(employee -> {
                    if (employee.getJob() != null && FetchType.EAGER.equals(arg1)) {
                        employee.setJob(jobDao.getJobById(employee.getJob().getId()));
                    }
                    return employee;
                }).map(employee -> {
                    if (employee.getManager() != null && FetchType.EAGER.equals(arg2)) {
                        employee.setManager(employeeDao.getEmployeeById(employee.getManager().getId()));
                    }
                    return employee;
                }).map(employee -> {
                    if (employee.getDepartment() != null && FetchType.EAGER.equals(arg3)) {
                        employee.setDepartment(departmentDao.getDepartmentById(employee.getDepartment().getId()));
                    }
                    return employee;
                }).collect(Collectors.toList());

        return employeesPopulated;
    }

    public void registerEmployee(EmployeeCommand employeeCommand) {

//        Employee employee = new Employee();
//        BeanUtils.copyProperties(employeeCommand, employee);

        Employee employee = employeeCommand.toEmployee();

        if (employeeCommand.getJobId() != null) {
            employee.setJob(new Job(employeeCommand.getJobId()));
        }

        if (employeeCommand.getManagerId() != null) {
            employee.setManager(new Employee(employeeCommand.getManagerId()));
        }

        if (employeeCommand.getDepartmentId() != null) {
            employee.setDepartment(new Department(employeeCommand.getDepartmentId()));
        }

        employeeDao.insertEmployee(employee);
    }

    public EmployeeResponse getEmployeesPaginated(Map<String, Object> params, FetchType arg1, FetchType arg2, FetchType arg3) {
        EmployeeResponse employeeResponse = new EmployeeResponse();

        int totalRows = employeeDao.getTotalRows(params);

        int rows = (int) params.get("rows");
        int page = (int) params.get("page");

        Pagination pagination = new Pagination(rows, page, totalRows);

        int begin = pagination.getBegin();
        int end = pagination.getEnd();

        params.put("begin", begin);
        params.put("end", end);

        employeeResponse.setPagination(pagination);

        List<Employee> employees = employeeDao.getEmployeesPaginated(params);

        List<Employee> employeesPopulated = employees.stream()
                .map(employee -> {
                    if (employee.getJob() != null && FetchType.EAGER.equals(arg1)) {
                        employee.setJob(jobDao.getJobById(employee.getJob().getId()));
                    }
                    return employee;
                }).map(employee -> {
                    if (employee.getManager() != null && FetchType.EAGER.equals(arg2)) {
                        employee.setManager(employeeDao.getEmployeeById(employee.getManager().getId()));
                    }
                    return employee;
                }).map(employee -> {
                    if (employee.getDepartment() != null && FetchType.EAGER.equals(arg3)) {
                        employee.setDepartment(departmentDao.getDepartmentById(employee.getDepartment().getId()));
                    }
                    return employee;
                }).collect(Collectors.toList());

        employeeResponse.setEmployees(employeesPopulated);

        return employeeResponse;
    }

    public EmployeeResponse getEmployeesPaginatedByJoin(Map<String, Object> params) {
        EmployeeResponse employeeResponse = new EmployeeResponse();

        int totalRows = employeeDao.getTotalRows(params);

        int rows = (int) params.get("rows");
        int page = (int) params.get("page");

        Pagination pagination = new Pagination(rows, page, totalRows);

        int begin = pagination.getBegin();
        int end = pagination.getEnd();

        params.put("begin", begin);
        params.put("end", end);

        employeeResponse.setPagination(pagination);

        List<Employee> employees = employeeDao.getEmployeesPaginatedByJoin(params);
        employeeResponse.setEmployees(employees);

        return employeeResponse;
    }

    public Department getDepartmentById(int departmentId, FetchType arg1) {

        Department department = departmentDao.getDepartmentById(departmentId);

        if (department.getManager() != null && FetchType.EAGER.equals(arg1)) {
            department.setManager(employeeDao.getEmployeeById(department.getManager().getId()));
        }

        return department;
    }

    public Employee getEmployeeById(int employeeId, FetchType arg1, FetchType arg2, FetchType arg3) {

        Employee employee = employeeDao.getEmployeeById(employeeId);

        if (employee.getJob() != null && FetchType.EAGER.equals(arg1)) {
            employee.setJob(jobDao.getJobById(employee.getJob().getId()));
        }

        if (employee.getManager() != null && FetchType.EAGER.equals(arg2)) {
            employee.setManager(employeeDao.getEmployeeById(employee.getManager().getId()));
        }

        if (employee.getDepartment() != null && FetchType.EAGER.equals(arg3)) {
            employee.setDepartment(departmentDao.getDepartmentById(employee.getDepartment().getId()));
        }

        return employee;
    }

    public void insertBatchFile(EmployeeBatchFileCommand employeeBatchFileCommand) throws Exception {

        EmployeeBatchFile employeeBatchFile = employeeBatchFileCommand.toEmployeeBatchFile();

        if (employeeBatchFile != null) {
            employeeBatchFileDao.insertEmployeeBatchFile(employeeBatchFile);

            File file = new File(batchFileDirectory, employeeBatchFile.getName());
            employeeBatchFileCommand.getMultipartFile().transferTo(file);
        }
    }

    public List<EmployeeBatchFile> getAllEmployeeFiles() {

        return employeeBatchFileDao.getEmployeeBatchFiles();
    }

    public void registerEmployeeInBatch(int fileId) throws Exception {

        EmployeeBatchFile employeeBatchFile = employeeBatchFileDao.getEmployeeBatchFileById(fileId);
        String filename = employeeBatchFile.getName();

        File file = new File(batchFileDirectory, filename);

        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheet("Sheet1");

        for (int idx = 1; idx <= sheet.getLastRowNum(); idx++) {
            Row row = sheet.getRow(idx);

            String firstName = row.getCell(0).getStringCellValue();
            String lastName = row.getCell(1).getStringCellValue();
            String email = row.getCell(2).getStringCellValue();
            String phoneNumber = row.getCell(3).getStringCellValue();
            Date hireDate = row.getCell(4).getDateCellValue();
            String jobId = row.getCell(5).getStringCellValue();
            Double salary = row.getCell(6).getNumericCellValue();
            Double commissionPct = row.getCell(7).getNumericCellValue();
            int managerId = (int) row.getCell(8).getNumericCellValue();
            int departmentId = (int) row.getCell(9).getNumericCellValue();

            Employee employee = new Employee(firstName, lastName, email, phoneNumber, hireDate, salary, commissionPct);
            employee.setJob(jobDao.getJobById(jobId));
            employee.setManager(employeeDao.getEmployeeById(managerId));
            employee.setDepartment(departmentDao.getDepartmentById(departmentId));

            employeeDao.insertEmployee(employee);
        }

        employeeBatchFile.setAdded("Y");
        employeeBatchFileDao.updateEmployeeBatchFile(employeeBatchFile);

        workbook.close();
    }

    public EmployeeBatchFile getEmployeeBatchFileById(int fileId) {

        return employeeBatchFileDao.getEmployeeBatchFileById(fileId);
    }
}
