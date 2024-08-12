package com.ideas2it.employeeManagement.employee.respository;

import com.ideas2it.employeeManagement.model.Employee;
import com.ideas2it.employeeManagement.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 * Repository interface for accessing Employee data from the database.
 * This interface extends JpaRepository, providing CRUD operations for Employee entities.
 * It leverages Spring Data JPA to simplify data access and query execution.
 * Custom queries can be defined by adding method signatures that follow the naming
 * convention understood by Spring Data JPA.
 * </p>
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByIsDeletedFalse();

    Optional<Employee> findByEmployeeIdAndIsDeletedFalse(Long id);

}
