package com.harsh.School.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;

@Component
public class ResponseBodyFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        ContentCachingResponseWrapper wrapperResponse = new ContentCachingResponseWrapper(httpResponse);

        chain.doFilter(request , response);

        // getting data into byte

        byte [] originalBodyBytes = wrapperResponse.getContentAsByteArray();

        //need to convert into string

        String originalBody = new String(originalBodyBytes);

        String modifiedBody =
                """
                {
                "original body" : %s,
                "app-Name" : Student-Management-App
                }
                """.formatted(originalBody);

        wrapperResponse.resetBuffer();

        wrapperResponse.getWriter().write(modifiedBody);

        wrapperResponse.copyBodyToResponse();


    }
}
