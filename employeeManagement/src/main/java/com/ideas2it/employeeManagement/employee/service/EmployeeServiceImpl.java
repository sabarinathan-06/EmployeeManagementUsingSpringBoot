package com.ideas2it.employeeManagement.employee.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ideas2it.employeeManagement.department.departmentDTO.DepartmentDTO;
import com.ideas2it.employeeManagement.employee.employeeDTO.EmployeeDTO;
import com.ideas2it.employeeManagement.employee.respository.EmployeeRepository;
import com.ideas2it.employeeManagement.mapper.DepartmentMapper;
import com.ideas2it.employeeManagement.mapper.EmployeeMapper;
import com.ideas2it.employeeManagement.mapper.ProjectMapper;
import com.ideas2it.employeeManagement.model.Department;
import com.ideas2it.employeeManagement.model.Employee;
import com.ideas2it.employeeManagement.model.Project;
import com.ideas2it.employeeManagement.project.projectDTO.ProjectDTO;

/**
 * <p>
 * Service implementation for managing Employee-related operations.
 * This class contains business logic for handling Employee entities, including
 * creation, retrieval, update, deletion, and assignment operations. It acts as
 * a bridge between the controller layer and the repository layer, ensuring that
 * business rules are applied before interacting with the database.
 * </p>
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        logger.debug("Creating employee with data: {}", employeeDTO);
        Employee employee = EmployeeMapper.convertToEntity(employeeDTO);
        Employee savedEmployee = employeeRepository.save(employee);
        EmployeeDTO createdEmployeeDto = EmployeeMapper.convertToDTO(savedEmployee);
        logger.info("Employee created with ID: {}", createdEmployeeDto.getEmployeeId());
        return createdEmployeeDto;
    }

    @Override
    public EmployeeDTO getEmployeeById(Long id) {
        logger.debug("Retrieving employee with ID: {}", id);
        Optional<Employee> employeeOptional = employeeRepository.findByEmployeeIdAndIsDeletedFalse(id);
        Employee employee = employeeOptional.orElseThrow(() -> new NoSuchElementException("Employee not found for ID: " + id));
        EmployeeDTO employeeDTO = EmployeeMapper.convertToDTO(employee);
        logger.info("Employee retrieved with ID: {}", id);
        return employeeDTO;
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        logger.debug("Retrieving all employees.");
        List<Employee> employees = employeeRepository.findByIsDeletedFalse();
        List<EmployeeDTO> employeeDTOs = new ArrayList<>();
        for (Employee employee : employees) {
            employeeDTOs.add(EmployeeMapper.convertToDTO(employee));
        }
        logger.info("Retrieved {} employees.", employeeDTOs.size());
        return employeeDTOs;
    }

    @Override
    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO) {
        logger.debug("Updating employee with ID: {}", employeeDTO.getEmployeeId());
        Employee existingEmployee = employeeRepository.findByEmployeeIdAndIsDeletedFalse(employeeDTO.getEmployeeId())
                .orElseThrow(() -> new NoSuchElementException("Employee not found for ID: " + employeeDTO.getEmployeeId()));
        Employee updatedEmployee = EmployeeMapper.convertToEntity(employeeDTO);
        existingEmployee.setEmployeeName(updatedEmployee.getEmployeeName());
        existingEmployee.setPlace(updatedEmployee.getPlace());
        existingEmployee.setDateOfBirth(updatedEmployee.getDateOfBirth());
        existingEmployee.setSalary(updatedEmployee.getSalary());
        existingEmployee.setExperience(updatedEmployee.getExperience());
        Employee savedEmployee = employeeRepository.save(existingEmployee);
        EmployeeDTO updatedEmployeeDTO = EmployeeMapper.convertToDTO(savedEmployee);
        logger.info("Employee updated with ID: {}", employeeDTO.getEmployeeId());
        return updatedEmployeeDTO;
    }

    @Override
    public boolean deleteEmployee(Long id) {
        logger.debug("Deleting employee with ID: {}", id);
        Employee employee = employeeRepository.findByEmployeeIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new NoSuchElementException("Employee not found for ID: " + id));
        if (employee == null) {
            logger.info("While deleting there is no Such Employee id : {}", id);
        }
        assert employee != null;
        employee.setDeleted(true);
        employeeRepository.save(employee);
        logger.info("Employee marked as deleted with ID: {}", id);
        return true;
    }

    @Override
    public EmployeeDTO assignEmployeeToDepartment(EmployeeDTO employeeDTO, DepartmentDTO departmentDTO) {
        logger.debug("Assigning employee with ID: {} to department with ID: {}", employeeDTO.getEmployeeId(), departmentDTO.getDepartmentId());
        Employee employee = EmployeeMapper.convertToEntity(employeeDTO);
        Department department = DepartmentMapper.convertToEntity(departmentDTO);
        employee.setDepartment(department);
        Employee savedEmployee = employeeRepository.save(employee);
        EmployeeDTO updatedEmployeeDTO = EmployeeMapper.convertToDTO(savedEmployee);
        logger.info("Employee assigned to department with ID: {}", departmentDTO.getDepartmentId());
        return updatedEmployeeDTO;
    }

    @Override
    public EmployeeDTO assignEmployeeToProject(EmployeeDTO employeeDTO, ProjectDTO projectDTO) {
        logger.debug("Assigning employee with ID: {} to project with ID: {}", employeeDTO.getEmployeeId(), projectDTO.getProjectId());
        Employee employee = EmployeeMapper.convertToEntity(employeeDTO);
        Project project = ProjectMapper.convertToEntity(projectDTO);
        List<Project> projects = employee.getProjects();
        if (projects == null) {
            projects = new ArrayList<>();
        }
        projects.add(project);
        employee.setProjects(projects);
        Employee savedEmployee = employeeRepository.save(employee);
        EmployeeDTO updatedEmployeeDTO = EmployeeMapper.convertToDTO(savedEmployee);
        logger.info("Employee assigned to project with ID: {}", projectDTO.getProjectId());
        return updatedEmployeeDTO;
    }
}
