package com.tekpy.leave.controller;

import com.tekpy.leave.entity.LeaveRequest;
import com.tekpy.leave.service.LeaveService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class LeaveController {

    private final LeaveService leaveService;

    public LeaveController(LeaveService leaveService) {
        this.leaveService = leaveService;
    }

    @GetMapping("/apply-leave")
    public String applyPage(
            HttpSession session) {

        if (session.getAttribute("employeeId") == null) {
            return "redirect:/login";
        }

        return "apply-leave";
    }

    @PostMapping("/apply-leave")
    public String applyLeave(
            @RequestParam("leaveType") String leaveType,
            @RequestParam("fromDate") String fromDate,
            @RequestParam("toDate") String toDate,
            @RequestParam("reason") String reason,
            HttpSession session,
            Model model) {

        Long employeeId =
                (Long) session.getAttribute("employeeId");

        if (employeeId == null) {
            return "redirect:/login";
        }

        try {

            LocalDate startDate =
                    LocalDate.parse(fromDate);

            LocalDate endDate =
                    LocalDate.parse(toDate);

            String error =
                    leaveService.applyLeave(
                            employeeId,
                            leaveType,
                            startDate,
                            endDate,
                            reason);

            if (error != null) {

                model.addAttribute(
                        "error",
                        error);

                return "apply-leave";
            }

            model.addAttribute(
                    "success",
                    "Leave request submitted successfully.");

            return "redirect:/my-leaves";

        } catch (Exception e) {

            model.addAttribute(
                    "error",
                    "Unable to submit leave request.");

            return "apply-leave";
        }
    }

    @GetMapping("/my-leaves")
    public String myLeaves(
            HttpSession session,
            Model model) {

        Long employeeId =
                (Long) session.getAttribute("employeeId");

        if (employeeId == null) {
            return "redirect:/login";
        }

        List<LeaveRequest> requests =
                leaveService.getEmployeeLeaves(
                        employeeId);

        model.addAttribute(
                "requests",
                requests);

        return "leave-history";
    }

    @PostMapping("/preference")
    public String savePreference(
            @RequestParam("view") String view,
            HttpServletResponse response,
            HttpSession session) {

        if (session.getAttribute("employeeId") == null) {
            return "redirect:/login";
        }

        if (!"summary".equals(view) &&
                !"detailed".equals(view)) {

            view = "summary";
        }

        Cookie cookie =
                new Cookie("leaveView", view);

        cookie.setMaxAge(
                60 * 60 * 24 * 30);

        cookie.setHttpOnly(true);

        response.addCookie(cookie);

        return "redirect:/dashboard";
    }
}
