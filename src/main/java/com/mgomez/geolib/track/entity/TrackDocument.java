package com.mgomez.geolib.track.entity;

import com.google.common.base.Objects;
import org.ektorp.Attachment;
import org.ektorp.support.CouchDbDocument;

import java.time.LocalDateTime;

/**
 * @author: Marc Gomez / marc.gomez82 (at) gmail.com
 */
public class TrackDocument extends CouchDbDocument {

    private String name;
    private LocalDateTime uploadedDate;
    private String fileType = "text/xml";
    private String content;

    public TrackDocument() {
    }

    public TrackDocument(String name) {
        this(name, null);
    }

    public TrackDocument(String name, String content) {
        this.name = name;
        this.uploadedDate = LocalDateTime.now();
        this.content = content;
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
    public void addInlineAttachment(Attachment a) {
        super.addInlineAttachment(a);
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
