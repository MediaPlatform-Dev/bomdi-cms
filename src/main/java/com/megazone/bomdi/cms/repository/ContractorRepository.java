package com.megazone.bomdi.cms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.megazone.bomdi.cms.domain.Contractor;

public interface ContractorRepository extends JpaRepository<Contractor, Long> {

    Optional<Contractor> findByName(String name);
}
