package com.example.vitaliytsyapa.fsm.models;

public class Action {

    private int id;
    private String name;

    public Action(int id, String name){
        this.id=id;
        this.name=name;
    }

    public int getId(){ return id; }

    public String getName(){ return name; }
}
