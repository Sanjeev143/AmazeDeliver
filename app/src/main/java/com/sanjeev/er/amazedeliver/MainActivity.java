package com.sanjeev.er.amazedeliver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.sanjeev.er.amazedeliver.fragment.HomeFragment;

/* Author Sanjeev Sangral*/

public class MainActivity extends AppCompatActivity {

    private String HOME = "HOME_FRAGMENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().setCustomAnimations(android.R.anim.slide_in_left,
                android.R.anim.slide_out_right,android.R.animator.fade_in, android.R.animator.fade_out)
                .replace(R.id.container,new HomeFragment(),HOME)
                .commitAllowingStateLoss();
    }
}
