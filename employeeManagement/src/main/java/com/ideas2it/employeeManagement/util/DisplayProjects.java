package com.ideas2it.employeeManagement.util;

import com.ideas2it.employeeManagement.model.Employee;
import com.ideas2it.employeeManagement.model.Project;

/**
 * Utility class for displaying project information related to an employee.
 *
 * <p>
 * This class provides a method to format and concatenate project names associated with an employee
 * into a single string, separated by semicolons. It helps in presenting the project data in a readable format.
 * </p>
 */
public class DisplayProjects {

    /**
     * Concatenates the names of all projects associated with the given employee into a single string.
     *
     * @param employee {@link Employee} The employee whose projects are to be displayed.
     * @return A string containing the names of all projects, separated by semicolons.
     */
    public static String displayProject(Employee employee) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Project project : employee.getProjects()) {
            stringBuilder.append(project.getProjectName()).append("; ");
        }
        return stringBuilder.toString();
    }
}
