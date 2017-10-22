package com.example.vitaliytsyapa.fsm.models;

/**
 * Created by Vitaliy Tsyapa on 10/22/2017.
 */

public class State {

    private int id;
    private boolean isArmAlarmed;
    private String name;

    public State(int id, boolean isArmAlarmed, String name){
        this.id=id;
        this.isArmAlarmed=isArmAlarmed;
        this.name=name;
    }
}
