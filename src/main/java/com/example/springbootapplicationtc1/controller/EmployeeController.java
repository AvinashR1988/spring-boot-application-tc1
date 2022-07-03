package com.example.springbootapplicationtc1.controller;

import com.example.springbootapplicationtc1.model.Employee;
import com.example.springbootapplicationtc1.model.Message;
import com.example.springbootapplicationtc1.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/employee")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee createEmployee(@RequestBody Employee employee) {
        System.out.println("employee");
        return employee;
    }

    @GetMapping("/msg")
    public Message getEmployee() {
        return new Message("HI");
    }

}
