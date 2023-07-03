package com.zerkinisoft.peatbit.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "liquors")
public class Liquor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column
    String name;
    @Column
    String type;

    public Liquor() {}

    public Liquor(Integer id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}
