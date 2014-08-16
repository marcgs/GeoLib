package com.mgomez.geolib.upload;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.mgomez.geolib.file.boundary.GeoFileService;
import com.mgomez.geolib.file.entity.GeoFile;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author: Marc Gomez / marc.gomez82 (at) gmail.com
 */
@WebServlet(urlPatterns = {"/upload"})
@MultipartConfig
public class FileUploadServlet extends HttpServlet {

    public static final String MULTIPART_FORM_DATA_CONTENT_TYPE = "multipart/form-data";
    public static final String APPLICATION_JSON_CONTENT_TYPE = "application/json";

    private static final Logger logger = Logger.getLogger(FileUploadServlet.class.getName());

    @Inject
    private GeoFileService geoFileService;
    @Inject
    private MultipartRequestHandler multipartRequestHandler;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final String contentType = request.getContentType();
        if (!contentType.contains(MULTIPART_FORM_DATA_CONTENT_TYPE)) {
            logger.severe("Not multipart request: " + contentType);
            response.sendError(406, "Not multipart request: " + contentType);
            return;
        }

        List<GeoFile> files = Lists.newArrayList();
        files.addAll(multipartRequestHandler.handleUpload(request));
        response.setContentType(APPLICATION_JSON_CONTENT_TYPE);
        new ObjectMapper().writeValue(response.getOutputStream(), files);
    }


}