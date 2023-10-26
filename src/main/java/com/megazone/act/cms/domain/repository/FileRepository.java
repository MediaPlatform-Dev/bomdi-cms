package com.megazone.act.cms.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.megazone.act.cms.domain.entity.File;

public interface FileRepository extends JpaRepository<File, Integer> {

}
