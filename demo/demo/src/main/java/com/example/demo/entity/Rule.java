package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;


@Entity
@Data
@Table(name = "rule")
public class Rule {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    //@Column(name = "id")
    private Integer id;

    @Column(nullable = false)
    private String name;
    private Integer points;
    private Integer min_bound;
    private String component;

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public Integer getMin_bound() {
        return min_bound;
    }

    public Integer getPoints() {
        return points;
    }

    public String getComponent() {
        return component;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public void setMin_bound(Integer min_bound) {
        this.min_bound = min_bound;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}
