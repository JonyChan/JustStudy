package com.test.controller;

import com.test.model.po.SysUser;
import com.test.service.SysUserService;
import com.test.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Created by:chenxu
 * @Created date:2021/1/18 18:51
 */
@Controller
public class UserController {

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping("/logout.page")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getSession().invalidate();

        String path = "signin.jsp";
        response.sendRedirect(path);
    }

    @RequestMapping("/login.page")
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        SysUser sysUser = sysUserService.findByKeyword(username);
        String errorMsg = "";
        String ret = request.getParameter("ret");

        if (StringUtils.isBlank(username)){
            errorMsg = "username can not be null";
        }else if (StringUtils.isBlank(password)){
            errorMsg = "password can not be null";
        }else if (sysUser == null){
            errorMsg = "user can not be found";
        }else if (! sysUser.getPassword().equals(MD5Util.encrypt(password))){
            errorMsg = "password not true";
        }else if (sysUser.getStatus()!=1){
            errorMsg = "user had closed";
        }else {
            //login success
            request.getSession().setAttribute("user",sysUser);
            if (StringUtils.isNoneBlank(ret)){
                response.sendRedirect(ret);
            }else {
                response.sendRedirect("/admin/index.page");
            }
        }

        request.setAttribute("error",errorMsg);
        request.setAttribute("username",username);
        if (StringUtils.isNoneBlank(ret)){
            request.setAttribute("ret",ret);
        }
        String path = "signin.jsp";
        request.getRequestDispatcher(path).forward(request,response);

    }
}
