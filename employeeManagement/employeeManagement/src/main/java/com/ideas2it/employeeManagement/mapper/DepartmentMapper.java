package com.ideas2it.employeeManagement.mapper;

import com.ideas2it.employeeManagement.department.departmentDTO.DepartmentDTO;
import com.ideas2it.employeeManagement.model.Department;

public class DepartmentMapper {

    public static DepartmentDTO convertToDTO(Department department) {
        return DepartmentDTO.builder()
                .departmentId(department.getDepartmentId())
                .departmentName(department.getDepartmentName())
                .build();
    }

    public static Department convertToEntity(DepartmentDTO departmentDTO) {
        return Department.builder()
                .departmentId(departmentDTO.getDepartmentId())
                .departmentName(departmentDTO.getDepartmentName())
                .build();
    }
}
