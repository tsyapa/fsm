package com.example.vitaliytsyapa.fsm.models;

/**
 * Created by Vitaliy Tsyapa on 10/22/2017.
 */

public class State {

    private int id;
    private boolean isAlarmArmed;
    private String name;

    public State(int id, boolean isAlarmArmed, String name){
        this.id=id;
        this.isAlarmArmed=isAlarmArmed;
        this.name=name;
    }

    public int getId(){ return id; }

    public boolean getIsAlarmArmed(){ return isAlarmArmed; }

    public String getName(){ return name; }
}
