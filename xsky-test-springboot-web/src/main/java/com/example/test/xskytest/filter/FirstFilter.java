package com.example.test.xskytest.filter;


import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by admin on 2020/9/1.
 */

@Slf4j
@WebFilter(filterName = "firstFilter", urlPatterns = "/*")
public class FirstFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        log.info("拦截器添加 Hearders");
/*        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest)servletRequest;
    String origin = request.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Origin", origin);
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Authentication, content-type");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setCharacterEncoding("UTF-8");
        log.info(response.getOutputStream().toString());*/
        //response.setCharacterEncoding("gbk");

      /*  if(request.getRequestURI().equals("/utf-8/getJson")){
            log.info("gb2312");
            response.setContentType("application/json");
            response.setCharacterEncoding("gbk");
        }*/

    /*   if(request.getRequestURI().equals("/gb2312/getJson")){
           log.info("gb2312");
           response.setContentType("application/json;charset=gb2312");
           response.setCharacterEncoding("gb2312");
       }
       else if(request.getRequestURI().equals("/gbk/getJson")){
           log.info("gbk");
           response.setContentType("application/json;charset=gbk");
           response.setCharacterEncoding("gbk");
       }
       else if(request.getRequestURI().equals("/utf-8/getJson")){
           log.info("utf-8");
           response.setCharacterEncoding("utf-8");
       }*/
       /* if(method.equalsIgnoreCase("OPTIONS")){
            servletResponse.getOutputStream().write("Success".getBytes("utf-8"));
        }else{*/
        //log.info(((HttpServletRequest) servletRequest).getRequestURI());
        // log.info(((HttpServletRequest) servletRequest).getRequestURL().toString());
        filterChain.doFilter(servletRequest, servletResponse);
        // }
    }

    @Override
    public void destroy() {

    }
}

