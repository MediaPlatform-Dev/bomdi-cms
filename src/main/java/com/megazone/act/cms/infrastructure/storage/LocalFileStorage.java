package com.megazone.act.cms.infrastructure.storage;


import com.megazone.act.cms.domain.port.storage.FileStorage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static com.megazone.act.cms.utils.EnvironmentUtils.HOME_PATH;

@Slf4j
@Storage
public class LocalFileStorage implements FileStorage<FileResponse> {

    @Override
    public List<FileResponse> upload(List<MultipartFile> files) {
        return files.stream()
                .map(this::upload)
                .toList();
    }

    @Override
    public FileResponse upload(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String savedName = randomFileName(file);
        upload(file, savedName, originalFilename);

        return new FileResponse(savedName,
                file.getOriginalFilename(),
                HOME_PATH,
                file.getContentType(),
                null
        );
    }

    private static void upload(MultipartFile file, String savedName, String originalFilename) {
        try {
            file.transferTo(Path.of(HOME_PATH + "/" + savedName));
        } catch (IOException e) {
            log.error("파일 업로드 실패 - " + originalFilename);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Resource download(String fileId) {
        String filePath = HOME_PATH + "/" + fileId; // 파일 경로 설정
        return new FileSystemResource(filePath);
    }
}
