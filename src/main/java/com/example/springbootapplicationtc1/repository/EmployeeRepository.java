package com.example.springbootapplicationtc1.repository;

import com.example.springbootapplicationtc1.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    //Spring Data JPA Query method - custom operation
    Optional<Employee> findByEmail(String email);

    //JPQL index and parameterized
    @Query("select e from Employee e where e.firstName =?1 and e.lastName =?2")
    Employee getEmployeeByNameAndEmailJPQLIndex(String firstName, String lastName);

    //JPQL parameterized
    @Query("select e from Employee e where e.firstName =:fn and e.lastName =:ln")
    Employee getEmployeeByNameAndEmailJPQLParameterised(@Param("fn") String firstName,@Param("ln") String lastName);

    //Native SQL - Indexed
    @Query(value = "select * from employees e where e.first_name =?1 and e.last_name =?2", nativeQuery = true)
    Employee getEmployeeByNameAndEmailNativeSqlIndex(String firstName, String lastName);

    //Native SQL - Parameterised
    @Query(value = "select * from employees e where e.first_name =:fn and e.last_name =:ln", nativeQuery = true)
    Employee getEmployeeByNameAndEmailNativeSqlParams(@Param("fn") String firstName,@Param("ln") String lastName);

}