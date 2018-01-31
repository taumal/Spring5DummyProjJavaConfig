package com.dummies.model;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.persistence.Version;
import java.io.Serializable;
import java.util.Date;

/**
 * Base class for all persistent objects in the database.
 * Contains and automatically sets the created and updated columns.
 */
@MappedSuperclass
public class Persistent implements Serializable {
    @Transient
    private long id;

    private Date created;

    private Date updated;

    @Version
    private long version;

    public Persistent() {
    }

    public Persistent(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
}
