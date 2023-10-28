package com.megazone.act.cms.domain.repository;

public record FileInfo(
        String name,
        String originalName,
        String relativePath,
        String type
) {
}
