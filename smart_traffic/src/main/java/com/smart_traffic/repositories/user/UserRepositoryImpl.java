package com.smart_traffic.repositories.user;

import com.smart_traffic.models.UserModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;


    public Optional<UserModel> findClientByEmail(String userEmail){
        TypedQuery<UserModel> query = entityManager.createQuery("SELECT c FROM UserModel c WHERE c.userEmail = :userEmail ", UserModel.class);
        query.setParameter("userEmail", userEmail);

        List<UserModel> resultados = query.getResultList();

        if (resultados.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(resultados.get(0));
    }
}
