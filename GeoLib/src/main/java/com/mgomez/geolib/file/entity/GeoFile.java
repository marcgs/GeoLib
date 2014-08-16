package com.mgomez.geolib.file.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.base.Objects;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @author: Marc Gomez / marc.gomez82 (at) gmail.com
 */
@JsonIgnoreProperties({"content"})
public class GeoFile {

    private String fileName;
    private byte[] content;
    private LocalDateTime uploadedDate = LocalDateTime.now();


    public GeoFile() {
    }

    public GeoFile(String fileName, byte[] content) {
        this.fileName = fileName;
        this.content = content;
    }

    public String getFileName() {
        return fileName;
    }

    public byte[] getContent() {
        return content;
    }

    public long getUploadedDate() {
        return uploadedDate.toEpochSecond(ZoneOffset.UTC);
    }

    public String getFileType() {
        return "text/xml";
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("fileName", fileName)
                .add("uploadedDate", uploadedDate)
                .toString();
    }


}
