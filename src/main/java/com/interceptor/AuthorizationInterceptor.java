package com.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: gxy
 * @CreateDate: 2020/1/13 18:03
 * @Description:
 */
@Component
@Slf4j
public class AuthorizationInterceptor implements HandlerInterceptor {
    @Value("${static.path}")
    private String path;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.debug("  进入拦截器, 请求url : {}", request.getServletPath());
        String urlPath = request.getServletPath();

        //  配置静态路径
        if (request.getServletContext().getAttribute("basePath") ==  null){
            //String staticPath = Config.getValue("static.path");
            String staticPath = path;
            log.debug("配置静态资源地址 :" + staticPath);
            request.getServletContext().setAttribute("basePath", staticPath);
        }

        // 白名单
        String[] whiteArr = new String[]{"/loginUI", "/login", "/upload","/overtime"};
        for (String s : whiteArr) {
            if (urlPath.equals(s)){
                return true;
            }
        }

        //获取请求地址
        String tempContextUrl = request.getServerName();
        log.debug("获取请求域名tempContextUrl：{}", tempContextUrl);
        return true;

    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}