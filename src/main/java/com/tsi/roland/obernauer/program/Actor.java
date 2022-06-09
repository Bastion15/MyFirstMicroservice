package com.tsi.roland.obernauer.program;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.stereotype.Repository;


@Qualifier("actors")
@Entity
@Repository
@Table(name = "actor")
public class Actor {
    //Attributes

        @Id
        @GeneratedValue(strategy= GenerationType.IDENTITY)
        int actor_id;
        String first_name;
        String last_name;
    //Constructors
        public Actor(String first_name, String last_name){
            this.first_name = first_name;
            this.last_name = last_name;
        }

        public Actor(){}


    //Methods
    //getters/setters

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public int getActor_id() {
        return actor_id;
    }

    public void setActor_id(int actor_id) {
        this.actor_id = actor_id;
    }
}
