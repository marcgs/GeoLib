package com.mgomez.geolib.track.resource;

import com.mgomez.geolib.track.entity.TrackDocument;

import java.util.function.Function;

/**
 * @author: Marc Gomez / marc.gomez82 (at) gmail.com
 */
public class TrackMetaFactory implements Function<TrackDocument, TrackMeta> {
    @Override
    public TrackMeta apply(TrackDocument track) {
        return new TrackMeta(track.getId(), track.getName(), track.getUploadedDate(), track.getFileType());
    }
}
