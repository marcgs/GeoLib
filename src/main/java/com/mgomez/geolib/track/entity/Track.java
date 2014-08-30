package com.mgomez.geolib.track.entity;

import com.google.common.base.Objects;

import java.time.LocalDateTime;

/**
 * @author: Marc Gomez / marc.gomez82 (at) gmail.com
 */
public class Track {

    private String fileName;
    private String content;
    private LocalDateTime uploadedDate = LocalDateTime.now();
    private String fileType = "text/xml";


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

    public LocalDateTime getUploadedDate() {
        return uploadedDate;
    }

    public String getFileType() {
        return fileType;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("fileName", fileName)
                .add("uploadedDate", uploadedDate)
                .add("fileType", fileType)
                .add("content", content)
                .toString();
    }


}
