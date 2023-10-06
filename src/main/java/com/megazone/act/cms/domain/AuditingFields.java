package com.megazone.act.cms.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

import lombok.Getter;
import org.springframework.data.annotation.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class AuditingFields {

    @Column(name = "reg_dttm", updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "regr_id", updatable = false)
    @CreatedBy
    protected String createdBy;

    @Column(name = "mod_dttm")
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Column(name = "modr_id")
    @LastModifiedBy
    private String modifiedBy;
}
