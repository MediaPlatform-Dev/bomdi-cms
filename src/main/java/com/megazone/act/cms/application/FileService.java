package com.megazone.act.cms.application;

import java.io.IOException;
import java.nio.file.Path;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import lombok.RequiredArgsConstructor;

import com.megazone.act.cms.domain.entity.File;
import com.megazone.act.cms.domain.repository.FileRepository;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class FileService {

    private static final String PATH = "C:/Users/MZ01-HORANG/cms-app/src/main/resources/static/img/fileStorage";

    private final FileRepository fileRepository;


    public void fileUpload(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String contentType = file.getContentType();

        try {
            file.transferTo(Path.of(PATH + "/" + originalFilename));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        File files = new File(originalFilename, contentType, PATH);
        fileRepository.save(files);
    }
}
