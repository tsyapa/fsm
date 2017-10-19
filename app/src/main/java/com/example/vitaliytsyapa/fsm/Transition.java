package com.example.vitaliytsyapa.fsm;

/**
 * Created by Vitaliy Tsyapa on 10/19/2017.
 */

public class Transition {

    private String fromState;
    private String toState;
    private String action;

    public Transition(String fromState, String toState, String action){
        this.fromState=fromState;
        this.toState=toState;
        this.action=action;
    }

    @Override
    public String toString(){
        return fromState+" "+toState+" "+action;
    }
}
