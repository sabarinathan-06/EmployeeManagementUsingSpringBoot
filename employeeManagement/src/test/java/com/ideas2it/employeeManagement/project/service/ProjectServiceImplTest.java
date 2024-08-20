package com.ideas2it.employeeManagement.project.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.ideas2it.employeeManagement.project.projectDTO.ProjectDTO;
import com.ideas2it.employeeManagement.project.respository.ProjectRepository;
import com.ideas2it.employeeManagement.model.Project;
import com.ideas2it.employeeManagement.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class ProjectServiceImplTest {

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectServiceImpl projectService;

    private Project project;
    private ProjectDTO projectDTO;
    private List<Project> projects;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        project = Project.builder()
                .projectId(1L)
                .projectName("Healthcare")
                .isDeleted(false)
                .build();

        projectDTO = ProjectDTO.builder()
                .projectId(1L)
                .projectName("Healthcare")
                .build();

        projects = new ArrayList<>();
        projects.add(project);

        Employee employee1 = Employee.builder()
                .employeeId(1L)
                .employeeName("Deepak")
                .place("Chennai")
                .build();

        Employee employee2 = Employee.builder()
                .employeeId(2L)
                .employeeName("Sandhiya")
                .place("Chennai")
                .build();

        project.setEmployees(Arrays.asList(employee1, employee2));
    }

    @Test
    void testCreateProject() {
        when(projectRepository.save(any(Project.class))).thenReturn(project);
        ProjectDTO result = projectService.createProject(projectDTO);
        assertEquals(projectDTO.getProjectId(), result.getProjectId());
    }

    @Test
    void testGetProjectById() {
        when(projectRepository.findByProjectIdAndIsDeletedFalse(anyLong())).thenReturn(Optional.of(project));
        ProjectDTO result = projectService.getProjectById(1L);
        assertEquals(projectDTO.getProjectId(), result.getProjectId());
    }

    @Test
    void testGetAllProjects() {
        when(projectRepository.findByIsDeletedFalse()).thenReturn(projects);
        List<ProjectDTO> result = projectService.getAllProjects();
        assertEquals(1, result.size());
        assertEquals(projectDTO.getProjectId(), result.get(0).getProjectId());
    }

    @Test
    void testUpdateProject() {
        when(projectRepository.findByProjectIdAndIsDeletedFalse(anyLong())).thenReturn(Optional.of(project));
        when(projectRepository.save(any(Project.class))).thenReturn(project);
        ProjectDTO result = projectService.updateProject(projectDTO);
        assertEquals(projectDTO.getProjectName(), result.getProjectName());
    }

    @Test
    void testDeleteProject() {
        when(projectRepository.findByProjectIdAndIsDeletedFalse(anyLong())).thenReturn(Optional.of(project));
        when(projectRepository.save(any(Project.class))).thenReturn(project);
        boolean result = projectService.deleteProject(1L);
        assertTrue(result);
    }

    @Test
    void testDeleteProject_Success() {

        when(projectRepository.findByProjectIdAndIsDeletedFalse(anyLong())).thenReturn(Optional.of(project));
        boolean result = projectService.deleteProject(anyLong());
        assertTrue(result);
        assertTrue(project.isDeleted());
        verify(projectRepository, times(1)).save(project);
    }

}

