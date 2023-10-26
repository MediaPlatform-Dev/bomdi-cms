package com.megazone.act.cms.domain.entity;

import jakarta.persistence.*;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class File {


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String fileName;  // 파일명
    private String contentType;  // 파일 컨텐츠 타입
    private String filePath;  // 파일 저장 경로

    @JoinColumn(name = "cntrct_id")
    @ManyToOne
    private Contract contract;  //양방향 매핑

    public File(String fileName, String contentType, String filePath) {
        this.filePath = filePath;
        this.contentType = contentType;
        this.fileName = fileName;
    }


    //public FileDTO toDTO() {
    //    FileDTO dto = new FileDTO();
    //    dto.setFilename(this.filename);
    //    dto.setContentType(this.contentType);
    //    return dto;
    //}
    //

}
