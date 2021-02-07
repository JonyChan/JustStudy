package com.test.filter;

import com.google.common.base.Splitter;
import com.google.common.collect.Sets;
import com.test.common.ApplicationContextHelper;
import com.test.common.JsonData;
import com.test.common.RequestHolder;
import com.test.model.po.SysUser;
import com.test.service.SysCoreService;
import com.test.util.JsonMapper;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Created by:chenxu
 * @Created date:2021/2/4 17:08
 */
@Slf4j
public class AclControlFilter implements Filter {

    private static Set<String> exclusionUrlSet = Sets.newConcurrentHashSet();

    private final static String noAuthUrl = "/sys/user/noAuth.page";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //定义白名单
        String exclusionUrls = filterConfig.getInitParameter("exclusions");
        List<String> exclusionUrl = Splitter.on(",").trimResults().omitEmptyStrings().splitToList(exclusionUrls);
        exclusionUrlSet = Sets.newConcurrentHashSet(exclusionUrl);
        exclusionUrlSet.add(noAuthUrl);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String path = request.getServletPath();
        Map resultMap = request.getParameterMap();

        //处理白名单
        if (exclusionUrlSet.contains(path)){
            filterChain.doFilter(servletRequest,servletResponse);
        }

        SysUser sysUser = RequestHolder.getCurrentUser();
        if (sysUser == null){
            noAuth(request,response);
             log.info("someone visit {}, but not login, parameter {}",path, JsonMapper.obj2String(resultMap));
             return;
        }
        SysCoreService sysCoreService = ApplicationContextHelper.popBean(SysCoreService.class);
        if (sysCoreService.hasAclUrl(path)){
            noAuth(request,response);
            log.info("{} visit {}, but not login, parameter {}",JsonMapper.obj2String(sysUser),path, JsonMapper.obj2String(resultMap));
            return;
        }
        filterChain.doFilter(servletRequest,servletResponse);
        return;
    }

    private void noAuth(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String path = request.getServletPath();
        if(path.endsWith(".json")){
            JsonData jsonData = JsonData.fail("noAuth");
            response.setHeader("Content-Type","application/json");
            response.getWriter().print(JsonMapper.obj2String(jsonData));
            return;
        }else {
            clientRedirect(path,response);
            return;
        }
    }

    private void clientRedirect(String path,HttpServletResponse response) throws IOException {
        response.setHeader("Content-Type","text/html");
        response.getWriter().print("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\n"
                + "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" + "<head>\n" + "<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\"/>\n"
                + "<title>跳转中...</title>\n" + "</head>\n" + "<body>\n" + "跳转中，请稍候...\n" + "<script type=\"text/javascript\">//<![CDATA[\n"
                + "window.location.href='" + path + "?ret='+encodeURIComponent(window.location.href);\n" + "//]]></script>\n" + "</body>\n" + "</html>\n");
    }

    @Override
    public void destroy() {

    }
}
