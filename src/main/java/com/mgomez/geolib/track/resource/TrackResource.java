package com.mgomez.geolib.track.resource;

import com.google.common.annotations.VisibleForTesting;
import com.mgomez.geolib.track.boundary.TrackService;
import com.mgomez.geolib.track.entity.TrackDocument;

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
    public List<TrackDocument> listTracks() {
        return trackService.listTracks();
    }

    @Path("{id}")
    @GET
    public String getTrackContent(@PathParam("id") String id) {
        return trackService.getTrackContent(id);
    }
}
