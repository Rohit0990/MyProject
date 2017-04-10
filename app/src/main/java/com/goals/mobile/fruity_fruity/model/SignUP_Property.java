package com.goals.mobile.fruity_fruity.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Mobile on 11/25/2016.
 */

public class SignUP_Property {
    @SerializedName("result")
    @Expose
    private SignUp_Result result;

    /**
     *
     * @return
     * The result
     */
    public SignUp_Result getResult() {
        return result;
    }

    /**
     *
     * @param result
     * The result
     */
    public void setResult(SignUp_Result result) {
        this.result = result;
    }

}
