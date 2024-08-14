package com.ideas2it.employeeManagement.department.respository;

import com.ideas2it.employeeManagement.model.Department;
import com.ideas2it.employeeManagement.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for accessing Department data from the database.
 *
 * <p>
 * This interface extends {@link JpaRepository}, providing CRUD operations for
 * {@link Department} entities. It leverages Spring Data JPA to simplify data
 * access and query execution. Custom queries can be defined by adding method
 * signatures that follow the naming convention understood by Spring Data JPA.
 * </p>
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    //String getDepartmentWithEmployees = "FROM Department d LEFT JOIN FETCH d.employees WHERE d.isDeleted = false AND d.employees.isDeleted = false AND d.id = :id";
    /**
     * Retrieves all non-deleted departments.
     *
     * @return A list of {@link Department} entities that are not marked as deleted.
     */
    List<Department> findByIsDeletedFalse();

    /**
     * Retrieves a department by its ID if it is not marked as deleted.
     *
     * @param departmentId The unique identifier of the department.
     * @return An {@link Optional} containing the department if found and not deleted,
     *         or an empty {@link Optional} if no such department exists.
     */
    Optional<Department> findByDepartmentIdAndIsDeletedFalse(Long departmentId);

    /**
     * Retrieves all employees associated with a specific department if they are not marked as deleted.
     *
     * @param id The unique identifier of the department.
     * @return A list of {@link Employee} entities associated with the department that are not marked as deleted.
     */
    //@Query(getDepartmentWithEmployees)@Param("id")
    //List<Employee> getDepartmentWithEmployees(Long id);
}
