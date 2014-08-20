package com.mgomez.geolib.file.resource;

import com.google.common.collect.ImmutableList;
import com.mgomez.geolib.file.boundary.TrackService;
import com.mgomez.geolib.file.entity.Track;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

/**
 * @author: Marc Gomez / marc.gomez82 (at) gmail.com
 */
@Path("tracks")
public class TrackResource {

    @Inject
    private TrackService trackService;

    @GET
    public List<Track> listFiles() {
        final ImmutableList<Track> files = trackService.getFiles();
        System.out.println("getting files: " + files);
        return files;
    }

    @Path("mostRecent")
    @GET
    public Track getMostRecentFile() {
        return trackService.getMostRecentTrack();
    }

    @Path("{fileName}")
    @GET
    public Track getFile(@PathParam("fileName") String fileName) {
        return trackService.getTrack(fileName);
    }
}
