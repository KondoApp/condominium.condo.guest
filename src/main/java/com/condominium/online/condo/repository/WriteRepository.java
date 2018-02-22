package com.condominium.online.condo.repository;

public interface WriteRepository<T> {
    void save(T t);
    void delete(T t);
}
