package com.kiminonawa.mydiary.entries;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kiminonawa.mydiary.R;
import com.kiminonawa.mydiary.entries.entries.BaseEntriesFragment;


public class CalendarFragment extends BaseEntriesFragment {


    public CalendarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_canlendar, container, false);

        return rootView;
    }

}
