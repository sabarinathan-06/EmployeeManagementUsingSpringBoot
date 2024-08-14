package com.ideas2it.employeeManagement.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * Entity class representing a department in the organization.
 *
 * <p>
 * Maps to the "departments" table in the database and holds information about
 * each department, including its ID, name, and deletion status. A department
 * can have multiple associated employees.
 * </p>
 */
@Entity
@Table(name = "departments")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    private Long departmentId;

    @JsonManagedReference
    @Column(name = "department_name", unique = true)
    private String departmentName;

    @Column(name = "is_deleted")
    private boolean isDeleted = false;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<Employee> employees;

}
