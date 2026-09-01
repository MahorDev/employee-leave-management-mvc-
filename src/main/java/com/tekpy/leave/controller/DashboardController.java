package com.tekpy.leave.controller;

import com.tekpy.leave.entity.Employee;
import com.tekpy.leave.service.EmployeeService;
import com.tekpy.leave.service.LeaveService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    private final EmployeeService employeeService;
    private final LeaveService leaveService;
    private final ServletContext servletContext;

    public DashboardController(
            EmployeeService employeeService,
            LeaveService leaveService,
            ServletContext servletContext) {

        this.employeeService = employeeService;
        this.leaveService = leaveService;
        this.servletContext = servletContext;
    }

    @GetMapping("/dashboard")
    public String dashboard(
            HttpSession session,
            HttpServletRequest request,
            Model model) {

        Long employeeId =
                (Long) session.getAttribute("employeeId");

        if (employeeId == null) {

            return "redirect:/login";
        }

        Employee employee =
                employeeService.getEmployee(employeeId);

        if (employee == null) {

            session.invalidate();

            return "redirect:/login";
        }

        String viewPreference =
                readViewPreference(request);

        model.addAttribute(
                "employee",
                employee);

        model.addAttribute(
                "pendingCount",
                leaveService.countPending(employeeId));

        model.addAttribute(
                "approvedCount",
                leaveService.countApproved(employeeId));

        model.addAttribute(
                "appName",
                servletContext.getAttribute("appName"));

        model.addAttribute(
                "appVersion",
                servletContext.getAttribute("appVersion"));

        model.addAttribute(
                "viewPreference",
                viewPreference);

        return "dashboard";
    }

    private String readViewPreference(
            HttpServletRequest request) {

        Cookie[] cookies = request.getCookies();

        if (cookies == null) {
            return "summary";
        }

        for (Cookie cookie : cookies) {

            if ("leaveView".equals(cookie.getName())) {

                if ("detailed".equals(cookie.getValue())) {
                    return "detailed";
                }

                return "summary";
            }
        }

        return "summary";
    }
}
