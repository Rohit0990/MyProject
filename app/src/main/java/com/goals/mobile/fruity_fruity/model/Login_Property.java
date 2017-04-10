package com.goals.mobile.fruity_fruity.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Mobile on 11/28/2016.
 */

public class Login_Property {

    @SerializedName("result")
    @Expose
    private Login_Result result;

    /**
     *
     * @return
     * The result
     */
    public Login_Result getResult() {
        return result;
    }

    /**
     *
     * @param result
     * The result
     */
    public void setResult(Login_Result result) {
        this.result = result;
    }

}