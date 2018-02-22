package com.condominium.online.condo.repository;

import java.util.List;

public interface ReadRepository<T, R> {
    T findOne(R r);
    List<T> findAll();
}
