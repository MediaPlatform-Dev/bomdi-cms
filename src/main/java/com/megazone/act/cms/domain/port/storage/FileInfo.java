package com.megazone.act.cms.domain.port.storage;

import org.springframework.core.io.Resource;

public interface FileInfo {

    String name();

    String originalName();

    String relativePath();

    String type();

    Resource resource();
}
