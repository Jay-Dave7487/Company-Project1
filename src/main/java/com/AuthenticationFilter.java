//package com;
//import java.io.IOException;
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//@WebFilter("/LogoutServlet")
//public class AuthenticationFilter implements Filter {
//
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        HttpServletResponse httpResponse = (HttpServletResponse) response;
//
//        HttpSession session = httpRequest.getSession(false);
//
//        if (session == null || session.getAttribute("user") == null) {
//            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.jsp");
//        } else {
//            chain.doFilter(request, response);
//        }
//    }
//
//    public void destroy() {
//    }
//
//    public void init(FilterConfig fConfig) throws ServletException {
//    }
//}
