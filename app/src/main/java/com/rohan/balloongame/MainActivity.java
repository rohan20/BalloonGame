package com.rohan.balloongame;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

public class MainActivity extends AppCompatActivity {

    private ViewGroup mContentView;
    private int[] mBalloonColors = new int[3];
    private int mNextColor, mScreenWidth, mScreenHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBalloonColors[0] = Color.argb(255, 255, 0, 0);
        mBalloonColors[1] = Color.argb(255, 0, 255, 0);
        mBalloonColors[2] = Color.argb(255, 0, 0, 255);

        getWindow().setBackgroundDrawableResource(R.drawable.modern_background);

        mContentView = (ViewGroup) findViewById(R.id.activity_main);
        setToFullScreen();

        ViewTreeObserver viewTreeObserver = mContentView.getViewTreeObserver();
        if(viewTreeObserver.isAlive()){
             viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                 @Override
                 public void onGlobalLayout() {
                     mContentView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                     mScreenHeight = mContentView.getHeight();
                     mScreenWidth = mContentView.getWidth();
                 }
             });
        }

        mContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setToFullScreen();
            }
        });

        mContentView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    Balloon b = new Balloon(MainActivity.this, mBalloonColors[mNextColor], 100);
                    b.setX(motionEvent.getX());
                    b.setY(mScreenHeight);
                    mContentView.addView(b);

                    b.releaseBalloon(mScreenHeight, 3000);

                    if(mNextColor + 1 == mBalloonColors.length)
                        mNextColor = 0;
                    else
                        mNextColor++;
                }

                return false;
            }
        });
    }

    private void setToFullScreen() {

        ViewGroup rootLayout =  (ViewGroup) findViewById(R.id.activity_main);

        rootLayout.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        );
    }

    @Override
    protected void onResume() {
        super.onResume();
        setToFullScreen();
    }
}
