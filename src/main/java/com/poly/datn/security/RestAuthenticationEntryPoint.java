package com.poly.datn.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse,
                         AuthenticationException e) throws IOException, ServletException {
        log.error("Responding with unauthorized error. Message - {}", e.getMessage());
        
        // ! return json for 401 - unauthorized - (not the best option now)
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> mess = new HashMap<>();mess.put("message", "Authentication information is missing or invalid.");
        String responseMsg = mapper.writeValueAsString(mess);
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpServletResponse.getWriter().write(responseMsg);
       
        // httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED,
        //         e.getLocalizedMessage());
    }
}
