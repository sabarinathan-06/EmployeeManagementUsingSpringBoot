package com.ideas2it.employeeManagement.department.controller;

import com.ideas2it.employeeManagement.department.departmentDTO.DepartmentDTO;
import com.ideas2it.employeeManagement.department.service.DepartmentService;
import com.ideas2it.employeeManagement.employee.employeeDTO.EmployeeDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class DepartmentControllerTest {

    @InjectMocks
    private DepartmentController departmentController;

    @Mock
    private DepartmentService departmentService;

    private DepartmentDTO departmentDTO;
    private EmployeeDTO employeeDTO;

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
    }

    @Test
    void testCreateDepartment() {
        when(departmentService.createDepartment(any(DepartmentDTO.class))).thenReturn(departmentDTO);

        ResponseEntity<DepartmentDTO> response = departmentController.createDepartment(departmentDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(departmentDTO, response.getBody());
        verify(departmentService, times(1)).createDepartment(any(DepartmentDTO.class));
    }

    @Test
    void testCreateDepartment_Negative() {
        when(departmentService.createDepartment(any(DepartmentDTO.class))).thenThrow(new RuntimeException("Department creation failed"));

        try {
            departmentController.createDepartment(departmentDTO);
        } catch (Exception e) {
            assertEquals("Department creation failed", e.getMessage());
        }

        verify(departmentService, times(1)).createDepartment(any(DepartmentDTO.class));
    }

    @Test
    void testGetDepartmentById() {
        when(departmentService.getDepartmentById(1L)).thenReturn(departmentDTO);

        ResponseEntity<DepartmentDTO> response = departmentController.getDepartmentById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(departmentDTO, response.getBody());
        verify(departmentService, times(1)).getDepartmentById(1L);
    }

    @Test
    void testGetDepartmentById_Negative() {
        when(departmentService.getDepartmentById(1L)).thenThrow(new RuntimeException("Department not found"));

        ResponseEntity<DepartmentDTO> response = departmentController.getDepartmentById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(departmentService, times(1)).getDepartmentById(1L);
    }

    @Test
    void testGetAllDepartments() {
        List<DepartmentDTO> departmentDTOs = new ArrayList<>();
        departmentDTOs.add(departmentDTO);
        when(departmentService.getAllDepartments()).thenReturn(departmentDTOs);

        ResponseEntity<List<DepartmentDTO>> response = departmentController.getAllDepartments();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(departmentDTOs, response.getBody());
        verify(departmentService, times(1)).getAllDepartments();
    }

    @Test
    void testGetAllDepartments_Negative() {
        when(departmentService.getAllDepartments()).thenReturn(new ArrayList<>());

        ResponseEntity<List<DepartmentDTO>> response = departmentController.getAllDepartments();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(0, response.getBody().size());
        verify(departmentService, times(1)).getAllDepartments();
    }

    @Test
    void testUpdateDepartment() {
        when(departmentService.updateDepartment(any(DepartmentDTO.class))).thenReturn(departmentDTO);

        ResponseEntity<DepartmentDTO> response = departmentController.updateDepartment(1L, departmentDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(departmentDTO, response.getBody());
        verify(departmentService, times(1)).updateDepartment(any(DepartmentDTO.class));
    }

    @Test
    void testUpdateDepartment_Negative() {
        when(departmentService.updateDepartment(any(DepartmentDTO.class))).thenThrow(new RuntimeException("Update failed"));

        try {
            departmentController.updateDepartment(1L, departmentDTO);
        } catch (Exception e) {
            assertEquals("Update failed", e.getMessage());
        }

        verify(departmentService, times(1)).updateDepartment(any(DepartmentDTO.class));
    }

    @Test
    void testDeleteDepartment() {
        when(departmentService.deleteDepartment(1L)).thenReturn(true);

        ResponseEntity<Boolean> response = departmentController.deleteDepartment(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(departmentService, times(1)).deleteDepartment(1L);
    }

    @Test
    void testDeleteDepartment_Negative() {
        when(departmentService.deleteDepartment(1L)).thenThrow(new RuntimeException("Delete failed"));

        try {
            departmentController.deleteDepartment(1L);
        } catch (Exception e) {
            assertEquals("Delete failed", e.getMessage());
        }

        verify(departmentService, times(1)).deleteDepartment(1L);
    }

    @Test
    void testGetEmployeesByDepartment() {
        List<EmployeeDTO> employeeDTOs = new ArrayList<>();
        employeeDTOs.add(employeeDTO);
        when(departmentService.getEmployeesByDepartment(1L)).thenReturn(employeeDTOs);

        ResponseEntity<List<EmployeeDTO>> response = departmentController.getEmployeesByDepartment(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employeeDTOs, response.getBody());
        verify(departmentService, times(1)).getEmployeesByDepartment(1L);
    }

    @Test
    void testGetEmployeesByDepartment_Negative() {
        when(departmentService.getEmployeesByDepartment(1L)).thenReturn(new ArrayList<>());

        ResponseEntity<List<EmployeeDTO>> response = departmentController.getEmployeesByDepartment(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(0, response.getBody().size());
        verify(departmentService, times(1)).getEmployeesByDepartment(1L);
    }
}

