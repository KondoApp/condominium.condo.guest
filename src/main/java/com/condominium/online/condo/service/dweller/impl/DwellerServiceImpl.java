package com.condominium.online.condo.service.dweller.impl;

import com.condominium.online.condo.entity.Dweller;
import com.condominium.online.condo.exceptions.InvalidUserException;
import com.condominium.online.condo.repository.DwellerRepository;
import com.condominium.online.condo.service.dweller.DwellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DwellerServiceImpl implements DwellerService {

    private DwellerRepository dwellerRepository;

    @Autowired
    public DwellerServiceImpl(DwellerRepository dwellerRepository) {

        this.dwellerRepository = dwellerRepository;
    }

    @Override
    public boolean insert(Dweller dweller) throws InvalidUserException {

        //TODO: Create validation class to use instead of inline
        //        Optional.of(dweller).map(Dweller::getName).filter(name -> !name.isEmpty()).orElseThrow(() -> {
        //            return new InvalidUserException("Invalid Name");
        //        });
        //
        //        Optional.of(dweller).map(Dweller::getApartmentNumber).filter(apNumber -> !apNumber.isEmpty())
        //                .orElseThrow(() -> {
        //                    return new InvalidUserException("Invalid apartment");
        //                });
        //
        //        Optional.of(dweller).map(Dweller::getCpf).filter(cpf -> !cpf.isEmpty()).orElseThrow(() -> {
        //            return new InvalidUserException("Invalid CPF");
        //        });
        dwellerRepository.save(dweller);
        return true;
    }

    @Override
    public boolean update(Dweller dweller) {

        return false;
    }

    private boolean deleteDweller(String id) {

        if (!dwellerRepository.exists(Long.parseLong(id))) {
            throw new InvalidUserException("No such user");
        }
        dwellerRepository.delete(Long.parseLong(id));
        return true;
    }

    @Override
    public boolean delete(String id) {

        return deleteDweller(id);
    }


    @Override
    public List<Dweller> findAll() {

        return dwellerRepository.findAll();
    }

    @Override
    public Optional<Dweller> findOne(String id) {

        return Optional.ofNullable(dwellerRepository.findOne(id));
    }
}
