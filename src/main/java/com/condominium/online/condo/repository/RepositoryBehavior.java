package com.condominium.online.condo.repository;

public interface RepositoryBehavior<T> extends WriteRepository<T>, ReadRepository<T, String> {

}
