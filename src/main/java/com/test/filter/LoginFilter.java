package com.test.filter;

import com.test.common.RequestHolder;
import com.test.model.po.SysUser;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Created by:chenxu
 * @Created date:2021/1/20 21:43
 */
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    //filter all requests, if there is user who had login, put user in the threadLocal
    //if not ,go to the login page
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        SysUser sysUser = (SysUser) request.getSession().getAttribute("user");
        if (sysUser==null){
            String path = "/signin.jsp";
            response.sendRedirect(path);
            return;
        }
        RequestHolder.add(sysUser);
        RequestHolder.add(request);

        filterChain.doFilter(servletRequest,servletResponse);
        return;

    }

    @Override
    public void destroy() {

    }
}
