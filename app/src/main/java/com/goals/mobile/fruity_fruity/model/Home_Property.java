package com.goals.mobile.fruity_fruity.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mobile on 11/30/2016.
 */

public class Home_Property {
    @SerializedName("result")
    @Expose
    private List<Home_Result> result = new ArrayList<Home_Result>();

    /**
     *
     * @return
     * The result
     */
    public List<Home_Result> getResult() {
        return result;
    }

    /**
     *
     * @param result
     * The result
     */
    public void setResult(List<Home_Result> result) {
        this.result = result;
    }

}