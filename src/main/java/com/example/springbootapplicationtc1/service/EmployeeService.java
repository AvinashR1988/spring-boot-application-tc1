package com.example.springbootapplicationtc1.service;

import com.example.springbootapplicationtc1.model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface EmployeeService {

    Employee saveEmployee(Employee employee);

    List<Employee> findAllEmployee();

    Optional<Employee> getEmployeeById(Long id);

    Employee updateEmployee(Employee employee);

    void deleteEmployee(Long id);
}
