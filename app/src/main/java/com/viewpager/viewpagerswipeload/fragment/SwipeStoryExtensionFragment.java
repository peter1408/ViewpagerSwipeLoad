package com.viewpager.viewpagerswipeload.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.viewpager.viewpagerswipeload.R;


/**
 * Created by Peter Punnoose on 6/7/2017.
 */

public class SwipeStoryExtensionFragment extends Fragment {
    public static final String CONTENT_KEY = "fragment_content";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate( R.layout.layout_swipe_story_extension, container, false );
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated( savedInstanceState );

        String content = getArguments() != null ? getArguments().getString( CONTENT_KEY ) : "Unknown";
        TextView text = (TextView) getView().findViewById(R.id.textView);
        text.setText( content );
    }

    public String getContent() {
        return getArguments() != null ? getArguments().getString( CONTENT_KEY ) : "Unknown";
    }
}
