package com.ideas2it.employeeManagement.employee.controller;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ideas2it.employeeManagement.department.departmentDTO.DepartmentDTO;
import com.ideas2it.employeeManagement.department.service.DepartmentService;
import com.ideas2it.employeeManagement.employee.employeeDTO.EmployeeDTO;
import com.ideas2it.employeeManagement.employee.service.EmployeeService;
import com.ideas2it.employeeManagement.project.projectDTO.ProjectDTO;
import com.ideas2it.employeeManagement.project.service.ProjectService;

class EmployeeControllerTest {

    @InjectMocks
    private EmployeeController employeeController;

    @Mock
    private EmployeeService employeeService;

    @Mock
    private DepartmentService departmentService;

    @Mock
    private ProjectService projectService;

    private EmployeeDTO employeeDTO;
    private DepartmentDTO departmentDTO;
    private ProjectDTO projectDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

         employeeDTO = EmployeeDTO.builder()
                .employeeId(1L)
                .employeeName("Deepak")
                .place("Chennai")
                .build();

        departmentDTO = DepartmentDTO.builder()
                .departmentId(1L)
                .departmentName("IT")
                .build();

        projectDTO = ProjectDTO.builder()
                .projectId(1L)
                .projectName("Healthcare")
                .build();
    }

    @Test
    void testCreateEmployee() {
        when(employeeService.createEmployee(any(EmployeeDTO.class))).thenReturn(employeeDTO);

        ResponseEntity<EmployeeDTO> response = employeeController.createEmployee(employeeDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(employeeDTO, response.getBody());
        verify(employeeService, times(1)).createEmployee(any(EmployeeDTO.class));
    }

    @Test
    void testCreateEmployee_Negative() {
        when(employeeService.createEmployee(any(EmployeeDTO.class))).thenThrow(new RuntimeException("Employee creation failed"));

        try {
            employeeController.createEmployee(employeeDTO);
        } catch (Exception e) {
            assertEquals("Employee creation failed", e.getMessage());
        }

        verify(employeeService, times(1)).createEmployee(any(EmployeeDTO.class));
    }

    @Test
    void testGetEmployeeById() {
        when(employeeService.getEmployeeById(1L)).thenReturn(employeeDTO);

        ResponseEntity<EmployeeDTO> response = employeeController.getEmployeeById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employeeDTO, response.getBody());
        verify(employeeService, times(1)).getEmployeeById(1L);
    }

    @Test
    void testGetEmployeeById_Negative() {
        when(employeeService.getEmployeeById(1L)).thenThrow(new RuntimeException("Employee not found"));

        try {
            employeeController.getEmployeeById(1L);
        } catch (Exception e) {
            assertEquals("Employee not found", e.getMessage());
        }

        verify(employeeService, times(1)).getEmployeeById(1L);
    }

    @Test
    void testGetAllEmployees() {
        List<EmployeeDTO> employeeDTOs = new ArrayList<>();
        employeeDTOs.add(employeeDTO);
        when(employeeService.getAllEmployees()).thenReturn(employeeDTOs);

        ResponseEntity<List<EmployeeDTO>> response = employeeController.getAllEmployees();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employeeDTOs, response.getBody());
        verify(employeeService, times(1)).getAllEmployees();
    }

    @Test
    void testGetAllEmployees_Negative() {
        when(employeeService.getAllEmployees()).thenReturn(new ArrayList<>());

        ResponseEntity<List<EmployeeDTO>> response = employeeController.getAllEmployees();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(0, response.getBody().size());
        verify(employeeService, times(1)).getAllEmployees();
    }

    @Test
    void testUpdateEmployee() {
        when(employeeService.updateEmployee(any(EmployeeDTO.class))).thenReturn(employeeDTO);

        ResponseEntity<EmployeeDTO> response = employeeController.updateEmployee(1L, employeeDTO);

        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals(employeeDTO, response.getBody());
        verify(employeeService, times(1)).updateEmployee(any(EmployeeDTO.class));
    }

    @Test
    void testUpdateEmployee_Negative() {
        when(employeeService.updateEmployee(any(EmployeeDTO.class))).thenThrow(new RuntimeException("Update failed"));

        try {
            employeeController.updateEmployee(1L, employeeDTO);
        } catch (Exception e) {
            assertEquals("Update failed", e.getMessage());
        }

        verify(employeeService, times(1)).updateEmployee(any(EmployeeDTO.class));
    }

    @Test
    void testDeleteEmployee() {
        when(employeeService.deleteEmployee(1L)).thenReturn(true);

        ResponseEntity<Boolean> response = employeeController.deleteEmployee(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(employeeService, times(1)).deleteEmployee(1L);
    }

    @Test
    void testDeleteEmployee_Negative() {
        doThrow(new RuntimeException("Delete failed")).when(employeeService).deleteEmployee(1L);

        try {
            employeeController.deleteEmployee(1L);
        } catch (Exception e) {
            assertEquals("Delete failed", e.getMessage());
        }

        verify(employeeService, times(1)).deleteEmployee(1L);
    }

    @Test
    void testAssignEmployeeToDepartment() {
        when(employeeService.getEmployeeById(1L)).thenReturn(employeeDTO);
        when(departmentService.getDepartmentById(1L)).thenReturn(departmentDTO);
        when(employeeService.assignEmployeeToDepartment(employeeDTO, departmentDTO)).thenReturn(employeeDTO);

        ResponseEntity<EmployeeDTO> response = employeeController.assignEmployeeToDepartment(1L, 1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employeeDTO, response.getBody());
        verify(employeeService, times(1)).assignEmployeeToDepartment(employeeDTO, departmentDTO);
    }

    @Test
    void testAssignEmployeeToDepartment_Negative() {
        when(employeeService.getEmployeeById(1L)).thenReturn(null);

        ResponseEntity<EmployeeDTO> response = employeeController.assignEmployeeToDepartment(1L, 1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(employeeService, never()).assignEmployeeToDepartment(any(EmployeeDTO.class), any(DepartmentDTO.class));
    }

    @Test
    void testAssignEmployeeToProject() {
        when(employeeService.getEmployeeById(1L)).thenReturn(employeeDTO);
        when(projectService.getProjectById(1L)).thenReturn(projectDTO);
        when(employeeService.assignEmployeeToProject(employeeDTO, projectDTO)).thenReturn(employeeDTO);

        ResponseEntity<EmployeeDTO> response = employeeController.assignEmployeeToProject(1L, 1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employeeDTO, response.getBody());
        verify(employeeService, times(1)).assignEmployeeToProject(employeeDTO, projectDTO);
    }

    @Test
    void testAssignEmployeeToProject_Negative() {
        when(employeeService.getEmployeeById(1L)).thenReturn(null);

        ResponseEntity<EmployeeDTO> response = employeeController.assignEmployeeToProject(1L, 1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(employeeService, never()).assignEmployeeToProject(any(EmployeeDTO.class), any(ProjectDTO.class));
    }
}
