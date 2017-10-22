package com.example.vitaliytsyapa.fsm.models;

public class Transition {

    private int id;
    private int fromStateId;
    private int toStateId;
    private int actionId;

    public Transition(int id, int fromStateId, int toStateId, int actionId){
        this.id=id;
        this.fromStateId=fromStateId;
        this.toStateId=toStateId;
        this.actionId=actionId;
    }

    public int getFromStateId(){ return fromStateId; }

    public int getToStateId(){ return toStateId; }

    public int getActionId(){ return actionId; }

}
