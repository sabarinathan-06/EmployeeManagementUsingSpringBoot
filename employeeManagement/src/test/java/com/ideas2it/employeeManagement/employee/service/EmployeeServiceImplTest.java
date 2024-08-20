package com.ideas2it.employeeManagement.employee.service;

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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ideas2it.employeeManagement.employee.employeeDTO.EmployeeDTO;
import com.ideas2it.employeeManagement.employee.respository.EmployeeRepository;
import com.ideas2it.employeeManagement.model.Department;
import com.ideas2it.employeeManagement.model.Employee;
import com.ideas2it.employeeManagement.model.Project;

public class EmployeeServiceImplTest {
    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Employee employee;
    private EmployeeDTO employeeDTO;
    private List<Employee> employees;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        Department department = Department.builder()
                .departmentId(1L)
                .departmentName("IT")
                .build();

        Project project1 = Project.builder()
                .projectId(1L)
                .projectName("HealthCare")
                .build();

        Project project2 = Project.builder()
                .projectId(2L)
                .projectName("Employee Management")
                .build();

        employee = Employee.builder()
                .employeeId(1L)
                .employeeName("Sabari")
                .department(department)
                .projects(Arrays.asList(project1, project2))
                .build();

        employeeDTO = EmployeeDTO.builder()
                .employeeId(1L)
                .employeeName("Sabari")
                .departmentName("IT")
                .projectName("HealthCare")
                .build();

        employees = Arrays.asList(employee);

    }

    @Test
    void testCreateEmployee() {
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
        EmployeeDTO result = employeeService.createEmployee(employeeDTO);
        assertEquals(employeeDTO.getEmployeeId(), result.getEmployeeId());
    }

    @Test
    void testGetEmployeeById() {
        when(employeeRepository.findByEmployeeIdAndIsDeletedFalse(anyLong())).thenReturn(Optional.of(employee));
        EmployeeDTO result = employeeService.getEmployeeById(1L);
        assertEquals(employeeDTO.getEmployeeId(), result.getEmployeeId());
    }

    @Test
    void testGetAllEmployees() {
        when(employeeRepository.findByIsDeletedFalse()).thenReturn(employees);
        List<EmployeeDTO> result = employeeService.getAllEmployees();
        assertEquals(1, result.size());
        assertEquals(employeeDTO.getEmployeeId(), result.getFirst().getEmployeeId());
    }

    @Test
    void testUpdateEmployee() {
        when(employeeRepository.findByEmployeeIdAndIsDeletedFalse(anyLong())).thenReturn(Optional.of(employee));
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
        EmployeeDTO result = employeeService.updateEmployee(employeeDTO);
        assertEquals(employeeDTO.getEmployeeName(), result.getEmployeeName());
    }

    @Test
    void testDeleteEmployee() {
        when(employeeRepository.findByEmployeeIdAndIsDeletedFalse(employee.getEmployeeId())).thenReturn(Optional.of(employee));
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        boolean result = employeeService.deleteEmployee(1L);

        assertTrue(result);
        verify(employeeRepository, times(1)).save(employee);
    }

//    @Test
//    void testAssignEmployeeToDepartment() {
//        DepartmentDTO departmentDTO = DepartmentDTO.builder()
//                .departmentId(1L)
//                .departmentName("IT")
//                .build();
//
//        Employee updatedEmployee = employee;
//        updatedEmployee.setDepartment(EmployeeMapper.convertToEntity(departmentDTO));
//
//        when(employeeRepository.save(any(Employee.class))).thenReturn(updatedEmployee);
//
//        EmployeeDTO updatedEmployeeDTO = employeeService.assignEmployeeToDepartment(employeeDTO, departmentDTO);
//
//        assertEquals(departmentDTO.getDepartmentId(), updatedEmployeeDTO.getDepartmentId());
//        verify(employeeRepository, times(1)).save(any(Employee.class));
//    }
//
//    @Test
//    void testAssignEmployeeToProject() {
//        ProjectDTO projectDTO = ProjectDTO.builder()
//                .projectId(1L)
//                .projectName("Project 1")
//                .build();
//
//        List<Project> projects = new ArrayList<>();
//        projects.add(ProjectMapper.convertToEntity(projectDTO));
//        employee.setProjects(projects);
//
//        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
//
//        EmployeeDTO updatedEmployeeDTO = employeeService.assignEmployeeToProject(employeeDTO, projectDTO);
//
//        assertEquals(1, updatedEmployeeDTO.getProjects().size());
//        assertEquals(projectDTO.getProjectId(), updatedEmployeeDTO.getProjects().get(0).getProjectId());
//        verify(employeeRepository, times(1)).save(any(Employee.class));
//    }

}
