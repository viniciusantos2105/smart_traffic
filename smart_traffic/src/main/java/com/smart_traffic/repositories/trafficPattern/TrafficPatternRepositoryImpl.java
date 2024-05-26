package com.smart_traffic.repositories.trafficPattern;

import com.smart_traffic.exceptions.commons.NotFoundException;
import com.smart_traffic.models.TrafficPatternModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TrafficPatternRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    public TrafficPatternModel findById(Long trafficPatternId){
        TypedQuery<TrafficPatternModel> query = entityManager.createQuery("SELECT t FROM TrafficPatternModel t WHERE t.trafficPatternId = :trafficPatternId", TrafficPatternModel.class);
        query.setParameter("trafficPatternId", trafficPatternId);

        if(query.getResultList().isEmpty()){
            throw NotFoundException.createNotFoundException("Padrão de tráfego não encontrado");
        }

        return query.getSingleResult();
    }

    public List<TrafficPatternModel> listAll(){
        TypedQuery<TrafficPatternModel> query = entityManager.createQuery("SELECT t FROM TrafficPatternModel t", TrafficPatternModel.class);
        return query.getResultList();
    }
}
