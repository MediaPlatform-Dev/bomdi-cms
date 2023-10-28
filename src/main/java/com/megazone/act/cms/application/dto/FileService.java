package com.megazone.act.cms.application.dto;

import com.megazone.act.cms.domain.port.storage.FileInfo;
import com.megazone.act.cms.infrastructure.storage.FileResponse;
import com.megazone.act.cms.domain.entity.AttachmentFile;
import com.megazone.act.cms.domain.port.repository.AttachmentFileRepository;
import com.megazone.act.cms.domain.port.storage.FileStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FileService {

    private final FileStorage<FileResponse> fileStorage;
    private final AttachmentFileRepository fileRepository;

    public FileInfo download(String name) {
        AttachmentFile file = fileRepository.findByFileName(name)
                .orElseThrow(() -> new IllegalArgumentException("파일을 찾을 수 없습니다. - " + name));

        Resource resource = fileStorage.download(name);
        return FileResponse.from(file, resource);
    }
}
