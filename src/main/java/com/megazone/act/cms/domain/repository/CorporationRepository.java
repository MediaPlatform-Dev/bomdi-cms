package com.megazone.act.cms.domain.repository;

import com.megazone.act.cms.domain.BusinessPartner;
import com.megazone.act.cms.domain.Corporation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CorporationRepository extends JpaRepository<Corporation, Long> {

    Optional<Corporation> findByName(String name);
}
