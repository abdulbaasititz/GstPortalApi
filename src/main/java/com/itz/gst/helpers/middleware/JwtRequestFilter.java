package com.itz.gst.helpers.middleware;


import com.itz.gst.helpers.common.calc.DateTimeCalc;
import com.itz.gst.helpers.utils.JwtUtil;
import com.itz.gst.use_cases.aaa_module.auth.AuthService;
import io.jsonwebtoken.ExpiredJwtException;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private AuthService authService;
    @Autowired
    private JwtUtil jwtTokenUtil;
    @Value("${spring.credential.login-path}")
    private String[] loginPath;
    @Value("${spring.credential.security-check}")
    private Boolean securityCheck;
//    @Value("${spring.credential.swagger-path}")
//    private String[] swaggerPath;
//    @Value("${spring.credential.actuator-path}")
//    private String[] actuator;

    boolean containsEqualsIgnoreCase(String[] c, String s) {
        for (String str : c) {
            if (s.equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain chain)
            throws ServletException, IOException {
        String userName = null;
        String jwtToken = null;
        String requestTokenHeader = request.getHeader("Authorization");
        String passUrl = request.getRequestURI();

        if (!securityCheck ||
                containsEqualsIgnoreCase(loginPath, passUrl)) {
            chain.doFilter(request, response);
            return;
        }

        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);
            try {
                userName = jwtTokenUtil.getUsernameFromToken(jwtToken);
            } catch (ExpiredJwtException e) {
                String dateTime = new DateTimeCalc().getTodayDateTime();
                response.setStatus(401);
                response.getWriter().println("{\"message\" :\"Token Expired\",\"dateTime\":\"" + dateTime + "\"}");
                response.setContentType("application/json");
                return;
            } catch (Exception e) {
                String dateTime = new DateTimeCalc().getTodayDateTime();
                response.setStatus(401);
                response.getWriter().println("{\"message\" :\"Invalid Auth Token\",\"dateTime\":\"" + dateTime + "\"}");
                response.setContentType("application/json");
                return;
            }
        }
        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = this.authService.loadUserByUsername(userName);
            if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
//        if(requestTokenHeader == null){
//            String dateTime = new DateTimeCalc().getTodayDateTime();
//            response.setStatus(401);
//            response.getWriter().println("{\"message\" :\"No Token\",\"dateTime\":\"" + dateTime + "\"}");
//            response.setContentType("application/json");
//            return;
//        }
        chain.doFilter(request, response);
    }
}