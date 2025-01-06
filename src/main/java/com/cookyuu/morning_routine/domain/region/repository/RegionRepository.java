package com.cookyuu.morning_routine.domain.region.repository;

import com.cookyuu.morning_routine.domain.region.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region, String> {
}
