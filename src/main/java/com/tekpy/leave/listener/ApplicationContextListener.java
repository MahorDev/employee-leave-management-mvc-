package com.tekpy.leave.listener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class ApplicationContextListener
        implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {

        ServletContext context =
                event.getServletContext();

        context.setAttribute(
                "appName",
                "employee-leave-management");

        context.setAttribute(
                "appVersion",
                "1.0");
    }
}
