package com.demo.Common.Filter;

import com.auth0.jwt.interfaces.Claim;
import com.demo.Common.KeyHelper.KeyHelper;
import com.demo.Common.UserContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;


public class TokenFilter implements Filter {

    @Autowired
    private UserContext userContext;

    @Override
    public void init(FilterConfig filterConfig) {
        // TODO Auto-generated method stub
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                filterConfig.getServletContext());

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String auth = httpRequest.getHeader("Authorization");
        if ((auth != null) && (auth.length() > 0)) {
            Map<String,Claim> token = KeyHelper.verifyJWT(auth);
            if (!token.isEmpty()) {
                UserContext context = new UserContext();
                if(token.containsKey("UserName"))context.setUserName(token.get("UserName").asString());
                if(token.containsKey("UserId"))context.setUserId(token.get("UserId").asString());
                if(token.containsKey("DepartmentId"))context.setDepartmentId(token.get("DepartmentId").asString());
                if(token.containsKey("DepartmentName"))context.setDepartmentName(token.get("DepartmentName").asString());
                userContext.setUserContext(context);

                chain.doFilter(request, response);
                return;
            }
        }

        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setCharacterEncoding("UTF-8");
        httpResponse.setContentType("application/json; charset=utf-8");
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }
}