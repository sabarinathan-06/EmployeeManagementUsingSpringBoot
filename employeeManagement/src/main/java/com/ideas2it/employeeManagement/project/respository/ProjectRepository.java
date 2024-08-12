package com.ideas2it.employeeManagement.project.respository;

import com.ideas2it.employeeManagement.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByIsDeletedFalse();
}
