package com.goals.mobile.fruity_fruity.controller.viewcontols;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by mb on 12/4/16.
 */
public class TextViewMedium extends TextView {
    public TextViewMedium(Context context, AttributeSet attrs) {
        super(context, attrs);
        Typeface tf = Typeface.createFromAsset(context.getAssets(), "fonts/AvenirLTStd-Medium.ttf");
        setTypeface(tf);
    }
}
