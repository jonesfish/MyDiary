package com.kiminonawa.mydiary.entries;


import android.content.Context;
import android.support.v4.app.Fragment;


public class BaseEntriesFragment extends Fragment {


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        EntriesActivity activity;
        if (context instanceof EntriesActivity) {
            activity = (EntriesActivity) context;
            activity.popTopBar();
        }
    }

}

