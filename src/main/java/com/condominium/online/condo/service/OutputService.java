package com.condominium.online.condo.service;

import java.util.List;
import java.util.Optional;

public interface OutputService<T, R> {
    List<T> findAll();
    Optional<T> findOne(R r);
}
