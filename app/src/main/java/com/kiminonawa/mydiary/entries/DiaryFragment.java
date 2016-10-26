package com.kiminonawa.mydiary.entries;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kiminonawa.mydiary.R;
import com.kiminonawa.mydiary.entries.entries.BaseEntriesFragment;


/**
 * This page doesn't be used in the movie.
 */

public class DiaryFragment extends BaseEntriesFragment {


    public DiaryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_diary, container, false);
        return rootView;
    }

}
