package com.cefalo.school.model;

public class SFMAction {
    public Enum actionType;
    public String description = "";

    public SFMAction(Enum actionType){
        this.actionType = actionType;
    }

    public SFMAction(Enum actionType, String msg){
        this.actionType = actionType;
        this.description = msg;
    }
}
