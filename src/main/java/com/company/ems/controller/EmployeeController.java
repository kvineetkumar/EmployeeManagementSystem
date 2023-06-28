package com.company.ems.controller;

import com.company.ems.model.Employee;
import com.company.ems.model.EmployeeDTO;
import com.company.ems.service.EmployeeService;
import com.company.ems.util.CustomModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employee/")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private CustomModelMapper customModelMapper;

    @PostMapping
    ResponseEntity<String> createNewEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
        Employee employee = (Employee) customModelMapper.map(employeeDTO, Employee.class);
        String id = employeeService.createNewEmployee(employee);
        return ResponseEntity.ok("Employee created successfully with id = ".concat(id));
    }

    @PostMapping(path = "batch")
    ResponseEntity<String> createNewEmployeeBatch(@RequestBody List<EmployeeDTO> employeeDTOs) {
        List<Employee> employees = employeeDTOs.stream().map(employeeDTO -> (Employee) customModelMapper.map(employeeDTO, Employee.class)).collect(Collectors.toList());
        employeeService.createNewEmployeeBatch(employees);
        return ResponseEntity.ok(employees.size() + " employees created successfully.");
    }

    @GetMapping(path = "{id}")
    ResponseEntity<Employee> getEmployee(@PathVariable("id") String id) {
        Employee employee = employeeService.getEmployee(id);
        return ResponseEntity.ok(employee);
    }

    @GetMapping
    ResponseEntity<Object> getEmployees(@RequestParam("start") int start, @RequestParam("end") int end) {
        if (start >= 0 && end > 0) {
            List<Employee> employees = employeeService.getEmployees(start, end);
            return ResponseEntity.ok(employees);
        } else {
            return ResponseEntity.badRequest().body("Expected values: start >=0 && end > 0");
        }
    }

    @PutMapping(path = "{id}")
    ResponseEntity<String> updateEmployee(@PathVariable("id") String id, @RequestBody EmployeeDTO employeeDTO) {
        Employee employee = (Employee) customModelMapper.map(employeeDTO, Employee.class);
        Employee updateEmployee = employeeService.updateEmployee(id, employee);
        return ResponseEntity.ok("Employee updated successfully with id = ".concat(updateEmployee.getId()));
    }

    @DeleteMapping(path = "{id}")
    ResponseEntity<String> deleteEmployee(@PathVariable("id") String id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok("Employee deleted successfully");
    }

    @GetMapping("/search")
    ResponseEntity<Object> searchEmployees(@RequestParam("query") String searchQuery) {
        List<Employee> matchingEmployees = employeeService.searchEmployee(searchQuery);
        if (matchingEmployees.isEmpty()) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(matchingEmployees);
    }
}