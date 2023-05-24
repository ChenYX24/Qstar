package com.wenjuan.wenjuandemo.filter;

import jakarta.servlet.*;

import java.io.IOException;


public class filterdomo implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("hello");

    }

    @Override

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        
    }

    @Override
    public void destroy() {
        System.out.println("bye");
    }
}
