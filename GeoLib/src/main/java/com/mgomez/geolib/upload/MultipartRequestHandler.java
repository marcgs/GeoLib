package com.mgomez.geolib.upload;

import com.mgomez.geolib.file.boundary.GeoFileService;
import com.mgomez.geolib.file.entity.GeoFile;
import org.apache.commons.io.IOUtils;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class MultipartRequestHandler {

    @Inject
    private GeoFileService geoFileService;

    public List<GeoFile> handleUpload(HttpServletRequest request) throws IOException, ServletException {
        List<GeoFile> files = new LinkedList<GeoFile>();
        request.getParts().stream().filter(part -> part.getContentType() != null).forEach(part -> {
            final byte[] content = getContent(part);
            final GeoFile file = new GeoFile(getFilename(part), content);
            geoFileService.addFile(file);
            files.add(file);
        });
        return files;
    }

    private byte[] getContent(Part part) {
        final byte[] content;
        try {
            content = IOUtils.toByteArray(part.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return content;
    }

    private String getFilename(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
            }
        }
        return null;
    }
}
