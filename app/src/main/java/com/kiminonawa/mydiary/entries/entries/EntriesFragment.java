package com.kiminonawa.mydiary.entries.entries;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kiminonawa.mydiary.R;
import com.kiminonawa.mydiary.entries.BaseEntriesFragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class EntriesFragment extends BaseEntriesFragment {

    private RecyclerView RecyclerView_entries;
    private EntriesAdapter entriesAdapter;
    private List<EntriesEntity> entriesList;

    public EntriesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_entries, container, false);
        RecyclerView_entries = (RecyclerView) rootView.findViewById(R.id.RecyclerView_entries);

        entriesList = new ArrayList<>();
        //Test data
        Date today = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        entriesList.add(new EntriesEntity(today, "1 day", "Today I am a man!", true));

        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 1);
        entriesList.add(0, new EntriesEntity(calendar.getTime(), "2 day", "Test", false));

        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 1);
        entriesList.add(0, new EntriesEntity(calendar.getTime(), "3 day", "QAQ", false));

        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 1);
        entriesList.add(0, new EntriesEntity(calendar.getTime(), "4 day", "QAQ", true));

        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 1);
        entriesList.add(0, new EntriesEntity(calendar.getTime(), "5 day", "I love this life!", false));

        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 1);
        entriesList.add(0, new EntriesEntity(calendar.getTime(), "6 day", "QAQ", false));

        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 1);
        entriesList.add(0, new EntriesEntity(calendar.getTime(), "7 day", "QAQ", true));

        LinearLayoutManager lmr = new LinearLayoutManager(getActivity());
        RecyclerView_entries.setLayoutManager(lmr);
        entriesAdapter = new EntriesAdapter(getActivity(), entriesList);
        RecyclerView_entries.setAdapter(entriesAdapter);

        return rootView;
    }

}
