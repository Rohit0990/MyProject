package com.goals.mobile.fruity_fruity.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mobile on 11/30/2016.
 */

public class Card_Property {
    @SerializedName("result")
    @Expose
    private List<Card_Result> result = new ArrayList<Card_Result>();

    /**
     *
     * @return
     * The result
     */
    public List<Card_Result> getResult() {
        return result;
    }

    /**
     *
     * @param result
     * The result
     */
    public void setResult(List<Card_Result> result) {
        this.result = result;
    }

}