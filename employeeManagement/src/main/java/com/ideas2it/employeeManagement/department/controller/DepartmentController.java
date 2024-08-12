package com.ideas2it.employeeManagement.department.controller;

import com.ideas2it.employeeManagement.department.departmentDTO.DepartmentDTO;
import com.ideas2it.employeeManagement.mapper.DepartmentMapper;
import com.ideas2it.employeeManagement.model.Department;
import com.ideas2it.employeeManagement.department.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping
    public DepartmentDTO createDepartment(@RequestBody DepartmentDTO departmentDTO) {
        return DepartmentMapper.convertToDTO(departmentService.createDepartment(DepartmentMapper.convertToEntity(departmentDTO)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable Long id) {
        try {
            DepartmentDTO departmentDTO = DepartmentMapper.convertToDTO(departmentService.getDepartmentById(id));
            return new ResponseEntity<>(departmentDTO, HttpStatus.OK);
        } catch (RuntimeException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public List<DepartmentDTO> getAllDepartments() {
        List<DepartmentDTO> departmentDTOs = new ArrayList<>();
        List<Department> departments = departmentService.getAllDepartments();
        for (Department department : departments) {
            departmentDTOs.add(DepartmentMapper.convertToDTO(department));
        }
        return departmentDTOs;
    }

    @PutMapping("/{id}")
    public DepartmentDTO updateDepartment(@PathVariable Long id, @RequestBody DepartmentDTO departmentDTO) {
        return DepartmentMapper.convertToDTO(departmentService.updateDepartment(id, DepartmentMapper.convertToEntity(departmentDTO)));
    }

    @DeleteMapping("/{id}")
    public void deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
    }
}

