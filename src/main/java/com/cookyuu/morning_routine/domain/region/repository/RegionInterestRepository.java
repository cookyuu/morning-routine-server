package com.cookyuu.morning_routine.domain.region.repository;

import com.cookyuu.morning_routine.domain.region.entity.Region;
import com.cookyuu.morning_routine.domain.region.entity.RegionInterest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RegionInterestRepository extends JpaRepository<RegionInterest, Long>{
    @Query("SELECT DISTINCT region FROM RegionInterest")
    List<Region> findAllDuplicatedRegion();
}
