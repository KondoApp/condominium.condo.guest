package com.condominium.online.condo.repository.impl;

import com.condominium.online.condo.entity.Dweller;
import com.condominium.online.condo.repository.DwellerJPARepository;
import com.condominium.online.condo.repository.DwellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DwellerRepositoryImpl implements DwellerRepository {

    @Autowired
    private DwellerJPARepository repository;

    @Override
    public boolean exists(long id) {

        return repository.exists(id);
    }

    @Override
    public Dweller findOne(String id) {

        return repository.getOne(Long.valueOf(id));
    }

    @Override
    public List<Dweller> findAll() {

        return repository.findAll();
    }


    @Override
    public void save(Dweller dweller) {

        repository.save(dweller);
    }

    @Override
    public void delete(Dweller dweller) {

        repository.save(dweller);
    }

    @Override
    public void delete(long id) {

        repository.delete(id);
    }
}
