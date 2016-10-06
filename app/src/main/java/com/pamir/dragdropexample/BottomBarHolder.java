package com.pamir.dragdropexample;

import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Pamir on 06/10/2016.
 */

public class BottomBarHolder {
    private LinearLayout container;
    private ImageView icon;
    private TextView title;

    private boolean isActive;

    public BottomBarHolder(LinearLayout container, ImageView icon, TextView title){
        this.container = container;
        this.icon = icon;
        this.title = title;
    }

    public void setActive(boolean isActive){
        this.isActive = isActive;
        if(this.isActive){
            expand();
        }else{
            shrink();
        }
    }

    public void setClickListener(View.OnClickListener listener){
        container.setOnClickListener(listener);
    }

    public boolean isActive(){
        return isActive;
    }

    private void expand(){
        int color = Color.parseColor("#009688");
        icon.setColorFilter( color );
        title.setTextColor( color );
        title.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
    }

    private void shrink(){
        int color = Color.parseColor("#9E9E9E");
        icon.setColorFilter( color );
        title.setTextColor( color );
        title.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
    }
}
