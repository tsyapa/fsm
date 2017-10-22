package com.example.vitaliytsyapa.fsm.models;

import android.os.Parcel;
import android.os.Parcelable;

public class State implements Parcelable{

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


    protected State(Parcel in) {
        id = in.readInt();
        isAlarmArmed = in.readByte() != 0x00;
        name = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeByte((byte) (isAlarmArmed ? 0x01 : 0x00));
        dest.writeString(name);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<State> CREATOR = new Parcelable.Creator<State>() {
        @Override
        public State createFromParcel(Parcel in) {
            return new State(in);
        }

        @Override
        public State[] newArray(int size) {
            return new State[size];
        }
    };
}
