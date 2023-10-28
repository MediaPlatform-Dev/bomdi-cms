package com.megazone.act.cms.application.dto.response;

import com.megazone.act.cms.domain.entity.AttachmentFile;
import com.megazone.act.cms.domain.repository.FileInfo;
import org.springframework.core.io.Resource;

public record FileResponse (
        String name,
        String originalName,
        String relativePath,
        String type,
        Resource resource
)  implements FileInfo {

    public AttachmentFile toEntity() {
        return new AttachmentFile(name, originalName, type, relativePath);
    }

    public static FileResponse from(AttachmentFile entity) {
        return from(entity, null);
    }

    public static FileResponse from(AttachmentFile entity, Resource resource) {
        return new FileResponse(
                entity.getFileName(),
                entity.getOriginalFileName(),
                entity.getFilePath(),
                entity.getContentType(),
                resource
        );
    }
}
