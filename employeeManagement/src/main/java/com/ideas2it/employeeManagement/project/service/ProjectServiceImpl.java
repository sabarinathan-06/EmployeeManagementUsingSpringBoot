package com.ideas2it.employeeManagement.project.service;

import com.ideas2it.employeeManagement.model.Project;
import com.ideas2it.employeeManagement.project.respository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Project getProjectById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findByIsDeletedFalse();
    }

    @Override
    public Project updateProject(Long id, Project projectDetails) {
        Project project = getProjectById(id);
        project.setProjectName(projectDetails.getProjectName());
        return projectRepository.save(project);
    }

    @Override
    public void deleteProject(Long id) {
        Project project = getProjectById(id);
        project.setDeleted(true);
        projectRepository.save(project);
    }
}
