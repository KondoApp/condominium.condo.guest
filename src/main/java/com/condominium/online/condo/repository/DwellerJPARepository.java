package com.condominium.online.condo.repository;

import com.condominium.online.condo.entity.Dweller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DwellerJPARepository extends JpaRepository<Dweller, Long> {

    void delete(long id);

    boolean exists(long id);

}
