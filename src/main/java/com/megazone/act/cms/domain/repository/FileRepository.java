package com.megazone.act.cms.domain.repository;

import com.megazone.act.cms.domain.entity.AttachmentFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<AttachmentFile, Integer> {

}
