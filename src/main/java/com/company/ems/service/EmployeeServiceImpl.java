package com.company.ems.service;

import com.company.ems.exception.ResourceNotFoundException;
import com.company.ems.model.Employee;
import com.company.ems.repository.EmployeeRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public String createNewEmployee(Employee employee) {
        log.trace("...createNewEmployee(employee)...");
        Employee savedEmployee = employeeRepository.save(employee);
        return savedEmployee.getId();
    }

    @Override
    public void createNewEmployeeBatch(List<Employee> employees) {
        employeeRepository.saveAll(employees);
    }

    @Override
    public Employee getEmployee(String id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        return employeeOptional.orElseThrow(() -> new ResourceNotFoundException(id, "Employee not found"));
    }

    @Override
    public List<Employee> getEmployees(int start, int end) {
        Sort sort = Sort.by("name");
        Pageable pageable = PageRequest.of(start, end, sort);
        Page<Employee> page = employeeRepository.findAll(pageable);
        return page.get().collect(Collectors.toList());
    }

    @Override
    public Employee updateEmployee(String id, Employee employee) {
        if (employeeRepository.existsById(id)) {
            employee.setId(id);
            return employeeRepository.save(employee);
        } else throw new ResourceNotFoundException(id, "Employee not found");
    }

    @Override
    public void deleteEmployee(String id) {
        log.trace("...deleteEmployee(id)...");
        if (employeeRepository.existsById(id)) employeeRepository.deleteById(id);
        else throw new ResourceNotFoundException(id, "Employee not found");
    }

    @Override
    public List<Employee> searchEmployee(String query) {
        log.trace("...searchEmployee(searchQuery)...");
        return employeeRepository.findAllByNameContainingIgnoreCase(query);
    }
}