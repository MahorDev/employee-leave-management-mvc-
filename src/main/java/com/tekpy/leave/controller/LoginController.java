package com.tekpy.leave.controller;

import com.tekpy.leave.entity.Employee;
import com.tekpy.leave.service.EmployeeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    private final EmployeeService employeeService;

    public LoginController(
            EmployeeService employeeService) {

        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public String home() {
        return "login";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(
            @RequestParam("employeeId") String employeeId,
            @RequestParam("password") String password,
            HttpSession session,
            Model model) {

        Employee employee =
                employeeService.login(
                        employeeId,
                        password);

        if (employee == null) {

            model.addAttribute(
                    "error",
                    "Invalid Employee ID or Password");

            return "login";
        }

        session.setAttribute(
                "employeeId",
                employee.getId());

        return "redirect:/dashboard";
    }

    @GetMapping("/logout")
    public String logout(
            HttpSession session) {

        session.invalidate();

        return "redirect:/login";
    }
}
