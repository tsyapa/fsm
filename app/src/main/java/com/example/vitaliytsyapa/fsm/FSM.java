package com.example.vitaliytsyapa.fsm;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Vitaliy Tsyapa on 10/17/2017.
 */

public class FSM {

    private ArrayList<String> states;
    private ArrayList<String> actions;
    private String currentState;
    private ArrayList<Transition> transitions;
    private Context context;

    public FSM(Context context){
        this.context=context;
        init();
    }

    private void init(){
        try{
            JSONObject jsonObject=new JSONObject(readJson(context));
            states=readArray("states", jsonObject);
            actions=readArray("actions", jsonObject);
            currentState=readField("initialState",jsonObject);
            transitions=readTransitions(jsonObject);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private String readJson(Context context) throws IOException{
        String json=null;
        InputStream is = context.getAssets().open("config.json");
        int size=is.available();
        byte[] buffer=new byte[size];
        is.read(buffer);
        is.close();
        json=new String(buffer, "UTF-8");
        return json;
    }

    private ArrayList<String> readArray(String name, JSONObject jsonObject) throws JSONException{
        JSONArray jsonArray = jsonObject.getJSONArray(name);
        ArrayList<String> arrayList=new ArrayList<String>();
        for(int i=0;i<jsonArray.length();i++){
            arrayList.add(jsonArray.getString(i));
        }
        return arrayList;
    }

    private String readField(String name, JSONObject jsonObject) throws JSONException{
        return jsonObject.getString(name);
    }

    private ArrayList<Transition> readTransitions(JSONObject jsonObject) throws JSONException{
        JSONArray jsonArray = jsonObject.getJSONArray("transitions");
        ArrayList<Transition> transitionsList=new ArrayList<Transition>();
        for(int i=0;i<jsonArray.length();i++){
            JSONObject inner=jsonArray.getJSONObject(i);
            Transition transition=new Transition(inner.getString("from"),inner.getString("to"),inner.getString("action"));
            transitionsList.add(transition);
        }
        return transitionsList;
    }

    public void changeState(String action){
        String nextState=null;
        for(Transition transition : transitions){
            if(transition.getFromState().equals(currentState) && transition.getAction().equals(action))
                nextState=transition.getToState();
        }
        if(nextState!=null)
            this.currentState=nextState;
    }

    public String getCurrentState(){
        return currentState;
    }

    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
