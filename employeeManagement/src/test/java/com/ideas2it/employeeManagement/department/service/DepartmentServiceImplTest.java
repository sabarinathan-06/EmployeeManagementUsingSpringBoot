package com.ideas2it.employeeManagement.department.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import com.ideas2it.employeeManagement.department.departmentDTO.DepartmentDTO;
import com.ideas2it.employeeManagement.department.respository.DepartmentRepository;
import com.ideas2it.employeeManagement.employee.employeeDTO.EmployeeDTO;
import com.ideas2it.employeeManagement.model.Department;
import com.ideas2it.employeeManagement.model.Employee;
import com.ideas2it.employeeManagement.model.Project;

public class DepartmentServiceImplTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    private Department department;
    private DepartmentDTO departmentDTO;
    private List<Department> departments;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        department = Department.builder()
                .departmentId(1L)
                .departmentName("IT")
                .build();

        departmentDTO = DepartmentDTO.builder()
                .departmentId(1L)
                .departmentName("IT")
                .build();

        departments = Arrays.asList(department);

        Project project1 = Project.builder()
                .projectId(1L)
                .projectName("HealthCare")
                .build();

        Project project2 = Project.builder()
                .projectId(2L)
                .projectName("Employee Management")
                .build();

        Employee employee1 = Employee.builder()
                .employeeId(1L)
                .employeeName("Deepak")
                .place("Chennai")
                .projects(Arrays.asList(project1, project2))
                .build();

        Employee employee2 = Employee.builder()
                .employeeId(2L)
                .employeeName("Sabri")
                .place("Chennai")
                .projects(Arrays.asList(project1, project2))
                .build();

        department.setEmployees(Arrays.asList(employee1, employee2));
    }

    @Test
    void testCreateDepartment() {
        when(departmentRepository.save(any(Department.class))).thenReturn(department);
        DepartmentDTO result = departmentService.createDepartment(departmentDTO);
        assertEquals(departmentDTO.getDepartmentId(), result.getDepartmentId());
    }

    @Test
    void testGetDepartmentById() {
        when(departmentRepository.findByDepartmentIdAndIsDeletedFalse(anyLong())).thenReturn(Optional.of(department));
        DepartmentDTO result = departmentService.getDepartmentById(1L);
        assertEquals(departmentDTO.getDepartmentId(), result.getDepartmentId());
    }

    @Test
    void testGetAllDepartments() {
        when(departmentRepository.findByIsDeletedFalse()).thenReturn(departments);
        List<DepartmentDTO> result = departmentService.getAllDepartments();
        assertEquals(1, result.size());
        assertEquals(departmentDTO.getDepartmentId(), result.getFirst().getDepartmentId());
    }

    @Test
    void testUpdateDepartment() {
        when(departmentRepository.findByDepartmentIdAndIsDeletedFalse(anyLong())).thenReturn(Optional.of(department));
        when(departmentRepository.save(any(Department.class))).thenReturn(department);
        DepartmentDTO result = departmentService.updateDepartment(departmentDTO);
        assertEquals(departmentDTO.getDepartmentName(), result.getDepartmentName());
    }

    @Test
    void testDeleteDepartment() {
        when(departmentRepository.findByDepartmentIdAndIsDeletedFalse(anyLong())).thenReturn(Optional.of(department));
        when(departmentRepository.save(any(Department.class))).thenReturn(department);
        boolean result = departmentService.deleteDepartment(1L);
        assertTrue(result);
    }

    @Test
    void testGetEmployeesByDepartment() {
        when(departmentRepository.findByDepartmentIdAndIsDeletedFalse(1L)).thenReturn(Optional.of(department));
        List<EmployeeDTO> result = departmentService.getEmployeesByDepartment(1L);
        assertEquals(2, result.size());
    }

}
