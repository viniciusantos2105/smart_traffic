package com.smart_traffic.repositories.accident;

import com.smart_traffic.exceptions.commons.NotFoundException;
import com.smart_traffic.models.AccidentModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccidentRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    public AccidentModel findById(Long accidentId){
        TypedQuery<AccidentModel> query = entityManager.createQuery("SELECT a FROM AccidentModel a WHERE a.accidentId = :accidentId", AccidentModel.class);
        query.setParameter("accidentId", accidentId);

        if(query.getResultList().isEmpty()){
            throw NotFoundException.createNotFoundException("Acidente não encontrado");
        }

        return query.getSingleResult();
    }

    public List<AccidentModel> listAll(){
        TypedQuery<AccidentModel> query = entityManager.createQuery("SELECT a FROM AccidentModel a", AccidentModel.class);
        return query.getResultList();
    }
}
