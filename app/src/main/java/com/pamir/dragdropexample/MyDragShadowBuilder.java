package com.pamir.dragdropexample;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Point;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Pamir on 03/10/2016.
 */

public class MyDragShadowBuilder extends View.DragShadowBuilder {

    private Point mScaleFactor;
    // Defines the constructor for myDragShadowBuilder
    public MyDragShadowBuilder(View view) {

        // Stores the View parameter passed to myDragShadowBuilder.
        super(view);


    }

    // Defines a callback that sends the drag shadow dimensions and touch point back to the
    // system.
    @Override
    public void onProvideShadowMetrics (final Point size, final Point touch) {
        // Defines local variables
         final int width;
        final int height;

        // Sets the width of the shadow to half the width of the original View
        width = getView().getWidth();

        // Sets the height of the shadow to half the height of the original View
        height = getView().getHeight() ;

        // Sets the size parameter's width and height values. These get back to the system
        // through the size parameter.
        size.set(width, height);
        // Sets size parameter to member that will be used for scaling shadow image.
        mScaleFactor = size;


    }

    @Override
    public void onDrawShadow(final Canvas canvas) {

        ValueAnimator anim = ValueAnimator.ofInt(getView().getWidth(),  getView().getWidth() / 2);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {


                int val = (Integer) valueAnimator.getAnimatedValue();

                // Sets size parameter to member that will be used for scaling shadow image.
                mScaleFactor.set(val, canvas.getWidth());

                // Draws the ColorDrawable in the Canvas passed in from the system.
                canvas.scale(mScaleFactor.x/(float)getView().getWidth(), mScaleFactor.y/(float)getView().getHeight());
                getView().draw(canvas);

            }
        });
        anim.setDuration(250);
        anim.start();

    }

}