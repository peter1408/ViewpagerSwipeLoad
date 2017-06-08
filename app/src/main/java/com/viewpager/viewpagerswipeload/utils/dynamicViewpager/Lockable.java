package com.viewpager.viewpagerswipeload.utils.dynamicViewpager;

/**
 * Created by Peter Punnoose on 6/7/2017.
 */

public interface Lockable {
    void lockSwiping();
    void unlockSwiping();
    void toggleLock();
    boolean isLocked();
}
