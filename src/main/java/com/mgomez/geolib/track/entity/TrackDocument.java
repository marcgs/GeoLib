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

    public TrackDocument() {
    }

    public TrackDocument(String name, LocalDateTime uploadedDate) {
        this.name = name;
        this.uploadedDate = uploadedDate;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getUploadedDate() {
        return uploadedDate;
    }

    @Override
    public void addInlineAttachment(Attachment attachment) {
        super.addInlineAttachment(attachment);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("name", name)
                .add("uploadedDate", uploadedDate)
                .toString();
    }

}
