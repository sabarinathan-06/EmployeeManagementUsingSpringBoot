package com.ideas2it.employeeManagement.employee.respository;

import com.ideas2it.employeeManagement.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for accessing Employee data from the database.
 *
 * <p>
 * This interface extends JpaRepository, providing CRUD operations for Employee entities.
 * It leverages Spring Data JPA to simplify data access and query execution.
 * Custom queries can be defined by adding method signatures that follow the naming
 * convention understood by Spring Data JPA.
 * </p>
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    /**
     * Retrieves a list of all employees that are not marked as deleted.
     *
     * <p>
     * This method uses a custom query derived from the method name to find all
     * employees with the `isDeleted` field set to `false`.
     * </p>
     *
     * @return a list of {@link Employee} entities that are not marked as deleted.
     */
    List<Employee> findByIsDeletedFalse();

    /**
     * Retrieves an employee by their unique employee ID, excluding those marked as deleted.
     *
     * <p>
     * This method uses a custom query derived from the method name to find an employee
     * with the specified ID and `isDeleted` field set to `false`.
     * </p>
     *
     * @param id the unique employee ID
     * @return an {@link Optional} containing the {@link Employee} if found, otherwise empty.
     */
    Optional<Employee> findByEmployeeIdAndIsDeletedFalse(Long id);

}
