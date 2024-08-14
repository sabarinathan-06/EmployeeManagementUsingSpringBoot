package com.ideas2it.employeeManagement.project.respository;

import com.ideas2it.employeeManagement.model.Employee;
import com.ideas2it.employeeManagement.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for accessing Project data.
 *
 * <p>
 * This interface extends JpaRepository, providing methods to perform CRUD operations
 * on Project entities. It includes a custom query method to find non-deleted projects.
 * Spring Data JPA will automatically implement this interface, allowing for easy
 * and efficient data access.
 * </p>
 */
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    /**
     * Finds all projects that have not been marked as deleted.
     *
     * @return A list of non-deleted projects.
     */
    List<Project> findByIsDeletedFalse();

    /**
     * Finds a project by its ID, considering its deletion status.
     *
     * @param id The unique ID of the project.
     * @return An {@link Optional} containing the project if found and not deleted,
     *         or an empty {@link Optional} if no matching project is found or it is deleted.
     */
    Optional<Project> findByProjectIdAndIsDeletedFalse(Long id);
}
