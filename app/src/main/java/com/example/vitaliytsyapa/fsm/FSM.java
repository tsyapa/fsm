package com.example.vitaliytsyapa.fsm;

import android.content.Context;

import com.example.vitaliytsyapa.fsm.models.Transition;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Vitaliy Tsyapa on 10/17/2017.
 */

public class FSM implements FSMInterface{

    private String[] states;
    private String[] actions;
    private String currentState;
    private Transition[] transitions;
    private Context context;

    public FSM(Context context) throws IOException,JSONException{
        this.context=context;
        init();
    }

    private void init() throws IOException,JSONException{
        JSONObject jsonObject=new JSONObject(readJsonFile(context));
        states=readArrayFromJson("states", jsonObject);
        actions=readArrayFromJson("actions", jsonObject);
        currentState=readFieldFromJson("initialState",jsonObject);
        transitions=readTransitionsFromJson(jsonObject);
        jsonObject=null;
    }

    protected String readJsonFile(Context context) throws IOException{
        InputStream is=context.getAssets().open("config.json");
        int size=is.available();
        byte[] buffer=new byte[size];
        is.read(buffer);
        is.close();
        String json=new String(buffer, "UTF-8");
        return json;
    }

    protected String[] readArrayFromJson(String name, JSONObject jsonObject) throws JSONException{
        JSONArray jsonArray=jsonObject.getJSONArray(name);
        int length=jsonArray.length();
        String[] array=new String[length];
        for(int i=0;i<length;i++)
            array[i]=jsonArray.getString(i);
        return array;
    }

    protected String readFieldFromJson(String name, JSONObject jsonObject) throws JSONException{
        return jsonObject.getString(name);
    }

    protected Transition[] readTransitionsFromJson(JSONObject jsonObject) throws JSONException{
        JSONArray jsonArray = jsonObject.getJSONArray("transitions");
        int length=jsonArray.length();
        Transition[] transitions=new Transition[length];
        for(int i=0;i<length;i++){
            JSONObject inner=jsonArray.getJSONObject(i);
            Transition transition=new Transition(inner.getString("from"),inner.getString("to"),inner.getString("action"));
            transitions[i]=transition;
        }
        return transitions;
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

}
