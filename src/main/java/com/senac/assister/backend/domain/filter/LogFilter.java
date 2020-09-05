package com.senac.assister.backend.domain.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LogFilter implements Filter {

    Logger logger = LoggerFactory.getLogger(LogFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        long start = System.currentTimeMillis();

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String httpMethod = req.getMethod();
        String endpoint = req.getRequestURI();

        logger.info(String.format("m=%s message= %s", httpMethod + " " + endpoint, " Request started."));

        chain.doFilter(request, response);

        long status = (long) res.getStatus();
        long totalTime = System.currentTimeMillis() - start;

        logger.info(String.format("m=%s message= %s in: %d ms", httpMethod + " " + endpoint, " Request ended with status " + status, totalTime));
    }
}
