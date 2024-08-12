package com.ideas2it.employeeManagement.project.controller;

import com.ideas2it.employeeManagement.mapper.ProjectMapper;
import com.ideas2it.employeeManagement.model.Project;
import com.ideas2it.employeeManagement.project.projectDTO.ProjectDTO;
import com.ideas2it.employeeManagement.project.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping
    public ProjectDTO createProject(@RequestBody ProjectDTO projectDTO) {
        return ProjectMapper.convertToDTO(projectService.createProject(ProjectMapper.convertToEntity(projectDTO)));
    }

    @GetMapping("/{id}")
    public ProjectDTO getProjectById(@PathVariable Long id) {
        return ProjectMapper.convertToDTO(projectService.getProjectById(id));
    }

    @GetMapping
    public List<ProjectDTO> getAllProjects() {
        List<ProjectDTO> projectDTOs = new ArrayList<>();
        List<Project> projects = projectService.getAllProjects();
        for (Project project : projects) {
            projectDTOs.add(ProjectMapper.convertToDTO(project));
        }
        return projectDTOs;    }

    @PutMapping("/{id}")
    public ProjectDTO updateProject(@PathVariable Long id, @RequestBody ProjectDTO projectDTO) {
        return ProjectMapper.convertToDTO(projectService.updateProject(id, ProjectMapper.convertToEntity(projectDTO)));
    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
    }
}
