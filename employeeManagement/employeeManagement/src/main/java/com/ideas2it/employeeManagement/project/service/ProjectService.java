package com.ideas2it.employeeManagement.project.service;

import com.ideas2it.employeeManagement.model.Project;

import java.util.List;

public interface ProjectService {
    Project createProject(Project project);

    Project getProjectById(Long id);

    List<Project> getAllProjects();

    Project updateProject(Long id, Project projectDetails);

    void deleteProject(Long id);
}
