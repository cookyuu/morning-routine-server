package com.cookyuu.morning_routine.domain.region.repository;

import com.cookyuu.morning_routine.domain.region.entity.Region;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public class RegionInterestRepositoryJPQL {
    @Query(value = "select region " +
            "from User user " +
            "where user.name = :name")
    List<Region> findAllDuplicatedRegion(@Param("name") String name) {
        return null;
    }
}
