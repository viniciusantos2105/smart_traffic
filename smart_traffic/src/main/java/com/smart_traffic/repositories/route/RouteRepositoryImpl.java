package com.smart_traffic.repositories.route;

import com.smart_traffic.exceptions.commons.NotFoundException;
import com.smart_traffic.exceptions.commons.ResourceCannotCreateException;
import com.smart_traffic.models.RouteModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RouteRepositoryImpl {


    @PersistenceContext
    private EntityManager entityManager;


    public RouteModel findById(Long routeId){
        TypedQuery<RouteModel> query = entityManager.createQuery("SELECT r FROM RouteModel r WHERE r.routeId = :routeId", RouteModel.class);
        query.setParameter("routeId", routeId);

        if(query.getResultList().isEmpty()){
            throw NotFoundException.createNotFoundException("Rota de viagem não encontrada");
        }

        return query.getSingleResult();
    }

    public List<RouteModel> listAll(){
        TypedQuery<RouteModel> query = entityManager.createQuery("SELECT r FROM RouteModel r", RouteModel.class);
        return query.getResultList();
    }

    public void existRoute(String routeStartLocation, String routeEndLocation){
        TypedQuery<RouteModel> query = entityManager.createQuery("SELECT r FROM RouteModel r " +
                "WHERE lower(r.routeStartLocation) LIKE lower(:routeStartLocation) AND lower(r.routeEndLocation) LIKE lower(:routeEndLocation) ", RouteModel.class);
        query.setParameter("routeStartLocation", routeStartLocation);
        query.setParameter("routeEndLocation", routeEndLocation);

        if(!query.getResultList().isEmpty()){
            throw ResourceCannotCreateException.createResourceCannotCreateException("Rota já existe");
        }
    }

}
