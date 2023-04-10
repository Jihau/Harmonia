package com.harmonia.backend.config;

import com.harmonia.backend.constants.HarmoniaConstants;
import com.harmonia.backend.utils.HarmoniaUtils;
import com.harmonia.backend.utils.KeyValidator;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class APIKeyValidatorFilter implements Filter {
    @Autowired
    KeyValidator keyValidator;
    @Value("${harmonia.constants.protectEndpoints}")
    private boolean protectEndpoints;

    @Value("${harmonia.constants.exposed.endpoints}")
    private String exposedEndpoints;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        if (!protectEndpoints || shouldNotFilter(req)) {
            chain.doFilter(request, response);
        } else {
            String key = req.getHeader(HarmoniaConstants.API_KEY_HEADER_NAME);
            if (key == null) {
                ((HttpServletResponse) response).setStatus(401);
                response.getOutputStream().write("API Key is missing!".getBytes());
                return;
            }
            if (!keyValidator.validateAPIKey(key)) {
                ((HttpServletResponse) response).setStatus(403);
                response.getOutputStream().write("API Key is invalid".getBytes());
                return;
            }
            chain.doFilter(request, response);
        }
    }

    private boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        String[] exposedResources = exposedEndpoints.split(",");
        return HarmoniaUtils.arrayMatchesString(exposedResources, path);
    }

}