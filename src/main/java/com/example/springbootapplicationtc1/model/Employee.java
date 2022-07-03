package com.example.springbootapplicationtc1.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name="employees")
public class Employee {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private long id;

@Column(name = "first_name")
private String firstName;

@Column(name = "last_name")
private String lastName;

@Column
private String email;
}
