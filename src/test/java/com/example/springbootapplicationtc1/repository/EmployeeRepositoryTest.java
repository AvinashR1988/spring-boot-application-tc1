package com.example.springbootapplicationtc1.repository;

import com.example.springbootapplicationtc1.model.Employee;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee employee;

    @BeforeEach
    public void setUp() {
        employee = Employee.builder().firstName("Aniket").lastName("Rakhunde").email("ani@gmail.com").build();
    }

    @DisplayName("JUnit test for save Employee operation")
    @Test
    public void givenEmployeeObject_whenSave_thenReturnSavedEmployee(){

        Employee employee = Employee.builder().firstName("Aniket").lastName("Rakhunde").email("ani@gmail.com").build();

        Employee savedEmp = employeeRepository.save(employee);

        Assertions.assertThat(savedEmp).isNotNull();

        Assertions.assertThat(savedEmp.getId()).isGreaterThan(0);
    }

    //JUnit test for get List of Employees
    @DisplayName("JUnit test for get List of Employees")
    @Test
    public void givenEmployeeList_whenFindAll_thenEmployeeList() {
        Employee employee2 = Employee.builder().firstName("Avinash").lastName("Rakhunde").email("avi@gmail.com").build();

        employeeRepository.save(employee);
        employeeRepository.save(employee2);

        List<Employee> empList = employeeRepository.findAll();

        Assertions.assertThat(empList).isNotNull();
        Assertions.assertThat(empList.size()).isEqualTo(2);

    }

    //JUnit Test For - get employee by Id operation
    @DisplayName("JUnit Test For - get employee by Id operation")
    @Test
    public void givenEmployeeObject_whenFindById_thenReturnEmployeeObject() {
        //given - precondition or setup
        Employee savedEmployee = employeeRepository.save(employee);

        //when - action or behavior that we are going to test.
        Employee fetchedEmployee = employeeRepository.findById(savedEmployee.getId()).get();

        //then - verify the output
        Assertions.assertThat(fetchedEmployee).isNotNull();
        Assertions.assertThat(fetchedEmployee.getId()).isEqualTo(employee.getId());
    }


    //JUnit Test For - Custom Spring Data JPA Query
    @DisplayName("JUnit Test For - get employee by Email Spring Data JPA Query")
    @Test
    public void givenEmployeeObject_whenFindByEmail_thenEmployeeObject() {
        //given - precondition or setup
        Employee savedEmployee = employeeRepository.save(employee);

        //when - action or behavior that we are going to test.
        Employee fetchedEmployee = employeeRepository.findByEmail(savedEmployee.getEmail()).get();

        //then - verify the output
        Assertions.assertThat(fetchedEmployee).isNotNull();
    }


    //JUnit Test For - update employee
    @DisplayName("JUnit Test For - update employee operation")
    @Test
    public void givenEmployeeObject_whenUpdateEmployee_thenUpdatedEmployeeObject() {
        //given - precondition or setup
        employeeRepository.save(employee);

        //when - action or behavior that we are going to test.
        Optional<Employee> fetchedEmployee = employeeRepository.findById(employee.getId());
        fetchedEmployee.get().setEmail("aniket@gmail.com");
        Employee updatedEmployee = employeeRepository.save(fetchedEmployee.get());

        //then - verify the output
        Assertions.assertThat(updatedEmployee).isNotNull();
        Assertions.assertThat(updatedEmployee.getEmail()).isEqualTo("aniket@gmail.com");
    }

    //JUnit Test For - delete employee
    @DisplayName("JUnit Test For - delete employee operation")
    @Test
    public void givenEmployeeObject_whenDelete_thenRemoveEmployee() {
        //given - precondition or setup
        employeeRepository.save(employee);
        //when - action or behavior that we are going to test.

        employeeRepository.deleteById(employee.getId());

        //then - verify the output
        Optional<Employee> employeeOptional = employeeRepository.findById(employee.getId());
        Assertions.assertThat(employeeOptional).isEmpty();
    }

    //JUnit Test For - PQL Index to find Employee by first & last name
    @DisplayName("JUnit Test For - JPQL Index find Employee by first & last name")
    @Test
    public void givenEmployeeObject_whenFindByFirstAndLastNameJPQLIndex_thenEmployeeObject() {

        //given - precondition or setup
        employeeRepository.save(employee);

        //when - action or behavior that we are going to test.
        Employee fetchedEmployee = employeeRepository.getEmployeeByNameAndEmailJPQLIndex(employee.getFirstName(), employee.getLastName());

        //then - verify the output

        Assertions.assertThat(fetchedEmployee).isNotNull();
        Assertions.assertThat(fetchedEmployee.getFirstName()).isEqualTo("Aniket");
    }

    //JUnit Test For - PQL Index to find Employee by first & last name
    @DisplayName("JUnit Test For - JPQL Parameterised find Employee by first & last name")
    @Test
    public void givenEmployeeObject_whenFindByFirstAndLastNameJPQLParam_thenEmployeeObject() {

        //given - precondition or setup
        employeeRepository.save(employee);

        //when - action or behavior that we are going to test.
        Employee fetchedEmployee = employeeRepository.getEmployeeByNameAndEmailJPQLParameterised(employee.getFirstName(), employee.getLastName());

        //then - verify the output

        Assertions.assertThat(fetchedEmployee).isNotNull();
        Assertions.assertThat(fetchedEmployee.getFirstName()).isEqualTo("Aniket");
    }

    //JUnit Test For - Native SQL index : to find Employee by first & last name
    @DisplayName("JUnit Test For - Native SQL index find Employee by first & last name")
    @Test
    public void givenEmployeeObject_whenFindByFirstAndLastNameNativeSqlIndex_thenEmployeeObject() {

        //given - precondition or setup
        employeeRepository.save(employee);

        //when - action or behavior that we are going to test.
        Employee fetchedEmployee = employeeRepository.getEmployeeByNameAndEmailNativeSqlIndex(employee.getFirstName(), employee.getLastName());

        //then - verify the output

        Assertions.assertThat(fetchedEmployee).isNotNull();
        Assertions.assertThat(fetchedEmployee.getFirstName()).isEqualTo("Aniket");
    }

    //JUnit Test For - Native SQL param : to find Employee by first & last name
    @DisplayName("JUnit Test For - Native SQL Param find Employee by first & last name")
    @Test
    public void givenEmployeeObject_whenFindByFirstAndLastNameNativeSqlParam_thenEmployeeObject() {

        //given - precondition or setup
        employeeRepository.save(employee);

        //when - action or behavior that we are going to test.
        Employee fetchedEmployee = employeeRepository.getEmployeeByNameAndEmailNativeSqlIndex(employee.getFirstName(), employee.getLastName());

        //then - verify the output

        Assertions.assertThat(fetchedEmployee).isNotNull();
        Assertions.assertThat(fetchedEmployee.getFirstName()).isEqualTo("Aniket");
    }
}
