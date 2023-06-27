package com.company.ems;

import com.company.ems.model.Employee;
import com.company.ems.repository.EmployeeRepository;
import com.company.ems.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class EmployeeServiceTest {

    @Autowired
    private EmployeeService employeeService;

    @MockBean
    private EmployeeRepository employeeRepository;

    @BeforeEach
    public void setUp() {
        Employee employee = new Employee();
        //employee.setId("1");
        employee.setName("David");
        employee.setPhone("9876543210L");
        employee.setEmail("david@david.com");
        Mockito.when(employeeRepository.findById("1")).thenReturn(Optional.of(employee));
    }

    @Test
    void testGetEmployeeById() {
        Employee employee = employeeService.getEmployee("1");
        assertTrue(employee.getName().contentEquals("David"));
    }
}