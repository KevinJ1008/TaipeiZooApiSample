package com.kevinj1008.taipeizooapisample.api.bean;

import com.kevinj1008.taipeizooapisample.model.Zoo;

import java.util.ArrayList;

public class GetZoos {

    private ArrayList<Zoo> mZoos;

    public GetZoos() {
        mZoos = new ArrayList<>();
    }

    public ArrayList<Zoo> getZoos() {
        return mZoos;
    }

    public void setZoos(ArrayList<Zoo> zoos) {
        mZoos = zoos;
    }

}
