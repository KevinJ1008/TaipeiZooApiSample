package com.kevinj1008.taipeizooapisample.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.kevinj1008.taipeizooapisample.model.result.ZooResult;

public class ZooResponse {

    @SerializedName("result")
    @Expose
    private ZooResult mZooResult;

    public ZooResult getZooResult() {
        return mZooResult;
    }

    public void setZooResult(ZooResult zooResult) {
        this.mZooResult = zooResult;
    }
}
