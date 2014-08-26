package com.mgomez.geolib.upload;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * @author: Marc Gomez / marc.gomez82 (at) gmail.com
 */
@WebServlet(urlPatterns = {"/upload"})
@MultipartConfig
public class FileUploadServlet extends HttpServlet {

    public static final int ERROR_CODE_NOT_MULTIPART = 406;
    public static final String MULTIPART_FORM_DATA_CONTENT_TYPE = "multipart/form-data";
    public static final String APPLICATION_JSON_CONTENT_TYPE = "application/json";

    private MultipartRequestHandler multipartRequestHandler;
    private ObjectMapper objectMapper;
    private Logger logger;

    @Inject
    public FileUploadServlet(MultipartRequestHandler multipartRequestHandler, ObjectMapper objectMapper, Logger logger) {
        this.multipartRequestHandler = multipartRequestHandler;
        this.objectMapper = objectMapper;
        this.logger = logger;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final String contentType = request.getContentType();
        if (!contentType.contains(MULTIPART_FORM_DATA_CONTENT_TYPE)) {
            logger.severe("Not multipart request: " + contentType);
            response.sendError(ERROR_CODE_NOT_MULTIPART, "Not multipart request: " + contentType);
            return;
        }

        response.setContentType(APPLICATION_JSON_CONTENT_TYPE);
        objectMapper.writeValue(response.getOutputStream(), multipartRequestHandler.handleUpload(request));
    }

}