package com.pamir.dragdropexample;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.pamir.dragdropexample.databinding.ActivityThirdBinding;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityThirdBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_third);

        final BottomBarHolder tab1 = new BottomBarHolder(binding.tab1, binding.icon1, binding.title1);
        final BottomBarHolder tab2 = new BottomBarHolder(binding.tab2, binding.icon2, binding.title2);
        final BottomBarHolder tab3 = new BottomBarHolder(binding.tab3, binding.icon3, binding.title3);

        tab1.setActive(true);
        tab2.setActive(false);
        tab3.setActive(false);

        tab1.setClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tab1.setActive(true);
                tab2.setActive(false);
                tab3.setActive(false);
            }
        });

        tab2.setClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tab1.setActive(false);
                tab2.setActive(true);
                tab3.setActive(false);
            }
        });

        tab3.setClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tab1.setActive(false);
                tab2.setActive(false);
                tab3.setActive(true);
            }
        });
    }
}
