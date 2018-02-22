package com.condominium.online.condo.entity;

import javax.persistence.Entity;

@Entity
public class Dweller extends Person {

    public Dweller(){
        super();
    }

    public Dweller(String name, String cpf) {

        super(name, cpf);
    }

    public Dweller(String name, String cpf, String apartmentNumber) {

        super(name, cpf, apartmentNumber);
    }

}
