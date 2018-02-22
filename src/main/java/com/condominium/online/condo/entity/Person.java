package com.condominium.online.condo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public abstract class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @NotNull
    private String name;

    @NotNull
    @Column(unique = true)
    private String cpf;

    @Column(unique = true)
    private String apartmentNumber;

    public Person() {

    }

    public Person(String name, String cpf) {

        this.name = name;
        this.cpf = cpf;
    }

    public Person(String name, String cpf, String apartmentNumber) {

        this.name = name;
        this.cpf = cpf;
        this.apartmentNumber = apartmentNumber;
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getCpf() {

        return cpf;
    }

    public void setCpf(String cpf) {

        this.cpf = cpf;
    }

    public String getApartmentNumber() {

        return apartmentNumber;
    }

    public void setApartmentNumber(String apartmentNumber) {

        this.apartmentNumber = apartmentNumber;
    }
}
