package com.an.maphouse.security.ajax_login;

import com.an.maphouse.base.ApiResponse;
import com.an.maphouse.base.ApiResponseEnum;
import com.google.gson.Gson;
import com.an.maphouse.base.ApiResponse;
import com.an.maphouse.base.ApiResponseEnum;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 无权访问
 * @author Annan An
 * @date 2020/5/8 14:15
 */
@Component
public class AjaxAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        httpServletResponse.setCharacterEncoding("UTF-8");//设置服务器的编码，默认是ISO-8859-1
        httpServletResponse.setContentType("application/json; charset = utf-8");//告诉浏览器服务器的编码格式
        httpServletResponse.getWriter().write(new Gson().toJson(ApiResponse.ofStatus(ApiResponseEnum.NO_PRIORITY_ERROR)));
    }
}