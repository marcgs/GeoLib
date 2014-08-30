package com.mgomez.geolib.track.resource;

import com.google.common.annotations.VisibleForTesting;
import com.mgomez.geolib.track.boundary.TrackService;
import com.mgomez.geolib.track.entity.Track;

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

    public TrackResource() {
    }

    @VisibleForTesting
    TrackResource(TrackService trackService) {
        this.trackService = trackService;
    }

    @GET
    public List<Track> listAllTracks() {
        return trackService.getTracks();
    }

    @Path("mostRecent")
    @GET
    public Track getMostRecentTrack() {
        return trackService.getMostRecentTrack().orElse(null);
    }

    @Path("{fileName}")
    @GET
    public Track getTrack(@PathParam("fileName") String fileName) {
        return trackService.getTrack(fileName).orElse(null);
    }
}
