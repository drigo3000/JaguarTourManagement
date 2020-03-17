package com.swevolution.cucurumbe.model.entities.util;

/*
 * Copyright 2014 Rxkx.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import com.swevolution.cucurumbe.controllers.utility.JsfUtil;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

/**
 *
 * @author Rxkx
 */
@OptimisticLocking(type = OptimisticLockType.NONE)
@MappedSuperclass
public abstract class GeneralEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE_UPDATED", nullable = false)
    private Date dateUpdated;

    private LocalDateTime localDateUpdated;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE_CREATED", nullable = false)
    private Date dateCreated;

    private LocalDateTime localDateCreated;

    @Column(name = "IS_ACTIVE", nullable = false)
    private boolean active;

    @Column(name = "UUID", nullable = false)
    private String uuid;

    private long version;

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @PrePersist
    private void preSaving() {
        Date now = new Date(System.currentTimeMillis());
        setUuid(UUID.randomUUID().toString());
        setDateCreated(now);
        setDateUpdated(now);
        setLocalDateCreated(JsfUtil.getCancunNow());
        setLocalDateUpdated(JsfUtil.getCancunNow());
        setActive(true);
        if (this.uuid == null || this.uuid.isEmpty()) {
            setUuid(UUID.randomUUID().toString());
        }
    }

    @PreUpdate
    private void preUpdating() {
        setLocalDateUpdated(JsfUtil.getCancunNow());
        setDateUpdated(new Date(System.currentTimeMillis()));
        if (this.uuid == null || this.uuid.isEmpty()) {
            setUuid(UUID.randomUUID().toString());
        }
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public LocalDateTime getLocalDateUpdated() {
        return localDateUpdated;
    }

    public void setLocalDateUpdated(LocalDateTime localDateUpdated) {
        this.localDateUpdated = localDateUpdated;
    }

    public LocalDateTime getLocalDateCreated() {
        return localDateCreated;
    }

    public void setLocalDateCreated(LocalDateTime localDateCreated) {
        this.localDateCreated = localDateCreated;
    }

}
