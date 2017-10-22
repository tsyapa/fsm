package com.example.vitaliytsyapa.fsm;

/**
 * Created by Vitaliy Tsyapa on 10/22/2017.
 */

public interface FSMInterface {

    void changeState(String action);
    String getCurrentState();
}
