package com.cookyuu.morning_routine.domain.region.repository;

import com.cookyuu.morning_routine.domain.region.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegionRepository extends JpaRepository<Region, String> {

    Optional<Region> findFirstOneByGridXAndGridYOrderByCodeDesc(int x, int y);
}
