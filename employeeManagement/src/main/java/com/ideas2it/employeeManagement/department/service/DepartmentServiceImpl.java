package com.ideas2it.employeeManagement.department.service;

import com.ideas2it.employeeManagement.department.departmentDTO.DepartmentDTO;
import com.ideas2it.employeeManagement.employee.employeeDTO.EmployeeDTO;
import com.ideas2it.employeeManagement.mapper.DepartmentMapper;
import com.ideas2it.employeeManagement.mapper.EmployeeMapper;
import com.ideas2it.employeeManagement.model.Department;
import com.ideas2it.employeeManagement.department.respository.DepartmentRepository;
import com.ideas2it.employeeManagement.model.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service implementation class that provides business logic for managing departments.
 * This class implements the {@link DepartmentService} interface and acts as an intermediary
 * between the controller layer and the repository layer for department-related operations.
 * It includes methods for creating, retrieving, updating, and deleting Department entities.
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    private static final Logger logger = LogManager.getLogger(DepartmentServiceImpl.class);

    @Override
    public DepartmentDTO createDepartment(DepartmentDTO departmentDTO) {
        logger.debug("Request to create department with name: {}", departmentDTO.getDepartmentName());
        Department department = DepartmentMapper.convertToEntity(departmentDTO);
        Department savedDepartment = departmentRepository.save(department);
        logger.info("Department created with ID: {}", savedDepartment.getDepartmentId());
        return DepartmentMapper.convertToDTO(savedDepartment);
    }

    @Override
    public DepartmentDTO getDepartmentById(Long id) {
        logger.debug("Request to get department with ID: {}", id);
        Department department = departmentRepository
                .findByDepartmentIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("Department not found for ID: " + id));
        logger.info("Department retrieved with ID: {}", department.getDepartmentId());
        return DepartmentMapper.convertToDTO(department);
    }

    @Override
    public List<DepartmentDTO> getAllDepartments() {
        logger.debug("Request to get all departments");
        List<Department> departments = departmentRepository.findByIsDeletedFalse();
        List<DepartmentDTO> departmentDTOs = new ArrayList<>();
        for (Department department : departments) {
            departmentDTOs.add(DepartmentMapper.convertToDTO(department));
        }
        logger.info("Retrieved {} departments", departmentDTOs.size());
        return departmentDTOs;
    }

    @Override
    public DepartmentDTO updateDepartment(Long id, DepartmentDTO departmentDTO) {
        logger.debug("Request to update department with ID: {}", id);
        Department department = DepartmentMapper.convertToEntity(getDepartmentById(id));
        department.setDepartmentName(departmentDTO.getDepartmentName());
        Department updatedDepartment = departmentRepository.save(department);
        logger.info("Department updated with ID: {}", updatedDepartment.getDepartmentId());
        return DepartmentMapper.convertToDTO(updatedDepartment);
    }

    @Override
    public void deleteDepartment(Long id) {
        logger.debug("Request to delete department with ID: {}", id);
        Department department = DepartmentMapper.convertToEntity(getDepartmentById(id));
        department.setDeleted(true);
        departmentRepository.save(department);
        logger.info("Department deleted with ID: {}", id);
    }

    @Override
    public List<EmployeeDTO> getEmployeesByDepartment(Long id) {
        logger.debug("Request to get employees by department ID: {}", id);
        Optional<Department> department = departmentRepository.findByDepartmentIdAndIsDeletedFalse(id);
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();
        for (Employee employee : department.get().getEmployees()) {
            employeeDTOS.add(EmployeeMapper.convertToDTO(employee));
        }
        logger.info("Retrieved {} employees for department ID: {}", employeeDTOS.size(), id);
        return employeeDTOS;
    }
}
