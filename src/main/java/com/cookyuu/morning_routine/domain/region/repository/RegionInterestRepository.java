package com.cookyuu.morning_routine.domain.region.repository;

import com.cookyuu.morning_routine.domain.region.entity.RegionInterest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionInterestRepositoryImpl extends JpaRepository<RegionInterest, Long>, RegionInterestRepositoryJPQLImpl {
}
