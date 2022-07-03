package com.example.springbootapplicationtc1.service;

import com.example.springbootapplicationtc1.exception.ResourceNotFoundException;
import com.example.springbootapplicationtc1.model.Employee;
import com.example.springbootapplicationtc1.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {

        Optional<Employee> employeeOptional = employeeRepository.findByEmail(employee.getEmail());

        if (employeeOptional.isPresent()) {
            throw new ResourceNotFoundException("The Employee is already exists with the email id : "+employee.getEmail());
        }
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> findAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }


}
