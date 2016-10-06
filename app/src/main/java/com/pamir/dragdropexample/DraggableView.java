package com.pamir.dragdropexample;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Pamir on 04/10/2016.
 */

public class DraggableView extends Button {

    float dX;
    float dY;
    int lastAction;

    public DraggableView(Context context) {
        super(context);
    }

    public DraggableView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DraggableView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
//        switch (motionEvent.getActionMasked()){
//            case MotionEvent.ACTION_DOWN:
//                dX = getX() - motionEvent.getRawX();
//                dY = getY() - motionEvent.getRawY();
//                lastAction = MotionEvent.ACTION_DOWN;
//                break;
//            case MotionEvent.ACTION_MOVE:
//                setY(motionEvent.getRawY() + dY);
//                setX(motionEvent.getRawX() + dX);
//                lastAction = MotionEvent.ACTION_MOVE;
//                break;
//            case MotionEvent.ACTION_UP:
//                if( lastAction == MotionEvent.ACTION_DOWN){
//                    Toast.makeText(getContext(), "Clicked!", Toast.LENGTH_LONG).show();
//                }
//                break;
//            default:
//                return false;
//
//        }
//        return true;

        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        int width = getLayoutParams().width;
        int height = getLayoutParams().height;


        if (isInEdge(x, width)) {
            switch (motionEvent.getActionMasked()) {
                case MotionEvent.ACTION_DOWN:
                    dX = getX() - motionEvent.getRawX();
                    dY = getY() - motionEvent.getRawY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    Log.e(">>", "width:" + width + " height:" + height + " x:" + x + " y:" + y);

                    if (getLayoutParams().width > 200) {
                        if (x > 200) {
                            getLayoutParams().width = x;
                            requestLayout();
                        }else{
                            setX(motionEvent.getRawX() + dX);
                        }

                    } else {
                        setX(motionEvent.getRawX() + dX);
                    }


//                    getLayoutParams().height = y;

                    break;
                case MotionEvent.ACTION_UP:
                    break;
            }
        }
        return true;
    }

    public boolean isInEdge(int x, int width){
        return (x - width <= 50 && x - width > 0) || (width - x <= 50 && width - x > 0);
    }
}
