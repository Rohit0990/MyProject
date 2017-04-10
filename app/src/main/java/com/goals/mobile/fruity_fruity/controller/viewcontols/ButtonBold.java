package com.goals.mobile.fruity_fruity.controller.viewcontols;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by mb on 12/4/16.
 */
public class ButtonBold extends Button {
    public ButtonBold(Context context, AttributeSet attrs) {
        super(context, attrs);
        Typeface tf = Typeface.createFromAsset(context.getAssets(), "fonts/avenir-95-black-button.ttf");
        setTypeface(tf);
    }
}
