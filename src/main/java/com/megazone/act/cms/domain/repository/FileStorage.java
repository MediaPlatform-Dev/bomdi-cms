package com.megazone.act.cms.domain.repository;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface FileStorage {

    List<FileInfo> upload(List<MultipartFile> file);
    FileInfo upload(MultipartFile file);

    default String randomFileName(MultipartFile file) {
        String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 16);
        String ext = extractExt(file.getOriginalFilename());
        return String.format("%s.%s", uuid, ext);
    }

    private String extractExt(String fileName) {
        if (fileName == null) {
            throw new IllegalArgumentException("파일명이 없습니다.");
        }

        int lastDotIndex = fileName.lastIndexOf(".");
        if (lastDotIndex == -1) {
            return "";
        }

        return fileName.substring(lastDotIndex + 1);
    }
}
