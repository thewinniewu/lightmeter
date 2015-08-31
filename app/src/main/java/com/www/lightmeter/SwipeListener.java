package com.www.lightmeter;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by winniewu on 8/30/15.
 */
public class SwipeListener implements View.OnTouchListener {
    private final static int MIN_SWIPE_DIST = 100;
    private float downX, downY, upX, upY;
    private View v;

    public enum SwipeTypeEnum {
        RIGHT_TO_LEFT, LEFT_TO_RIGHT, TOP_TO_BOTTOM, BOTTOM_TO_TOP
    }

    abstract static public class OnSwipeEvent {
        public boolean SwipeEventDetected(View v, SwipeTypeEnum SwipeType) {
            return false;
        }
    }

    private OnSwipeEvent swipeEventListener;

    public SwipeListener(View v, OnSwipeEvent listener) {
        this.v = v;
        v.setOnTouchListener(this);
        try {
            swipeEventListener = listener;
        } catch (ClassCastException e) {
            Log.e("ClassCastException", "please pass SwipeListener.OnSwipeEvent Interface instance", e);
        }
    }

    public void onRightToLeftSwipe() {
        if (swipeEventListener != null)
            swipeEventListener.SwipeEventDetected(v, SwipeTypeEnum.RIGHT_TO_LEFT);
    }

    public void onLeftToRightSwipe() {
        if (swipeEventListener != null)
            swipeEventListener.SwipeEventDetected(v, SwipeTypeEnum.LEFT_TO_RIGHT);
    }

    public void onTopToBottomSwipe() {
        if (swipeEventListener != null)
            swipeEventListener.SwipeEventDetected(v, SwipeTypeEnum.TOP_TO_BOTTOM);
    }

    public void onBottomToTopSwipe() {
        if (swipeEventListener != null)
            swipeEventListener.SwipeEventDetected(v, SwipeTypeEnum.BOTTOM_TO_TOP);
    }

    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                downX = event.getX();
                downY = event.getY();
                return true;
            }
            case MotionEvent.ACTION_UP: {
                upX = event.getX();
                upY = event.getY();

                float deltaX = downX - upX;
                float deltaY = downY - upY;

                //HORIZONTAL SCROLL
                if (Math.abs(deltaX) > Math.abs(deltaY)) {
                    if (Math.abs(deltaX) > MIN_SWIPE_DIST) {
                        // left or right
                        if (deltaX < 0) {
                            this.onLeftToRightSwipe();
                            return true;
                        }
                        if (deltaX > 0) {
                            this.onRightToLeftSwipe();
                            return true;
                        }
                    } else {
                        return false;
                    }
                }
                //VERTICAL SCROLL
                else {
                    if (Math.abs(deltaY) > MIN_SWIPE_DIST) {
                        // top or down
                        if (deltaY < 0) {
                            this.onTopToBottomSwipe();
                            return true;
                        }
                        if (deltaY > 0) {
                            this.onBottomToTopSwipe();
                            return true;
                        }
                    } else {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }
}
