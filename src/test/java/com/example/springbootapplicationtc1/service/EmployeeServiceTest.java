package com.example.springbootapplicationtc1.service;

import com.example.springbootapplicationtc1.exception.ResourceNotFoundException;
import com.example.springbootapplicationtc1.model.Employee;
import com.example.springbootapplicationtc1.repository.EmployeeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.never;

public class EmployeeServiceTest {

    private EmployeeRepository employeeRepository;

    private EmployeeService employeeService;

    private Employee employee;

    @BeforeEach
    public void setUp() {
        employeeRepository = Mockito.mock(EmployeeRepository.class);
        employeeService = new EmployeeServiceImpl(employeeRepository);
        employee = Employee.builder().firstName("Aniket").lastName("Rakhunde").email("ani@gmail.com").build();
    }

    //JUnit Test For - service layer save method
    @Test
    public void givenEmployeeObject_whenSaveEmployee_thenEmployeeObject() {
        //given - precondition or setup
        BDDMockito.given(employeeRepository.findByEmail(employee.getEmail())).willReturn(Optional.empty());
        BDDMockito.given(employeeRepository.save(employee)).willReturn(employee);

        //when - action or behavior that we are going to test.
        Employee savedEmployee = employeeService.saveEmployee(employee);

        //then - verify the output
        assertThat(savedEmployee).isNotNull();

    }

    //JUnit Test For - test Throw exception
    @Test
    public void givenEmployeeObject_whenSaveThrowsException_thenPostLineShouldNeverExecute() {
        //given - precondition or setup
        BDDMockito.given(employeeRepository.findByEmail(employee.getEmail())).willReturn(Optional.of(employee));
        /*BDDMockito.given(employeeRepository.save(employee)).willReturn(employee);*///Not required

        //when - action or behavior that we are going to test.
        org.junit.jupiter.api.Assertions.assertThrows(ResourceNotFoundException.class, ()->{employeeService.saveEmployee(employee);});

        //then - verify the output
        Mockito.verify(employeeRepository,never()).save(Mockito.any(Employee.class));
    }


    //JUnit Test For - getEmployeeList (positive scenario)
    @DisplayName("JUnit Test For - getEmployeeList (positive scenario)")
    @Test
    public void givenEmployeeList_whenfindAllEmployee_thenReturnEmployeeList() {
        //given - precondition or setup
        Employee employee1 = Employee.builder().firstName("Avinash").lastName("Rakhunde").email("avi@gmail.com").build();
        BDDMockito.given(employeeRepository.findAll()).willReturn(List.of(employee,employee1));

        //when - action or behavior that we are going to test.
        List<Employee> empList = employeeService.findAllEmployee();

        //then - verify the output
        assertThat(empList).isNotNull();
        assertThat(empList.size()).isEqualTo(2);
    }


    //JUnit Test For - getEmployeeList (negative scenario)
    @Test
    public void given_when_then() {

       //given - precondition or setup
        BDDMockito.given(employeeRepository.findAll()).willReturn(BDDMockito.anyList());
       //when - action or behavior that we are going to test.
        List<Employee> allEmployee = employeeService.findAllEmployee();

        //then - verify the output
        assertThat(allEmployee.size()).isEqualTo(0);
    }
    //TODO
    //3. getEmployeeById
    //4. updateEmployee
    //5. deleteEmployee
}
