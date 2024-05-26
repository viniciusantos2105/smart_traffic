package com.smart_traffic.repositories.accident;

import com.smart_traffic.models.AccidentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccidentRepository extends JpaRepository<AccidentModel, Long> {
}
