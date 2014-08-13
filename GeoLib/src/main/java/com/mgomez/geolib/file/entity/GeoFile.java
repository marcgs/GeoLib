package com.mgomez.geolib.file.entity;

import com.google.common.base.Objects;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @author: Marc Gomez / marc.gomez82 (at) gmail.com
 */
public class GeoFile {

    private String fileName;
    private LocalDateTime uploadedDate = LocalDateTime.now();


    public GeoFile() {
    }

    public String getFileName() {
        return fileName;
    }

    public long getUploadedDate() {
        return uploadedDate.toEpochSecond(ZoneOffset.UTC);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("fileName", fileName)
                .add("uploadedDate", uploadedDate)
                .toString();
    }
}
