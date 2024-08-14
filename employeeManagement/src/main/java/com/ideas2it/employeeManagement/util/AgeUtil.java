package com.ideas2it.employeeManagement.util;

import java.time.LocalDate;
import java.time.Period;

/**
 * Utility class for common operations related to dates.
 *
 * <p>
 * This class provides static methods for calculating age and other date-related utilities.
 * It is designed to be stateless and thread-safe, with all methods being static.
 * </p>
 */
public class AgeUtil {

    /**
     * Calculates the age in years and months from the given date of birth.
     *
     * @param dateOfBirth The date of birth of the person.
     * @return A string representing the age in years and months.
     */
    public static String calculateAge(LocalDate dateOfBirth) {
        LocalDate now = LocalDate.now();
        Period period = Period.between(dateOfBirth, now);

        int years = period.getYears();
        int months = period.getMonths();

        return String.format("%d years and %d months", years, months);
    }
}

