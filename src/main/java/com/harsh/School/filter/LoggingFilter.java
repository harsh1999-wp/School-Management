package com.harsh.School.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

// filter is provided by servlet
// we need casting Servlet to http

//@Component
public class LoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain ) throws IOException, ServletException {
        // filter chain use to connect filter and inform other filter when current filter task completed.

        System.out.println("Request enter in logging filter");

        chain.doFilter(request, response);

        HttpServletRequest httpRequest =
                (HttpServletRequest) request;

        HttpServletResponse httpResponse =
                (HttpServletResponse) response;

        //logging Request
        System.out.println("Incoming Request" + httpRequest.getRequestURI()
                + " Incoming Method  " + httpRequest.getMethod() + " ");


        chain.doFilter(request, response);

        // logging Response

        System.out.println("response status" + httpResponse.getStatus());
        System.out.println("Request Exiting  from Logging filter");


    }
}

