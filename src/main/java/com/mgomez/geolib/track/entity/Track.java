package com.mgomez.geolib.track.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.base.Objects;

import java.time.LocalDateTime;

/**
 * @author: Marc Gomez / marc.gomez82 (at) gmail.com
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties({"revision"})
public class Track {

    @JsonProperty("_id")
    private String id;
    @JsonProperty("_rev")
    private String revision;
    private String name;
    private LocalDateTime uploadedDate = LocalDateTime.now();
    private String fileType = "text/xml";
    private String content;

    public Track() {
    }

    public Track(String name) {
        this.name = name;
    }

    public Track(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRevision() {
        return revision;
    }

    public void setRevision(String revision) {
        this.revision = revision;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getUploadedDate() {
        return uploadedDate;
    }

    public String getFileType() {
        return fileType;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("name", name)
                .add("uploadedDate", uploadedDate)
                .add("fileType", fileType)
                .add("content", content)
                .toString();
    }

}
