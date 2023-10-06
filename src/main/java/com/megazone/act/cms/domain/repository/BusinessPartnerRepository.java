package com.megazone.act.cms.domain.repository;

import com.megazone.act.cms.domain.BusinessPartner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BusinessPartnerRepository extends JpaRepository<BusinessPartner, Long> {

    Optional<BusinessPartner> findByName(String name);
}
