package com.company.ems.service;

import com.company.ems.model.Employee;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public interface EmployeeService {

    String createNewEmployee(Employee employee);

    void createNewEmployeeBatch(List<Employee> employees);

    Employee getEmployee(String id) throws ResponseStatusException;

    List<Employee> getEmployees(int start, int end);

    Employee updateEmployee(String id, Employee employee);

    void deleteEmployee(String id);

    List<Employee> searchEmployee(String searchQuery);
}
