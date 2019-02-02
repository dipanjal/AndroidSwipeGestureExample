package com.glitchstudios.gesturedetector_test.swipegestures.helper;

import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.glitchstudios.gesturedetector_test.swipegestures.listener.OnSwipeGestureListener;

public class SwipeGestureHelper implements View.OnTouchListener, GestureDetector.OnGestureListener {

    GestureDetector gestureDetector;

    private static final String TAG = "SwipeGestureHelper";
    private static SwipeGestureHelper instance;
    private Context avtivityContext;
    OnSwipeGestureListener onSwipeGestureListener;

    public static final int SWIPE_THRESHOLD = 100;
    public static final int SWIPE_VELOCITY_THRESHOLD = 100;


    private SwipeGestureHelper(Context context){
        this.avtivityContext = context;
        this.onSwipeGestureListener = (OnSwipeGestureListener) context;
        gestureDetector = new GestureDetector(this.avtivityContext,this);
    }

    public static synchronized SwipeGestureHelper getInstance(Context context){
        if(instance==null){
            instance = new SwipeGestureHelper(context);
        }
        return instance;
    }

    //OnTouchListener Overrides
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return true;
    }

    //OnGestureListener Overrides
    @Override
    public boolean onDown(MotionEvent e) {
        Log.d(TAG, "onDown: "+e.getAction());
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        Log.d(TAG, "onShowPress: "+e.getAction());
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Log.d(TAG, "onSingleTapUp: "+e.getAction());
//        textView1.setText("onSingleTapUp()");
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Log.d(TAG, "onScroll: (x="+ e1.getX()+", y="+ e1.getY());
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        Log.d(TAG, "onLongPress: "+e.getAction());
//        textView1.setText("onLongPress()");
    }

    @Override
    public boolean onFling(MotionEvent downEvent, MotionEvent moveEvent, float velocityX, float velocityY) {
        boolean flag = false;
        double diffX = moveEvent.getX() - downEvent.getX();
        double diffY = moveEvent.getY() - downEvent.getY();
        if(Math.abs(diffX) > Math.abs(diffY)){
            //swipe left right
            if(Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD){
                if(diffX>0){
                    //swipe right
                    swipeRight();
                }else{
                    //swipe left
                    swipeLeft();
                }
                flag = true;
            }
        }else{
            //swipe up down
            if(Math.abs(diffY)>SWIPE_THRESHOLD && Math.abs(velocityY)>SWIPE_VELOCITY_THRESHOLD){
                if(diffY>0){
                    swipeDown();
                }else{
                    swipeUp();
                }
                flag = true;
            }
        }
        return flag;
    }

    private void swipeUp() {
        Log.d(TAG, "swipeUp: called");
        onSwipeGestureListener.onSwipeUp();
//        Toast.makeText(this, "swipeUp()", Toast.LENGTH_SHORT).show();
//        textView1.setText("swipeUp()");
    }

    private void swipeDown() {
        Log.d(TAG, "swipeDown: called");
        onSwipeGestureListener.onSwipeDown();
//        Toast.makeText(this, "swipeDown()", Toast.LENGTH_SHORT).show();
//        textView1.setText("swipeDown()");
    }

    private void swipeLeft() {
        onSwipeGestureListener.onSwipeLeft();
        Log.d(TAG, "swipeLeft: called");
//        Toast.makeText(this, "swipeLeft()", Toast.LENGTH_SHORT).show();
//        textView1.setText("swipeLeft()");
    }

    private void swipeRight() {
        onSwipeGestureListener.onSwipeRight();
        Log.d(TAG, "swipeRight: called");
//        Toast.makeText(this, "swipeRight()", Toast.LENGTH_SHORT).show();
//        textView1.setText("swipeRight()");

    }


}
