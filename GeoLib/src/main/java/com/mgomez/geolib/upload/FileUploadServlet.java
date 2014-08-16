package com.mgomez.geolib.upload;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.mgomez.geolib.file.boundary.GeoFileService;
import com.mgomez.geolib.file.entity.GeoFile;
import org.apache.commons.io.IOUtils;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author: Marc Gomez / marc.gomez82 (at) gmail.com
 */
@WebServlet(urlPatterns = {"/upload"})
@MultipartConfig
public class FileUploadServlet extends HttpServlet {

    // name of form fields which are looked up in multipart request
    public static final String INPUT_NAME = "file";
    private static final Logger logger = Logger.getLogger(FileUploadServlet.class.getName());
    @Inject
    private GeoFileService geoFileService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String contentType = request.getContentType();
        if (!contentType.contains("multipart/form-data")) {
            logger.severe("Not multipart request: " + contentType);
            response.sendError(406, "Not multipart request: " + contentType);
            return;
        }

        List<GeoFile> uploadedFields = Lists.newArrayList();
        for (Part filePart : request.getParts()) {
            if (filePart.getContentType() != null) {
                final String fileName = getFileName(filePart);
                byte[] content = IOUtils.toByteArray(filePart.getInputStream());

                final GeoFile geoFile = new GeoFile(fileName, content);
                logger.info("Adding file: " + geoFile);
                geoFileService.addFile(geoFile);
                uploadedFields.add(geoFile);
            }
        }

        response.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), uploadedFields);
    }


    private String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");
        logger.log(Level.INFO, "Part Header = {0}", partHeader);
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
}