package com.mgomez.geolib.file.entity;

import com.google.common.base.Objects;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @author: Marc Gomez / marc.gomez82 (at) gmail.com
 */
public class Track {

    private String fileName;
    private String content;
    private LocalDateTime uploadedDate = LocalDateTime.now();


    public Track() {
    }

    public Track(String fileName, String content) {
        this.fileName = fileName;
        this.content = content;
    }

    public String getFileName() {
        return fileName;
    }

    public String getContent() {
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
