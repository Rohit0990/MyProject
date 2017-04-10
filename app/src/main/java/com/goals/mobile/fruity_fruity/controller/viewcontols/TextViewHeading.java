package com.goals.mobile.fruity_fruity.controller.viewcontols;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by mb on 12/4/16.
 */
public class TextViewHeading extends TextView {
    public TextViewHeading(Context context, AttributeSet attrs) {
        super(context, attrs);
        Typeface tf = Typeface.createFromAsset(context.getAssets(), "fonts/AvenirLTStd-Roman.ttf");
        setTypeface(tf);
    }
}
