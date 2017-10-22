package com.example.vitaliytsyapa.fsm.models;

/**
 * Created by Vitaliy Tsyapa on 10/22/2017.
 */

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
