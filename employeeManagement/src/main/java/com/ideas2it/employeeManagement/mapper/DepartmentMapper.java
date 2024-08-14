package com.ideas2it.employeeManagement.mapper;

import com.ideas2it.employeeManagement.department.departmentDTO.DepartmentDTO;
import com.ideas2it.employeeManagement.model.Department;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Utility class for mapping between Department and DepartmentDTO.
 *
 * <p>
 * This class provides static methods for converting between Department entities
 * and their corresponding Data Transfer Objects (DTOs). It facilitates the
 * conversion process needed for interacting with the service and controller layers
 * while keeping the domain model and DTOs separate.
 * </p>
 */
public class DepartmentMapper {

    private static final Logger logger = LogManager.getLogger(DepartmentMapper.class);

    /**
     * Converts a Department entity to a DepartmentDTO.
     *
     * @param department {@link Department} The Department entity to be converted.
     * @return {@link DepartmentDTO} The corresponding DTO with department data.
     */
    public static DepartmentDTO convertToDTO(Department department) {
        logger.debug("Converting Department entity to DepartmentDTO for department ID: {}",
                department.getDepartmentId());
        DepartmentDTO departmentDTO = DepartmentDTO.builder()
                .departmentId(department.getDepartmentId())
                .departmentName(department.getDepartmentName())
                .build();
        logger.info("Converted Department entity to DepartmentDTO with department ID: {}",
                departmentDTO.getDepartmentId());
        return departmentDTO;
    }

    /**
     * Converts a DepartmentDTO to a Department entity.
     *
     * @param departmentDTO {@link DepartmentDTO} The DTO to be converted.
     * @return {@link Department} The corresponding Department entity.
     */
    public static Department convertToEntity(DepartmentDTO departmentDTO) {
        logger.debug("Converting DepartmentDTO to Department entity for department ID: {}",
                departmentDTO.getDepartmentId());
        Department department = Department.builder()
                .departmentId(departmentDTO.getDepartmentId())
                .departmentName(departmentDTO.getDepartmentName())
                .build();
        logger.info("Converted DepartmentDTO to Department entity with department ID: {}",
                department.getDepartmentId());
        return department;
    }
}
