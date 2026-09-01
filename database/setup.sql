CREATE DATABASE IF NOT EXISTS employeeLeave;

USE employeeLeave;

-- Hibernate creates/updates the employee and leave_request tables.
-- Run this only to create the database itself.

-- Test employee for evaluator:
INSERT INTO employee
(employeeId, name, department, password, leaveBalance)
VALUES
('EMP001', 'Jaikishan', 'IT', 'admin', 12);
