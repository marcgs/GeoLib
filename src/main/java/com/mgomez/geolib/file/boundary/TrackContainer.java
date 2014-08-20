package com.mgomez.geolib.file.boundary;

import com.google.common.collect.Lists;
import com.mgomez.geolib.file.entity.Track;

import javax.inject.Singleton;
import java.util.List;

/**
 * @author: Marc Gomez / marc.gomez82 (at) gmail.com
 */
@Singleton
public class TrackContainer {

    private List<Track> tracks = Lists.newArrayList();

    public List<Track> getFiles() {
        return tracks;
    }

    public void addFile(Track file) {
        tracks.add(file);
    }

    public Track getFile(String fileName) {
        return tracks.stream().filter(s -> s.getFileName().equals(fileName)).findFirst().get();
    }
}
