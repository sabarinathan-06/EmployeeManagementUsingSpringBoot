package com.ideas2it.employeeManagement.project.controller;

import com.ideas2it.employeeManagement.project.projectDTO.ProjectDTO;
import com.ideas2it.employeeManagement.project.service.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class ProjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ProjectService projectService;

    @InjectMocks
    private ProjectController projectController;

    @Autowired
    private ObjectMapper objectMapper = new ObjectMapper();

    private ProjectDTO projectDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(projectController).build();

        projectDTO = ProjectDTO.builder()
                .projectId(1L)
                .projectName("Healthcare")
                .build();

    }

    @Test
    void testCreateProject() throws Exception{
        when(projectService.createProject(any(ProjectDTO.class))).thenReturn(projectDTO);

        mockMvc.perform(post("/api/projects")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(projectDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.projectId").value(1L))
                .andExpect(jsonPath("$.projectName").value("Healthcare"));
        verify(projectService, times(1)).createProject(any(ProjectDTO.class));
    }

    @Test
    void testGetProjectById() {
        Long projectId = 1L;
        when(projectService.getProjectById(projectId)).thenReturn(projectDTO);

        ResponseEntity<ProjectDTO> response = projectController.getProjectById(projectId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(projectDTO, response.getBody());
    }

    @Test
    void testGetAllProjects() {
        List<ProjectDTO> projectDTOs = Arrays.asList(new ProjectDTO(1L, "Project 1"), new ProjectDTO(2L, "Project 2"));

        when(projectService.getAllProjects()).thenReturn(projectDTOs);

        ResponseEntity<List<ProjectDTO>> responseEntity = projectController.getAllProjects();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(2, responseEntity.getBody().size());
        assertEquals("Project 1", responseEntity.getBody().getFirst().getProjectName());
    }

    @Test
    void testUpdateProject() {
        ProjectDTO projectDTO = new ProjectDTO(1L, "Updated Project");

        when(projectService.updateProject(any(ProjectDTO.class))).thenReturn(projectDTO);

        ResponseEntity<ProjectDTO> responseEntity = projectController.updateProject(1L, projectDTO);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals("Updated Project", responseEntity.getBody().getProjectName());
    }

    @Test
    void testDeleteProject() {
        when(projectService.deleteProject(1L)).thenReturn(true);

        ResponseEntity<Boolean> responseEntity = projectController.deleteProject(1L);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        assertEquals(Boolean.TRUE, responseEntity.getBody());
    }
}

