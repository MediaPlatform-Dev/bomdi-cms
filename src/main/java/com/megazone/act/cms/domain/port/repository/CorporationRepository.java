package com.megazone.act.cms.domain.port.repository;

import com.megazone.act.cms.domain.entity.Corporation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CorporationRepository extends JpaRepository<Corporation, Long> {
}