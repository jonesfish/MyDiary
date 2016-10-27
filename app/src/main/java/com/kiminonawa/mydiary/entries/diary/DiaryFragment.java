package com.kiminonawa.mydiary.entries.diary;


import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kiminonawa.mydiary.R;
import com.kiminonawa.mydiary.entries.entries.BaseEntriesFragment;
import com.kiminonawa.mydiary.shared.ColorTools;
import com.kiminonawa.mydiary.shared.TimeTools;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * This page doesn't be used in the movie.
 * I define this page for write diary.
 */

public class DiaryFragment extends BaseEntriesFragment {


    private TextView TV_diary_month, TV_diary_date, TV_diary_day, TV_diary_time;

    private ImageView IV_diary_menu, IV_diary_photo, IV_diary_delete;


    private Calendar calendar;
    private Date today;
    private TimeTools timeTools;
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        calendar = Calendar.getInstance();
        today = new Date();
        calendar.setTime(today);
        timeTools = TimeTools.getInstance(getActivity().getApplicationContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_diary, container, false);


        TV_diary_month = (TextView) rootView.findViewById(R.id.TV_diary_month);
        TV_diary_date = (TextView) rootView.findViewById(R.id.TV_diary_date);
        TV_diary_day = (TextView) rootView.findViewById(R.id.TV_diary_day);
        TV_diary_time = (TextView) rootView.findViewById(R.id.TV_diary_time);
        setToday();

        IV_diary_menu = (ImageView) rootView.findViewById(R.id.IV_diary_menu);
        IV_diary_photo = (ImageView) rootView.findViewById(R.id.IV_diary_photo);
        IV_diary_delete = (ImageView) rootView.findViewById(R.id.IV_diary_delete);

        //Disable Button
        IV_diary_menu.setColorFilter(ColorTools.getColor(getActivity(), R.color.button_disable_color), PorterDuff.Mode.SRC_ATOP);
        IV_diary_photo.setColorFilter(ColorTools.getColor(getActivity(), R.color.button_disable_color), PorterDuff.Mode.SRC_ATOP);

        return rootView;
    }

    private void setToday() {
        TV_diary_month.setText(timeTools.getMonthsFullName()[calendar.get(Calendar.MONTH)]);
        TV_diary_date.setText(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));
        TV_diary_day.setText(timeTools.getDaysFullName()[calendar.get(Calendar.DAY_OF_WEEK) - 1]);
        TV_diary_time.setText(sdf.format(calendar.getTime()));
    }

}
