package com.itz.gst.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;

@Getter(AccessLevel.PROTECTED)
@Setter(AccessLevel.PROTECTED)
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Auditable<U> {
//    @CreatedBy
//    @Column(name = "crBy")
//    private U createdBy;

    @CreatedDate
//    @Column(name = "crAt")
    private Timestamp crAt;

    @LastModifiedDate
//    @Column(name = "upAt")
    private Timestamp upAt;

}