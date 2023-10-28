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
    private String originalFileName;
    private String contentType;
    private String filePath;

    public AttachmentFile(String fileName, String originalFileName, String contentType, String filePath) {
        this.filePath = filePath;
        this.originalFileName = originalFileName;
        this.contentType = contentType;
        this.fileName = fileName;
    }
}
