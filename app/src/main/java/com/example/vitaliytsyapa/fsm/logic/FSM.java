package com.example.vitaliytsyapa.fsm.logic;

import android.content.Context;

import com.example.vitaliytsyapa.fsm.models.*;
import org.json.*;
import java.io.*;

public class FSM {

    private State[] states;
    private Action[] actions;
    private State currentState;
    private Transition[] transitions;
    private Context context;

    public FSM(Context context) throws IOException,JSONException{
        this.context=context;
        init();
    }

    private void init() throws IOException,JSONException{
        JSONObject jsonObject=new JSONObject(readJsonFile(context));
        states=readStates(jsonObject);
        actions=readActions(jsonObject);
        currentState=readInitialState(jsonObject);
        transitions=readTransitions(jsonObject);
        jsonObject=null;
    }
    //reading json file config.json in assets and converting it into String
    protected String readJsonFile(Context context) throws IOException{
        InputStream is=context.getAssets().open("config.json");
        int size=is.available();
        byte[] buffer=new byte[size];
        is.read(buffer);
        is.close();
        String json=new String(buffer, "UTF-8");
        return json;
    }
    //reading states from jsonobject and putting them into array
    private State[] readStates(JSONObject jsonObject) throws JSONException{
        JSONArray jsonArray = jsonObject.getJSONArray("states");
        int length=jsonArray.length();
        State[] states=new State[length];
        for(int i=0;i<length;i++){
            JSONObject inner=jsonArray.getJSONObject(i);
            State state=new State(inner.getInt("id"),inner.getBoolean("isAlarmArmed"),inner.getString("name"));
            states[i]=state;
        }
        return states;
    }
    //reading actions from jsonobject and putting them into array
    private Action[] readActions(JSONObject jsonObject) throws JSONException{
        JSONArray jsonArray = jsonObject.getJSONArray("states");
        int length=jsonArray.length();
        Action[] actions=new Action[length];
        for(int i=0;i<length;i++){
            JSONObject inner=jsonArray.getJSONObject(i);
            Action action=new Action(inner.getInt("id"),inner.getString("name"));
            actions[i]=action;
        }
        return actions;
    }
    //reading transitions from jsonobject and putting them into array
    private Transition[] readTransitions(JSONObject jsonObject) throws JSONException{
        JSONArray jsonArray = jsonObject.getJSONArray("transitions");
        int length=jsonArray.length();
        Transition[] transitions=new Transition[length];
        for(int i=0;i<length;i++){
            JSONObject inner=jsonArray.getJSONObject(i);
            Transition transition=new Transition(inner.getInt("id"),inner.getInt("fromStateId"),inner.getInt("toStateId"),inner.getInt("actionId"));
            transitions[i]=transition;
        }
        return transitions;
    }

    //reading initial state from jsonobject
    private State readInitialState(JSONObject jsonObject) throws JSONException{
        State state=null;
        int id=jsonObject.getInt("initialStateId");
        for(State s: states){
            if(s.getId()==id) {
                state=s;
                break;
            }
        }
        return state;
    }
    //changing state based on current state and actionId if such transition exists
    public void changeState(int actionId){
        int nextStateId=-1;
        for(Transition transition : transitions){
            if(transition.getFromStateId()==currentState.getId() && transition.getActionId()==actionId)
                nextStateId=transition.getToStateId();
        }
        if(nextStateId!=-1)
            for(State s: states){
                if(s.getId()==nextStateId) {
                    currentState=s;
                    break;
                }
            }
    }

    public State getCurrentState(){
        return currentState;
    }

    public void setCurrentState(State state){ currentState=state; }

}
