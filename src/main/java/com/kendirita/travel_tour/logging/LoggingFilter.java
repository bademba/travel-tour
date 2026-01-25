package com.kendirita.travel_tour.logging;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
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

        long startTime = System.currentTimeMillis();
        filterChain.doFilter(requestWrapper, responseWrapper);
        long timeTaken = System.currentTimeMillis() - startTime;

        String requestBody = getStringValue(requestWrapper.getContentAsByteArray(), request.getCharacterEncoding());
        String responseBody = getStringValue(responseWrapper.getContentAsByteArray(), response.getCharacterEncoding());

        //retrieving responseId from the response and assigning it to log id
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(responseBody);
        String responseId=jsonNode.get("responseId").asText();

        //Get OS details
        String browserDetails =request.getHeader("User-Agent");
        final String lowerCaseBrowser =browserDetails.toLowerCase();
        if (lowerCaseBrowser.contains("windows")) {
            browserDetails="windows";
        } else if (lowerCaseBrowser.contains("mac")) {
            browserDetails="Mac";
        } else if (lowerCaseBrowser.contains("x11")) {
            browserDetails= "Unix";
        } else if (lowerCaseBrowser.contains("android")) {
            browserDetails= "Android";
        } else if (lowerCaseBrowser.contains("iphone")) {
            browserDetails= "IPhone";
        } else {
            browserDetails= "UnKnown, More-Info: " + browserDetails;
        }
        //end of OS details

        LOGGER.info(
                "REQUEST::"+"|logId="+responseId+"|Method="+ request.getMethod()+"| RequestURI=" +request.getRequestURI()+"|User-Agent="+request.getHeader("User-Agent")+"| OS="+browserDetails+"| RequestBody="+requestBody+"| ResponseCode="+ response.getStatus()+"| ResponseBody="+ responseBody
                        +"| TimeTaken(ms)="+timeTaken+"|SourceIP="+request.getRemoteAddr()+ " |RemotePort="+request.getRemotePort()+" |ServerName=" +request.getServerName() +"|RemoteHost="+request.getRemoteHost() );

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