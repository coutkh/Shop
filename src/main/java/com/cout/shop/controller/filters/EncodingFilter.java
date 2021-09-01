package com.cout.shop.controller.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
@WebFilter
public class EncodingFilter implements Filter {
    private String encoding;

    @Override
    public void init(FilterConfig config){
        encoding = config.getInitParameter("requestEncoding");
        if (encoding == null) {
            encoding = "UTF-8";
        }
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        if (null == servletRequest.getCharacterEncoding()) {
            servletRequest.setCharacterEncoding(encoding);
        }

        // Set the default response content type and encoding
        servletResponse.setContentType("text/html; charset=UTF-8");
        servletResponse.setCharacterEncoding("UTF-8");

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
