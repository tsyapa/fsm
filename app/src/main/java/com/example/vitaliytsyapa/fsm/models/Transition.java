package com.example.vitaliytsyapa.fsm.models;

/**
 * Created by Vitaliy Tsyapa on 10/19/2017.
 */

public class Transition {

    private int id;
    private int fromStateId;
    private int toStateId;
    private int actionId;

    public Transition(int fromStateId, int toStateId, int actionId){
        this.fromStateId=fromStateId;
        this.toStateId=toStateId;
        this.actionId=actionId;
    }

    public int getFromStateId(){ return fromStateId; }

    public int getToStateId(){ return toStateId; }

    public int getActionId(){ return actionId; }

}
