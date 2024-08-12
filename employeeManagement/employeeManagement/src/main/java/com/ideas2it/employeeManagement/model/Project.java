package com.ideas2it.employeeManagement.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "projects")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Long projectId;

    @Column(name = "project_name")
    private String projectName;

    @Column(name = "is_deleted")
    private boolean isDeleted = false;

    @ManyToMany(mappedBy = "projects", cascade = CascadeType.ALL)
    private List<Employee> employees;

}

