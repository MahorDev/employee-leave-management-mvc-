package com.tekpy.leave.service;

import com.tekpy.leave.entity.Employee;
import com.tekpy.leave.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private static final Logger logger =
            LoggerFactory.getLogger(EmployeeService.class);

    private final EmployeeRepository employeeRepository;

    public EmployeeService(
            EmployeeRepository employeeRepository) {

        this.employeeRepository =
                employeeRepository;
    }

    public Employee login(
            String employeeId,
            String password) {

        if (employeeId == null ||
                employeeId.isBlank() ||
                password == null ||
                password.isBlank()) {

            return null;
        }

        Employee employee =
                employeeRepository
                        .findByEmployeeId(employeeId.trim());

        if (employee == null) {

            logger.info(
                    "Login failed for employee ID {}",
                    employeeId.trim());

            return null;
        }

        if (!employee.getPassword().equals(password)) {

            logger.info(
                    "Login failed for employee ID {}",
                    employeeId.trim());

            return null;
        }

        logger.info(
                "Login successful for employee ID {}",
                employee.getEmployeeId());

        return employee;
    }

    public Employee getEmployee(Long id) {

        return employeeRepository.findById(id);
    }
}
