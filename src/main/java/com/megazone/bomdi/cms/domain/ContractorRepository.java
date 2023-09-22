package com.megazone.bomdi.cms.domain;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractorRepository extends JpaRepository<Contractor, Long> {

    Optional<Contractor> findByName(String name);
}
