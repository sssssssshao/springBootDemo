package com.sfy.myproj.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity(name = "coords")
public class Coords {
    @Id
    private Integer id;
    private String name;
    @Column(name = "chinaName")
    private String chinaName;
    private String ssxq;
    private String cp;
    private String bound;

}
