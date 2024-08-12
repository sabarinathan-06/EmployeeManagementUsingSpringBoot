package com.ideas2it.employeeManagement.department.service;

import com.ideas2it.employeeManagement.model.Department;
import com.ideas2it.employeeManagement.department.respository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department getDepartmentById(Long id) {
        Optional<Department> departmentOptional = departmentRepository.findByDepartmentIdAndIsDeletedFalse(id);
        return departmentOptional.orElseThrow(() -> new RuntimeException("Department not found for ID: " + id));
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findByIsDeletedFalse();
    }

    @Override
    public Department updateDepartment(Long id, Department departmentDetails) {
        Department department = getDepartmentById(id);
        department.setDepartmentName(departmentDetails.getDepartmentName());
        return departmentRepository.save(department);
    }

    @Override
    public void deleteDepartment(Long id) {
        Department department = getDepartmentById(id);
        department.setDeleted(true);
        departmentRepository.save(department);
    }
}
