package com.example.web.view;

import com.example.web.vo.Employee;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Component
public class EmployeesExcelView extends AbstractXlsView {

    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {

        List<Employee> employees = (List<Employee>) model.get("entities");

        Sheet sheet = workbook.createSheet("Employees");

        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Identifier");
        headerRow.createCell(1).setCellValue("First Name");
        headerRow.createCell(2).setCellValue("Email");
        headerRow.createCell(3).setCellValue("Phone");
        headerRow.createCell(4).setCellValue("Joined Date");
        headerRow.createCell(5).setCellValue("Job");
        headerRow.createCell(6).setCellValue("Salary");
        headerRow.createCell(7).setCellValue("Commission");
        headerRow.createCell(8).setCellValue("Manager");
        headerRow.createCell(9).setCellValue("Department Id.");
        headerRow.createCell(10).setCellValue("Department Name");

        int rowIdx = 1;
        for (Employee employee : employees) {
            Row row = sheet.createRow(rowIdx);

            row.createCell(0).setCellValue(employee.getId());
            row.createCell(1).setCellValue(employee.getFirstName() + " " + employee.getLastName());
            row.createCell(2).setCellValue(employee.getEmail());
            row.createCell(3).setCellValue(employee.getPhoneNumber());
            row.createCell(4).setCellValue(employee.getHireDate());
            row.createCell(5).setCellValue(employee.getJob().getId());
            row.createCell(6).setCellValue(employee.getSalary());
            if (employee.getCommissionPct() != null) {
                row.createCell(7).setCellValue(employee.getCommissionPct());
            }

            if (employee.getManager() != null) {
                row.createCell(8).setCellValue(employee.getManager().getFirstName() + " " + employee.getManager().getLastName());
            }

            if (employee.getDepartment() != null) {
                row.createCell(9).setCellValue(employee.getDepartment().getId());
                row.createCell(10).setCellValue(employee.getDepartment().getName());
            }

            rowIdx++;
        }
    }
}
