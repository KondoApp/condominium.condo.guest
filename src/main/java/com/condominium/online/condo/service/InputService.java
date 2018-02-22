package com.condominium.online.condo.service;

public interface InputService<T> {
    boolean insert(T t);
    boolean update(T t);
    boolean delete(String id);
}
