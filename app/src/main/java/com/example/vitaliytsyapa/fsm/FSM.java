package com.example.vitaliytsyapa.fsm;

/**
 * Created by Vitaliy Tsyapa on 10/17/2017.
 */

public class FSM {

    private int[] states={State.ALARM_DISARMED_ALL_UNLOCKED,State.ALARM_DISARMED_ALL_LOCKED,
            State.ALARM_ARMED_ALL_LOCKED,State.ALARM_DISARMED_DRIVER_UNLOCKED};
    //lock, lockx2, unlock, unlockx2
    private int[][] transitions={{State.ALARM_DISARMED_ALL_LOCKED,State.ALARM_ARMED_ALL_LOCKED,State.ALARM_DISARMED_ALL_UNLOCKED,State.ALARM_DISARMED_ALL_UNLOCKED},
            {State.ALARM_ARMED_ALL_LOCKED,State.ALARM_DISARMED_ALL_UNLOCKED,State.ALARM_DISARMED_DRIVER_UNLOCKED,State.ALARM_DISARMED_ALL_UNLOCKED},
            {State.ALARM_ARMED_ALL_LOCKED,State.ALARM_ARMED_ALL_LOCKED,State.ALARM_DISARMED_DRIVER_UNLOCKED,State.ALARM_DISARMED_ALL_UNLOCKED},
            {State.ALARM_DISARMED_ALL_LOCKED,State.ALARM_ARMED_ALL_LOCKED,State.ALARM_DISARMED_DRIVER_UNLOCKED,State.ALARM_DISARMED_DRIVER_UNLOCKED}};
    private int currentState=State.ALARM_DISARMED_ALL_UNLOCKED;
    //private ArrayList<Transition>=new ArrayList<>;
    private void changeState(int action){
        currentState=transitions[currentState][action];
    }

    public void lock(){
        changeState(Action.LOCK);
    }

    public void lockX2(){
        changeState(Action.LOCK_X2);
    }

    public void unlock(){
        changeState(Action.UNLOCK);
    }

    public void unlockX2(){
        changeState(Action.UNLOCK_X2);
    }
}
