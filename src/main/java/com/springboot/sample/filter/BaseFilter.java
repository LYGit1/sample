package com.springboot.sample.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class BaseFilter implements Filter {
    private static final Logger LOG = LoggerFactory.getLogger(BaseFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long starttime = System.currentTimeMillis();
        filterChain.doFilter(servletRequest,servletResponse);
        long interval = System.currentTimeMillis() - starttime;
        StringBuffer sb = new StringBuffer("[");
        sb.append(((HttpServletRequest)servletRequest).getRequestURL());
        if(!StringUtils.isEmpty(((HttpServletRequest)servletRequest).getQueryString()))
            sb.append("?").append(((HttpServletRequest)servletRequest).getQueryString());
        sb.append("] execute time = ");
        sb.append(interval);
        LOG.info(sb.toString());
    }

    @Override
    public void destroy() {

    }
}
