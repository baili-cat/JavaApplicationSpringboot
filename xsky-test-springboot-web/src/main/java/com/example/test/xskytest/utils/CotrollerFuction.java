package com.example.test.xskytest.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.*;

@Slf4j
@Component
public class CotrollerFuction {

    public Map<String, String> printMessage(HttpServletRequest httpRequest) {

        log.info("请示时间：{}", String.valueOf(Calendar.getInstance().getTimeInMillis()));

        //转换 http请求参数转换成Map对象
        Map<String, String> parameterMap = new HashMap<>();
        Enumeration<String> params = httpRequest.getParameterNames();
        String element;
        while (params.hasMoreElements()) {
            element = params.nextElement();

            log.info("name:{}  value:{}", element, httpRequest.getParameter(element));
            parameterMap.put(element, httpRequest.getParameter(element));
        }
        //请求方式

        log.debug("getMethod：{}", httpRequest.getMethod());
        log.debug("getRequestURI：{}", httpRequest.getRequestURI());
        log.debug("getRequestURL：{}", httpRequest.getRequestURL());
        log.debug("getRemoteHost:{}", httpRequest.getRemoteHost());
        log.debug("getRemoteUser:{}", httpRequest.getRemoteUser());
        log.debug("getProtocol:{}", httpRequest.getProtocol());
        log.debug("getQueryString:{}", httpRequest.getQueryString());

        //Header部分
        log.info("Http文件头:{}", httpRequest.getHeaderNames());

        return parameterMap;
    }

    public Map<String, String> printMessage(HttpServletRequest httpRequest, RedirectAttributes attr) {
        //转换 http请求参数转换成Map对象
        Map<String, String> parameterMap = new HashMap<>();
        Enumeration<String> params = httpRequest.getParameterNames();
        String element;
        while (params.hasMoreElements()) {
            element = params.nextElement();

            log.info("name:{}  value:{}", element, httpRequest.getParameter(element));
            attr.addAttribute(element, httpRequest.getParameter(element));
            parameterMap.put(element, httpRequest.getParameter(element));

        }
        //请求方式

        log.info("getMethod：{}", httpRequest.getMethod());
        log.info("getRequestURI：{}", httpRequest.getRequestURI());
        log.info("getRequestURL：{}", httpRequest.getRequestURL());
        log.info("getRemoteHost:{}", httpRequest.getRemoteHost());
        log.info("getRemoteUser:{}", httpRequest.getRemoteUser());
        log.info("getProtocol:{}", httpRequest.getProtocol());
        log.info("getQueryString:{}", httpRequest.getQueryString());

        //Header部分
        log.info("Http文件头:{}", httpRequest.getHeaderNames());

        Enumeration<?> enum1 = httpRequest.getHeaderNames();
        while (enum1.hasMoreElements()) {
            String key = (String) enum1.nextElement();
            String value = httpRequest.getHeader(key);
            log.info("key:{} , value:{}", key, value);
        }

        return parameterMap;
    }


    public HttpServletRequest getHttpServletRequest() {
        HttpServletRequest httpServletRequest = new HttpServletRequest() {
            @Override
            public String getAuthType() {
                return "定时任务自定义httpServletRequest";
            }

            @Override
            public Cookie[] getCookies() {
                return new Cookie[0];
            }

            @Override
            public long getDateHeader(String s) {
                return 0;
            }

            @Override
            public String getHeader(String s) {
                return null;
            }

            @Override
            public Enumeration<String> getHeaders(String s) {
                return null;
            }

            @Override
            public Enumeration<String> getHeaderNames() {
                return null;
            }

            @Override
            public int getIntHeader(String s) {
                return 0;
            }

            @Override
            public String getMethod() {
                return null;
            }

            @Override
            public String getPathInfo() {
                return null;
            }

            @Override
            public String getPathTranslated() {
                return null;
            }

            @Override
            public String getContextPath() {
                return null;
            }

            @Override
            public String getQueryString() {
                return null;
            }

            @Override
            public String getRemoteUser() {
                return null;
            }

            @Override
            public boolean isUserInRole(String s) {
                return false;
            }

            @Override
            public Principal getUserPrincipal() {
                return null;
            }

            @Override
            public String getRequestedSessionId() {
                return null;
            }

            @Override
            public String getRequestURI() {
                return null;
            }

            @Override
            public StringBuffer getRequestURL() {
                return null;
            }

            @Override
            public String getServletPath() {
                return null;
            }

            @Override
            public HttpSession getSession(boolean b) {
                return null;
            }

            @Override
            public HttpSession getSession() {
                return null;
            }

            @Override
            public String changeSessionId() {
                return null;
            }

            @Override
            public boolean isRequestedSessionIdValid() {
                return false;
            }

            @Override
            public boolean isRequestedSessionIdFromCookie() {
                return false;
            }

            @Override
            public boolean isRequestedSessionIdFromURL() {
                return false;
            }

            @Override
            public boolean isRequestedSessionIdFromUrl() {
                return false;
            }

            @Override
            public boolean authenticate(HttpServletResponse httpServletResponse) throws IOException, ServletException {
                return false;
            }

            @Override
            public void login(String s, String s1) throws ServletException {

            }

            @Override
            public void logout() throws ServletException {

            }

            @Override
            public Collection<Part> getParts() throws IOException, ServletException {
                return null;
            }

            @Override
            public Part getPart(String s) throws IOException, ServletException {
                return null;
            }

            @Override
            public <T extends HttpUpgradeHandler> T upgrade(Class<T> aClass) throws IOException, ServletException {
                return null;
            }

            @Override
            public Object getAttribute(String s) {
                return null;
            }

            @Override
            public Enumeration<String> getAttributeNames() {
                return null;
            }

            @Override
            public String getCharacterEncoding() {
                return null;
            }

            @Override
            public void setCharacterEncoding(String s) throws UnsupportedEncodingException {

            }

            @Override
            public int getContentLength() {
                return 0;
            }

            @Override
            public long getContentLengthLong() {
                return 0;
            }

            @Override
            public String getContentType() {
                return null;
            }

            @Override
            public ServletInputStream getInputStream() throws IOException {
                return null;
            }

            @Override
            public String getParameter(String s) {
                return null;
            }

            @Override
            public Enumeration<String> getParameterNames() {
                Enumeration<String> params = new Enumeration<String>() {
                    @Override
                    public boolean hasMoreElements() {
                        return false;
                    }

                    @Override
                    public String nextElement() {
                        return null;
                    }
                };
                return params;
            }

            @Override
            public String[] getParameterValues(String s) {
                return new String[0];
            }

            @Override
            public Map<String, String[]> getParameterMap() {
                return null;
            }

            @Override
            public String getProtocol() {
                return null;
            }

            @Override
            public String getScheme() {
                return null;
            }

            @Override
            public String getServerName() {
                return null;
            }

            @Override
            public int getServerPort() {
                return 0;
            }

            @Override
            public BufferedReader getReader() throws IOException {
                return null;
            }

            @Override
            public String getRemoteAddr() {
                return null;
            }

            @Override
            public String getRemoteHost() {
                return null;
            }

            @Override
            public void setAttribute(String s, Object o) {

            }

            @Override
            public void removeAttribute(String s) {

            }

            @Override
            public Locale getLocale() {
                return null;
            }

            @Override
            public Enumeration<Locale> getLocales() {
                return null;
            }

            @Override
            public boolean isSecure() {
                return false;
            }

            @Override
            public RequestDispatcher getRequestDispatcher(String s) {
                return null;
            }

            @Override
            public String getRealPath(String s) {
                return null;
            }

            @Override
            public int getRemotePort() {
                return 0;
            }

            @Override
            public String getLocalName() {
                return null;
            }

            @Override
            public String getLocalAddr() {
                return null;
            }

            @Override
            public int getLocalPort() {
                return 0;
            }

            @Override
            public ServletContext getServletContext() {
                return null;
            }

            @Override
            public AsyncContext startAsync() throws IllegalStateException {
                return null;
            }

            @Override
            public AsyncContext startAsync(ServletRequest servletRequest, ServletResponse servletResponse) throws IllegalStateException {
                return null;
            }

            @Override
            public boolean isAsyncStarted() {
                return false;
            }

            @Override
            public boolean isAsyncSupported() {
                return false;
            }

            @Override
            public AsyncContext getAsyncContext() {
                return null;
            }

            @Override
            public DispatcherType getDispatcherType() {
                return null;
            }
        };
        return httpServletRequest;
    }
}
