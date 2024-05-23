package com.hellolaw.hellolaw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hellolaw.hellolaw.entity.Precedent;

@Repository
public interface PrecedentRepository extends JpaRepository<Precedent, Long> {
}
