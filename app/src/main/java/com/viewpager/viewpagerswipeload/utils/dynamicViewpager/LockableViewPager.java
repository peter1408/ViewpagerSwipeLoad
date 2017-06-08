package com.viewpager.viewpagerswipeload.utils.dynamicViewpager;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Peter Punnoose on 6/7/2017.
 */

public class LockableViewPager extends ViewPager implements  Lockable{

    private boolean locked;
    OnSwipeOutListener mListener;

    public LockableViewPager( final Context context ) {
        super(context);
        unlockSwiping();
    }

    public LockableViewPager( final Context context, final AttributeSet attrs ) {
        super(context, attrs);
        unlockSwiping();
    }

   /* @Override
    public boolean onTouchEvent( MotionEvent event )
    {
        if( !isLocked() ) {
            return super.onTouchEvent( event );
        }
        return false;
    }*/




    public void setOnSwipeOutListener(OnSwipeOutListener listener) {
        mListener = listener;
    }

    private float mStartDragX;


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (getCurrentItem() == getAdapter().getCount() - 1  || getCurrentItem() == 0 ) {
            final int action = ev.getAction();
            float x = ev.getX();
            switch (action & MotionEventCompat.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN:
                    mStartDragX = x;
                    break;
                case MotionEvent.ACTION_MOVE:

                    break;
                case MotionEvent.ACTION_UP:

                    if (mStartDragX < x && getCurrentItem() == 0){
                        mListener.onSwipeOutAtStart();
                    }else if (x < mStartDragX && getCurrentItem() == getAdapter().getCount() - 1) {
                        mListener.onSwipeOutAtEnd();
                    } else {
                        mStartDragX = 0;
                    }
                    break;
            }
        } else {
            mStartDragX = 0;
        }
        return !isLocked() && super.onTouchEvent(ev);
    }



    @Override
    public boolean onInterceptTouchEvent( MotionEvent event )
    {
        if( !isLocked() ) {
            return super.onInterceptTouchEvent( event );
        }
        return false;
    }

    @Override
    public void lockSwiping() {
        this.locked = true;
    }

    @Override
    public void unlockSwiping() {
        this.locked = false;
    }

    @Override
    public void toggleLock() {
        this.locked = !isLocked();
    }

    @Override
    public boolean isLocked() {
        return locked;
    }


    public interface OnSwipeOutListener {
        public void onSwipeOutAtStart();
        public void onSwipeOutAtEnd();
    }


}
