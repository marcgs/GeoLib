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

    public GeoFile getFile(String fileName) {
        return container.getFile(fileName);
    }

    public GeoFile getMostRecentFile() {
        return container.getFiles().stream().max((o1, o2) -> (int) (o1.getUploadedDate() - o2.getUploadedDate())).get();
    }
}
