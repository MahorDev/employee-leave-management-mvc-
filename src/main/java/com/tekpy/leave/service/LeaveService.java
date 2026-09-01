package com.tekpy.leave.service;

import com.tekpy.leave.entity.Employee;
import com.tekpy.leave.entity.LeaveRequest;
import com.tekpy.leave.repository.EmployeeRepository;
import com.tekpy.leave.repository.LeaveRequestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
public class LeaveService {

    private static final Logger logger =
            LoggerFactory.getLogger(LeaveService.class);

    private static final Set<String> VALID_TYPES =
            Set.of("CASUAL", "SICK", "EARNED");

    private final LeaveRequestRepository leaveRepository;
    private final EmployeeRepository employeeRepository;

    public LeaveService(
            LeaveRequestRepository leaveRepository,
            EmployeeRepository employeeRepository) {

        this.leaveRepository = leaveRepository;
        this.employeeRepository = employeeRepository;
    }

    public String applyLeave(
            Long employeeId,
            String leaveType,
            LocalDate fromDate,
            LocalDate toDate,
            String reason) {

        try {

            if (leaveType == null ||
                    leaveType.isBlank()) {

                return "Leave type is required.";
            }

            if (fromDate == null) {

                return "From date is required.";
            }

            if (toDate == null) {

                return "To date is required.";
            }

            if (reason == null ||
                    reason.isBlank()) {

                return "Reason is required.";
            }

            leaveType =
                    leaveType.trim().toUpperCase();

            if (!VALID_TYPES.contains(leaveType)) {

                return "Invalid leave type.";
            }

            if (fromDate.isAfter(toDate)) {

                return "From date cannot be after To date.";
            }

            if (fromDate.isBefore(LocalDate.now())) {

                return "Leave cannot start in the past.";
            }

            int numberOfDays =
                    calculateWorkingDays(
                            fromDate,
                            toDate);

            if (numberOfDays <= 0) {

                return "Leave must contain at least one working day.";
            }

            Employee employee =
                    employeeRepository.findById(employeeId);

            if (employee == null) {

                return "Employee not found.";
            }

            if (numberOfDays >
                    employee.getLeaveBalance()) {

                return "Insufficient leave balance.";
            }

            /*
             * A new request is PENDING.
             * We do not deduct the balance at this point.
             * This keeps the pending request and balance
             * consistent until an approval operation exists.
             */

            LeaveRequest request =
                    new LeaveRequest();

            request.setEmployee(employee);
            request.setLeaveType(leaveType);
            request.setFromDate(fromDate);
            request.setToDate(toDate);
            request.setNumberOfDays(numberOfDays);
            request.setReason(reason.trim());
            request.setStatus("PENDING");
            request.setCreatedDate(LocalDate.now());

            leaveRepository.save(request);

            logger.info(
                    "Leave request created for employee {}",
                    employee.getEmployeeId());

            return null;

        } catch (Exception e) {

            logger.error(
                    "Leave request creation failed",
                    e);

            return "Unable to submit leave request.";
        }
    }

    private int calculateWorkingDays(
            LocalDate fromDate,
            LocalDate toDate) {

        int days = 0;

        LocalDate current = fromDate;

        while (!current.isAfter(toDate)) {

            DayOfWeek day =
                    current.getDayOfWeek();

            if (day != DayOfWeek.SATURDAY &&
                    day != DayOfWeek.SUNDAY) {

                days++;
            }

            current = current.plusDays(1);
        }

        return days;
    }

    public List<LeaveRequest> getEmployeeLeaves(
            Long employeeId) {

        return leaveRepository
                .findByEmployeeId(employeeId);
    }

    public long countPending(Long employeeId) {

        return leaveRepository
                .countByEmployeeAndStatus(
                        employeeId,
                        "PENDING");
    }

    public long countApproved(Long employeeId) {

        return leaveRepository
                .countByEmployeeAndStatus(
                        employeeId,
                        "APPROVED");
    }
}
