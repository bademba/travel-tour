package com.kendirita.travel_tour.logging;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.UUID;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

@Component
public class LoggingFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingFilter.class);


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        ContentCachingRequestWrapper requestWrapper =
                new ContentCachingRequestWrapper(request, 1024 * 1024);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);

        // 1️⃣ Generate a requestId for logging
        String logId = UUID.randomUUID().toString();

        long startTime = System.currentTimeMillis();
        filterChain.doFilter(requestWrapper, responseWrapper);
        long timeTaken = System.currentTimeMillis() - startTime;

        String requestBody = getStringValue(requestWrapper.getContentAsByteArray(), request.getCharacterEncoding());

        try {
            ObjectMapper mapper = new ObjectMapper();
            if(requestBody != null && !requestBody.isBlank()) {
                JsonNode jsonNode = mapper.readTree(requestBody);
                requestBody = mapper.writeValueAsString(jsonNode); // compact single-line
            }
        } catch (Exception e) {
            }

        String responseBody = getStringValue(responseWrapper.getContentAsByteArray(), response.getCharacterEncoding());
        try {
            ObjectMapper mapper = new ObjectMapper();
            if(responseBody != null && !responseBody.isBlank()) {
                JsonNode jsonNode = mapper.readTree(responseBody);
                responseBody = mapper.writeValueAsString(jsonNode);
            }
        } catch (Exception e) {
            }

        // 3️⃣ OS detection (keep your existing code)
        String browserDetails = request.getHeader("User-Agent");
        final String lowerCaseBrowser = browserDetails.toLowerCase();
        if (lowerCaseBrowser.contains("windows")) {
            browserDetails = "Windows";
        } else if (lowerCaseBrowser.contains("mac")) {
            browserDetails = "Mac";
        } else if (lowerCaseBrowser.contains("x11")) {
            browserDetails = "Unix";
        } else if (lowerCaseBrowser.contains("android")) {
            browserDetails = "Android";
        } else if (lowerCaseBrowser.contains("iphone")) {
            browserDetails = "IPhone";
        } else {
            browserDetails = "Unknown, More-Info: " + browserDetails;
        }

        // 4️⃣ Logging
        LOGGER.info(
                "REQUEST::|logId={} |Method={} |RequestURI={} |User-Agent={} |OS={} |RequestBody={} |ResponseCode={} |ResponseBody={} |TimeTaken(ms)={} |SourceIP={} |RemotePort={} |ServerName={} |RemoteHost={}",
                logId, request.getMethod(), request.getRequestURI(), request.getHeader("User-Agent"),
                browserDetails, requestBody, response.getStatus(), responseBody, timeTaken,
                request.getRemoteAddr(), request.getRemotePort(), request.getServerName(), request.getRemoteHost()
        );

        responseWrapper.copyBodyToResponse();
    }

    private String getStringValue(byte[] contentAsByteArray, String characterEncoding) {
        try {
            return new String(contentAsByteArray, 0, contentAsByteArray.length, characterEncoding);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

}