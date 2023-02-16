package com.example.test.component;

import com.example.test.util.Result;
import org.json.JSONObject;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class RestfulAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException{
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        //response.getWriter().println("Access Denied RestfulAccessDeniedHandler");
        JSONObject json = new JSONObject(Result.forbid());
        response.getWriter().println(json);
        response.getWriter().flush();
    }

}
