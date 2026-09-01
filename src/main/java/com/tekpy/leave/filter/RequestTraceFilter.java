package com.tekpy.leave.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class RequestTraceFilter implements Filter {

    private static final Logger logger =
            LoggerFactory.getLogger(RequestTraceFilter.class);

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest =
                (HttpServletRequest) request;

        long start = System.currentTimeMillis();

        logger.info(
                "Request started: {} {}",
                httpRequest.getMethod(),
                httpRequest.getRequestURI());

        try {

            chain.doFilter(request, response);

        } finally {

            long duration =
                    System.currentTimeMillis() - start;

            logger.info(
                    "Request completed: {} {} in {} ms",
                    httpRequest.getMethod(),
                    httpRequest.getRequestURI(),
                    duration);
        }
    }
}
