package com.example.authenticatorapp;

public class Groups_module {
    public String group_name, group_description;

    public Groups_module(String group_name, String group_description) {
        this.group_name = group_name;
        this.group_description = group_description;
    }
    public Groups_module(){


    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getGroup_description() {
        return group_description;
    }

    public void setGroup_description(String group_description) {
        this.group_description = group_description;
    }
}
