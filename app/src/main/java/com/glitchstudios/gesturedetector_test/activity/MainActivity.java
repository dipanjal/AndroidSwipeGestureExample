package com.glitchstudios.gesturedetector_test.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.glitchstudios.gesturedetector_test.R;
import com.glitchstudios.gesturedetector_test.swipegestures.helper.SwipeGestureHelper;
import com.glitchstudios.gesturedetector_test.swipegestures.listener.OnSwipeGestureListener;

public class MainActivity extends AppCompatActivity implements OnSwipeGestureListener {

    //widget
    TextView textView1;

    //vars
    private static final String TAG = "MainActivity";
    SwipeGestureHelper swipeGestureHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView1 = findViewById(R.id.textview_1);
        swipeGestureHelper = SwipeGestureHelper.getInstance(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        textView1.setOnTouchListener(swipeGestureHelper);
    }

    @Override
    public void onSwipeUp() {
        textView1.setText("swipeUp()");
    }

    @Override
    public void onSwipeDown() {
        textView1.setText("swipeDown()");
    }

    @Override
    public void onSwipeLeft() {
        textView1.setText("swipeLeft()");
    }

    @Override
    public void onSwipeRight() {
        textView1.setText("swipeRight()");
    }
}
