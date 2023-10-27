package com.megazone.act.cms.domain.entity;

import jakarta.persistence.*;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class AttachmentFile {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String fileName;
    private String contentType;
    private String filePath;

    @JoinColumn(name = "cntrct_id")
    @ManyToOne
    private Contract contract;

    public AttachmentFile(String fileName, String contentType, String filePath) {
        this.filePath = filePath;
        this.contentType = contentType;
        this.fileName = fileName;
    }
}
