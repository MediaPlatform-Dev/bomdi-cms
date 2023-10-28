package com.megazone.act.cms.infrastructure.storage;


import com.megazone.act.cms.domain.repository.FileInfo;
import com.megazone.act.cms.domain.repository.FileStorage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static com.megazone.act.cms.utils.EnvironmentUtils.HOME_PATH;

@Slf4j
@Storage
public class LocalFileStorage implements FileStorage {


    @Override
    public List<FileInfo> upload(List<MultipartFile> files) {
        return files.stream()
                .map(this::upload)
                .toList();
    }

    @Override
    public FileInfo upload(MultipartFile file) {
        // TODO: 파일 확장자 확인
        String originalFilename = file.getOriginalFilename();

        try {
            file.transferTo(Path.of(HOME_PATH + "/" + originalFilename));
        } catch (IOException e) {
            log.error("파일 업로드 실패 - " + originalFilename);
            throw new RuntimeException(e);
        }

        return new FileInfo(randomFileName(file), file.getOriginalFilename(), HOME_PATH, file.getContentType());
    }
}
