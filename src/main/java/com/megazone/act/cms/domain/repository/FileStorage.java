package com.megazone.act.cms.domain.repository;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

import static org.springframework.util.StringUtils.hasText;

public interface FileStorage<T extends FileInfo> {

    List<T> upload(List<MultipartFile> file);
    T upload(MultipartFile file);
    Resource download(String fileId);

    default String randomFileName(MultipartFile file) {
        String ext = extractExt(file.getOriginalFilename());
        String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 16);
        return String.format("%s.%s", uuid, ext);
    }

    default String extractExt(String fileName) {
        if (!hasText(fileName)) {
            throw new IllegalArgumentException("파일 이름이 없습니다.");
        }

        int lastDotIndex = fileName.lastIndexOf(".");
        if (lastDotIndex == -1) {
            return "";
        }

        return fileName.substring(lastDotIndex + 1);
    }
}
