package com.mgomez.geolib.file.boundary;

import com.google.common.collect.ImmutableList;
import com.mgomez.geolib.file.entity.Track;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @author: Marc Gomez / marc.gomez82 (at) gmail.com
 */
@Stateless
public class TrackService {

    @Inject
    private TrackContainer container;

    public ImmutableList<Track> getFiles() {
        return ImmutableList.copyOf(container.getFiles());
    }

    public void addTrack(Track file) {
        container.addFile(file);
    }

    public Track getTrack(String trackName) {
        return container.getFile(trackName);
    }

    public Track getMostRecentTrack() {
        return container.getFiles().stream().max((o1, o2) -> (int) (o1.getUploadedDate() - o2.getUploadedDate())).get();
    }
}
