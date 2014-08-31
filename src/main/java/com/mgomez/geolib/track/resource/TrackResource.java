package com.mgomez.geolib.track.resource;

import com.google.common.annotations.VisibleForTesting;
import com.mgomez.geolib.track.boundary.TrackService;
import com.mgomez.geolib.track.entity.TrackDocument;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<TrackMeta> listTracks() {
        return trackService.listTracks().stream().map(new TrackMetaFactory()).collect(Collectors.toList());
    }

    @Path("{id}")
    @GET
    public TrackContent getTrack(@PathParam("id") String id) {
        final Optional<TrackDocument> trackOptional = trackService.getTrack(id);
        if (trackOptional.isPresent()) {
            final TrackDocument track = trackOptional.get();
            return new TrackContent(track.getId(), track.getContent());
        }
        return null;

    }
}
