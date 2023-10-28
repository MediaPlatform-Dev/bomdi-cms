package com.megazone.act.cms.domain.repository;

import com.megazone.act.cms.domain.entity.AttachmentFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AttachmentFileRepository extends JpaRepository<AttachmentFile, Integer> {

    Optional<AttachmentFile> findByFileName(String fileName);
}
