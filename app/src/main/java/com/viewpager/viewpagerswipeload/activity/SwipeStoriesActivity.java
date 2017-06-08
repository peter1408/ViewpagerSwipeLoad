package com.viewpager.viewpagerswipeload.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.widget.TextView;

import com.viewpager.viewpagerswipeload.R;
import com.viewpager.viewpagerswipeload.fragment.SwipeStoryExtensionFragment;
import com.viewpager.viewpagerswipeload.utils.dynamicViewpager.DynamicTabFragmentPagerAdapter;
import com.viewpager.viewpagerswipeload.utils.dynamicViewpager.LockableViewPager;


/**
 * Created by Peter Punnoose on 6/7/2017.
 */

public class SwipeStoriesActivity extends AppCompatActivity {
    private DynamicTabFragmentPagerAdapter adapter;
    private LockableViewPager pager;
    private TextView log;
    private int pageCount = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_swipe_stories);

        // load views
        pager = (LockableViewPager) findViewById(R.id.viewPager);
        adapter = new DynamicTabFragmentPagerAdapter( this, getSupportFragmentManager(), pager);//, getSupportActionBar() );
        log = (TextView) findViewById(R.id.logView);


        pageChangelistner();


        // generate first tree fragments
        for( int position = 1; position <= pageCount; position++) {
            Bundle arguments = new Bundle();
            arguments.putString( SwipeStoryExtensionFragment.CONTENT_KEY, String.valueOf( position ) );
            adapter.addPage(position-1, String.valueOf( position ), SwipeStoryExtensionFragment.class, arguments );
        }
    }



    private void pageChangelistner(){
        pager.setOnSwipeOutListener(new LockableViewPager.OnSwipeOutListener() {
            @Override
            public void onSwipeOutAtStart() {
                Log.e("swipe Out At Start ", "swipe out");
                addPage(0);
            }

            @Override
            public void onSwipeOutAtEnd() {
                Log.e("swipe Out At End ", "swipe end");
                addPage(adapter.getCount());
            }
        });

        pager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int po) {
                Log.e("positon", ""+po);
            }
        });
    }



    private boolean logPager() {
        String log = "";
        for( int position = 0; position < adapter.getCount(); position++ ) {
            Fragment f = adapter.getFragmentInstance( position );
            if( f instanceof SwipeStoryExtensionFragment ) {
                log += ( (SwipeStoryExtensionFragment) f).getContent();
            }
        }
        this.log.setText( "Log: " + log );
        return true;
    }

    private boolean lockPager() {
        pager.toggleLock();
        supportInvalidateOptionsMenu();
        return true;
    }

    private boolean replacePage() {
        pageCount++;
        Bundle arguments = new Bundle();
        arguments.putString( SwipeStoryExtensionFragment.CONTENT_KEY, String.valueOf( pageCount ) );
        adapter.replacePage( getSupportActionBar().getSelectedNavigationIndex(), String.valueOf( pageCount ), SwipeStoryExtensionFragment.class, arguments );
        return true;
    }

    private boolean removePage() {
        adapter.removePage( getSupportActionBar().getSelectedNavigationIndex() );
        return true;
    }

    private boolean addPage(int pos) {
        pageCount++;
        Bundle arguments = new Bundle();
        arguments.putString( SwipeStoryExtensionFragment.CONTENT_KEY, String.valueOf( pageCount ) );
        adapter.addPage( pos, String.valueOf( pageCount ), SwipeStoryExtensionFragment.class, arguments );
        return true;
    }

}