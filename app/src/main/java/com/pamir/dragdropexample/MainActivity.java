package com.pamir.dragdropexample;

import android.animation.ValueAnimator;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnDragListener {

    private LinearLayout horizontalContainer;
    private Button btn1, btn2, btn3, btn4;
    private CoordinatorLayout activityMain;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ActivityMainBinding binding = DataBindingUtil.setContentView(this, LAYOUT);

        horizontalContainer = (LinearLayout) findViewById(R.id.horizontalContainer);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        activityMain = (CoordinatorLayout) findViewById(R.id.activity_main);


        horizontalContainer.setOnDragListener(this);
//        btn1.setOnTouchListener(this);
//        btn2.setOnTouchListener(this);
//        btn3.setOnTouchListener(this);
//        btn4.setOnTouchListener(this);

        btn1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                // Instantiates the drag shadow builder.
                View.DragShadowBuilder myShadow = new MyDragShadowBuilder(v);

                // Starts the drag

                v.startDrag(null,  // the data to be dragged
                        myShadow,  // the drag shadow builder
                        v,      // no need to use local data
                        0          // flags (not currently used, set to 0)
                );


                return false;
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int[] pos = new int[2];
                view.getLocationOnScreen(pos);
                pos[0] = pos[0] + view.getWidth()/2;


                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                intent.putExtra("pos", pos);
                startActivity(intent);

//                Snackbar.make(activityMain, "Snackbar hey hoy", Snackbar.LENGTH_INDEFINITE).setAction("Tamam", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//
//                    }
//                }).show();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ThirdActivity.class));
            }
        });



        View bottomSheet = findViewById(R.id.design_bottom_sheet);
        final TextView bottomSheetTextView = (TextView) bottomSheet.findViewById(R.id.bottomsheet_text);
        final BottomSheetBehavior behavior = BottomSheetBehavior.from(bottomSheet);
        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_DRAGGING:
                        Log.i("BottomSheetCallback", "BottomSheetBehavior.STATE_DRAGGING");
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        Log.i("BottomSheetCallback", "BottomSheetBehavior.STATE_SETTLING");
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        Log.i("BottomSheetCallback", "BottomSheetBehavior.STATE_EXPANDED");
                        break;
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        Log.i("BottomSheetCallback", "BottomSheetBehavior.STATE_COLLAPSED");
                        break;
                    case BottomSheetBehavior.STATE_HIDDEN:
                        Log.i("BottomSheetCallback", "BottomSheetBehavior.STATE_HIDDEN");
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                Log.i("BottomSheetCallback", "slideOffset: " + slideOffset);
//                fab.setScaleX(1-slideOffset);
//                fab.setScaleY(1-slideOffset);
                bottomSheetTextView.setAlpha(slideOffset);
            }
        });


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (behavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
//                    behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
//                } else {
//                    behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
//                }

                int[] pos = new int[2];
                view.getLocationOnScreen(pos);
                pos[0] = pos[0] + view.getWidth()/2;


                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                intent.putExtra("pos", pos);
                startActivity(intent);

            }
        });

    }

    @Override
    public boolean onDrag(View v, DragEvent dragEvent) {
        if (dragEvent.getAction() == DragEvent.ACTION_DROP) {
            //we want to make sure it is dropped only to left and right parent view
            View view = (View) dragEvent.getLocalState();

            if (v.getId() == R.id.horizontalContainer) {

                ViewGroup source = (ViewGroup) view.getParent();
                source.removeView(view);

                LinearLayout target = (LinearLayout) v;
                target.addView(view);
            }
            //make view visible as we set visibility to invisible while starting drag
            view.setVisibility(View.VISIBLE);
        }
        return true;
    }

//    @Override
//    public boolean onTouch(final View view, MotionEvent motionEvent) {
//        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
//
////
//
//            final MyDragShadowBuilder shadowBuilder = new MyDragShadowBuilder(view);
//            view.startDrag(null, shadowBuilder, view, 0);
//            view.setVisibility(View.INVISIBLE);
//
//
//            return true;
//        }
//        return false;
//    }


}
