package com.ideas2it.employeeManagement.department.service;

import com.ideas2it.employeeManagement.model.Department;

import java.util.List;

public interface DepartmentService {
    Department createDepartment(Department department);

    Department getDepartmentById(Long id);

    List<Department> getAllDepartments();

    Department updateDepartment(Long id, Department departmentDetails);

    void deleteDepartment(Long id);
}
