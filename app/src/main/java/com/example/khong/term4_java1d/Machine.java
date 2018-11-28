package com.example.khong.term4_java1d;

public class Machine {

    private String uuid;
    private int startTime;

    public Machine() {
    }  // Needed for Firebase

    public Machine(String uid) {
        uuid = uid;
    }


    public int getStartTime() {
        return this.startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }

}
