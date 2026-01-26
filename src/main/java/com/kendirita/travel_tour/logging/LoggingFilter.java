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
        String responseBody = getStringValue(responseWrapper.getContentAsByteArray(), response.getCharacterEncoding());

        // 2️⃣ Try to extract responseId from response body, but fallback to logId
        ObjectMapper mapper = new ObjectMapper();
        try {
            if (responseBody != null && !responseBody.isEmpty()) {
                JsonNode jsonNode = mapper.readTree(responseBody);
                String responseId = jsonNode.path("responseId").asText(null);
                if (responseId != null && !responseId.isEmpty()) {
                    logId = responseId; // override logId if response has responseId
                }
            }
        } catch (Exception e) {
            // keep logId as the generated UUID if parsing fails
            LOGGER.warn("Failed to parse response body for responseId: {}", e.getMessage());
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