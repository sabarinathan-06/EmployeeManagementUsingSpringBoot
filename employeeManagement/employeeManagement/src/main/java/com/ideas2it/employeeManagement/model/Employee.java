package com.ideas2it.employeeManagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 * This class is an Employee entity that is mapped to the "employee" table in the database.
 * This class holds the data related to an employee including their ID, name, place, dateOfBirth,
 * experience, salary and other relevant details.
 * It is annotated with JPA annotations to indicate that it is an entity and to define
 * the mapping between the fields and the database columns.
 * </p>
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "employees")
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "employee_name")
    private String employeeName;

    @Column(name = "place")
    private String place;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "experience")
    private int experience;

    @Column(name = "salary")
    private double salary;

    @Column(name = "is_deleted")
    private boolean isDeleted = false;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToMany
    @JoinTable(
            name = "employee_project",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    private List<Project> projects;

}
