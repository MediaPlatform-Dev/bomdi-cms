package com.megazone.act.cms.domain.repository;

import org.springframework.core.io.Resource;

public interface FileInfo {

    String name();

    String originalName();

    String relativePath();

    String type();

    Resource resource();
}
