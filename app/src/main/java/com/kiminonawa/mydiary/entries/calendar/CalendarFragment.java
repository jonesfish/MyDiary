package com.kiminonawa.mydiary.entries.calendar;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kiminonawa.mydiary.R;
import com.kiminonawa.mydiary.entries.entries.BaseEntriesFragment;

import java.util.Calendar;
import java.util.Date;


public class CalendarFragment extends BaseEntriesFragment {


    /**
     * Calendar
     */
    private Calendar calendar;
    private Date currentDate;
    private String[] monthsFullName;
    private String[] daysFullName;

    /**
     * UI
     */
    private LinearLayout LL_calendar_content;
    private TextView TV_calendar_months, TV_calendar_date, TV_calendar_day;

    public CalendarFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        calendar = Calendar.getInstance();
        currentDate = new Date();
        calendar.setTime(currentDate);
        monthsFullName = this.getResources().getStringArray(R.array.months_full_name);
        daysFullName = this.getResources().getStringArray(R.array.days_full_name);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_cnlendar, container, false);

        LL_calendar_content = (LinearLayout) rootView.findViewById(R.id.LL_calendar_content);
        TV_calendar_months = (TextView) rootView.findViewById(R.id.TV_calendar_months);
        TV_calendar_date = (TextView) rootView.findViewById(R.id.TV_calendar_date);
        TV_calendar_day = (TextView) rootView.findViewById(R.id.TV_calendar_day);

        TV_calendar_months.setText(monthsFullName[calendar.get(Calendar.MONTH)]);
        TV_calendar_date.setText(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));
        TV_calendar_day.setText(daysFullName[calendar.get(Calendar.DAY_OF_WEEK) - 1]);

        return rootView;
    }


}
