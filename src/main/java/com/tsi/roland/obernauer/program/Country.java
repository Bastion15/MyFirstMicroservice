package com.tsi.roland.obernauer.program;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.stereotype.Repository;



@Qualifier("country")
@Entity
@Repository
@Table(name = "country")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int country_id;
    String country;


    public Country(String country) {
        this.country = country;

    }


    public  int getCountry_id() {
        return country_id;
    }

    public void setCountry_id(int country_id) {
       this.country_id = country_id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Country(){}

}