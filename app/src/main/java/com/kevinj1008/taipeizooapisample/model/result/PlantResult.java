package com.kevinj1008.taipeizooapisample.model.result;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.kevinj1008.taipeizooapisample.model.Plant;

import java.util.List;

public class PlantResult {

    @SerializedName("limit")
    @Expose
    private int mLimit;
    @SerializedName("offset")
    @Expose
    private int mOffset;
    @SerializedName("count")
    @Expose
    private int mCount;
    @SerializedName("sort")
    @Expose
    private String mSort;
    @SerializedName("results")
    @Expose
    private List<Plant> mPlants = null;

    public int getLimit() {
        return mLimit;
    }

    public void setLimit(int limit) {
        this.mLimit = limit;
    }

    public int getOffset() {
        return mOffset;
    }

    public void setOffset(int offset) {
        this.mOffset = offset;
    }

    public int getCount() {
        return mCount;
    }

    public void setCount(int count) {
        this.mCount = count;
    }

    public String getSort() {
        return mSort;
    }

    public void setSort(String sort) {
        this.mSort = sort;
    }

    public List<Plant> getPlants() {
        return mPlants;
    }

    public void setPlants(List<Plant> plants) {
        this.mPlants = plants;
    }
}
