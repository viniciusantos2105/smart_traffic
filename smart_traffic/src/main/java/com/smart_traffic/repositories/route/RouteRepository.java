package com.smart_traffic.repositories.route;

import com.smart_traffic.models.RouteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends JpaRepository<RouteModel, Long> {
}
