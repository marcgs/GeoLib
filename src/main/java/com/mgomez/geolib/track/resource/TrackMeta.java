package com.mgomez.geolib.track.resource;

import com.google.common.base.Objects;

import java.time.LocalDateTime;

/**
 * @author: Marc Gomez / marc.gomez82 (at) gmail.com
 */
public class TrackMeta {

    private String id;
    private String name;
    private LocalDateTime uploadedDate;
    private String fileType;

    public TrackMeta(String id, String name, LocalDateTime uploadedDate, String fileType) {
        this.id = id;
        this.name = name;
        this.uploadedDate = uploadedDate;
        this.fileType = fileType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getUploadedDate() {
        return uploadedDate;
    }

    public void setUploadedDate(LocalDateTime uploadedDate) {
        this.uploadedDate = uploadedDate;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("uploadedDate", uploadedDate)
                .add("fileType", fileType)
                .toString();
    }

}
