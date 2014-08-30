package com.mgomez.geolib.upload;

import com.google.common.io.CharStreams;
import com.mgomez.geolib.track.boundary.TrackService;
import com.mgomez.geolib.track.entity.Track;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MultipartRequestHandler {

    private TrackService trackService;

    @Inject
    public MultipartRequestHandler(TrackService trackService) {
        this.trackService = trackService;
    }

    public List<Track> handleUpload(HttpServletRequest request) throws IOException, ServletException {
        List<Track> files = new ArrayList<Track>();
        request.getParts().stream().filter(part -> part.getContentType() != null).forEach(part -> {
            final String content = getContent(part);
            final Track file = new Track(getFilename(part), content);
            trackService.addTrack(file);
            files.add(file);
        });
        return files;
    }

    private String getContent(Part part) {
        try {
            return CharStreams.toString(new InputStreamReader(part.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getFilename(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
            }
        }
        throw new IllegalArgumentException("No filename found in request");
    }
}
