package com.mgomez.geolib.file.boundary;

import com.google.common.collect.Lists;
import com.mgomez.geolib.file.entity.GeoFile;

import javax.inject.Singleton;
import java.util.List;

/**
 * @author: Marc Gomez / marc.gomez82 (at) gmail.com
 */
@Singleton
public class GeoFileContainer {

    private List<GeoFile> geoFiles = Lists.newArrayList();

    public List<GeoFile> getFiles() {
        return geoFiles;
    }

    public void addFile(GeoFile file) {
        geoFiles.add(file);
    }
}
