package com.smart_traffic.services;

import java.util.List;

public interface GenericService<T, R> {
    T save(R entity);
    T findById(Long id);
    List<T> findAll();
    void deleteById(Long id);
}