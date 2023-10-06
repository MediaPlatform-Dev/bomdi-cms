package com.megazone.act.cms.domain.repository;

import com.megazone.act.cms.domain.BusinessPartner;
import com.megazone.act.cms.domain.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;

import java.util.Optional;

public interface BusinessPartnerRepository extends JpaRepository<BusinessPartner, Long> {

    Optional<BusinessPartner> findByName(String name);
}
