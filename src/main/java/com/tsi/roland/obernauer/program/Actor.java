package com.tsi.roland.obernauer.program;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
@Qualifier("actors")
@Entity
@Repository
@Table(name = "actor")
public class Actor {
    //Attributes
        @Id
        @GeneratedValue(strategy= GenerationType.IDENTITY)
         private int actor_id;
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
}
