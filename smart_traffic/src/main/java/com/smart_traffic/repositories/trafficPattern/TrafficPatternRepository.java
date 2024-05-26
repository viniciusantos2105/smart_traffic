package com.smart_traffic.repositories.trafficPattern;

import com.smart_traffic.models.TrafficPatternModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrafficPatternRepository extends JpaRepository<TrafficPatternModel, Long> {
}
