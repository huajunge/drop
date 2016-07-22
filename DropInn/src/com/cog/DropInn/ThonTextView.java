package com.cog.DropInn;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class ThonTextView extends TextView {
    public ThonTextView(Context context, AttributeSet attrs) {
        super(context, attrs);      
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/PTC75F.ttf"));
    }
}
