package com.megazone.act.cms.application;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import lombok.RequiredArgsConstructor;

import static com.megazone.act.cms.utils.EnvironmentUtils.HOME_PATH;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class FileService {

    public void upload(List<MultipartFile> files) {
        files.forEach(this::upload);
    }

    public void upload(MultipartFile file) {
        // TODO: 파일 확장자 확인
        String originalFilename = file.getOriginalFilename();
        try {
            file.transferTo(Path.of(HOME_PATH + "/" + originalFilename));
        } catch (IOException e) {
            log.error("파일 업로드 실패 - " + originalFilename);
            throw new RuntimeException(e);
        }
    }
}
