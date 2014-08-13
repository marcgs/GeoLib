package com.mgomez.geolib.file.boundary;

import com.google.common.collect.ImmutableList;
import com.mgomez.geolib.file.entity.GeoFile;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @author: Marc Gomez / marc.gomez82 (at) gmail.com
 */
@Stateless
public class GeoFileService {

    @Inject
    private GeoFileContainer container;

    public ImmutableList<GeoFile> getFiles() {
        return ImmutableList.copyOf(container.getFiles());
    }

    public void addFile(GeoFile file) {
        container.addFile(file);
    }
}
