package com.kiminonawa.mydiary.entries.diary;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kiminonawa.mydiary.R;
import com.kiminonawa.mydiary.db.DBManager;
import com.kiminonawa.mydiary.entries.BaseDiaryFragment;
import com.kiminonawa.mydiary.entries.DiaryActivity;
import com.kiminonawa.mydiary.shared.TimeTools;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * This page doesn't be used in the movie.
 * I define this page for write diary.
 */

public class DiaryFragment extends BaseDiaryFragment implements View.OnClickListener {


    /**
     * UI
     */
    private LinearLayout LL_diary_time_information;
    private TextView TV_diary_month, TV_diary_date, TV_diary_day, TV_diary_time;
    private EditText EDT_diary_title, EDT_diary_content;

    private ImageView IV_diary_menu, IV_diary_photo, IV_diary_delete, IV_diary_clear, IV_diary_save;

    /**
     * Time
     */
    private Calendar calendar;
    private Date nowDate;
    private TimeTools timeTools;
    private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        calendar = Calendar.getInstance();
        timeTools = TimeTools.getInstance(getActivity().getApplicationContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_diary, container, false);

        LL_diary_time_information = (LinearLayout) rootView.findViewById(R.id.LL_diary_time_information);
        LL_diary_time_information.setOnClickListener(this);

        TV_diary_month = (TextView) rootView.findViewById(R.id.TV_diary_month);
        TV_diary_date = (TextView) rootView.findViewById(R.id.TV_diary_date);
        TV_diary_day = (TextView) rootView.findViewById(R.id.TV_diary_day);
        TV_diary_time = (TextView) rootView.findViewById(R.id.TV_diary_time);


        EDT_diary_title = (EditText) rootView.findViewById(R.id.EDT_diary_title);
        EDT_diary_content = (EditText) rootView.findViewById(R.id.EDT_diary_content);


        IV_diary_menu = (ImageView) rootView.findViewById(R.id.IV_diary_menu);
        IV_diary_photo = (ImageView) rootView.findViewById(R.id.IV_diary_photo);
        IV_diary_delete = (ImageView) rootView.findViewById(R.id.IV_diary_delete);
        IV_diary_clear = (ImageView) rootView.findViewById(R.id.IV_diary_clear);
        IV_diary_clear.setOnClickListener(this);
        IV_diary_save = (ImageView) rootView.findViewById(R.id.IV_diary_save);
        IV_diary_save.setOnClickListener(this);


        IV_diary_delete.setVisibility(View.GONE);


        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        setCurrentTime();
    }

    private void setCurrentTime() {
        nowDate = new Date();
        calendar.setTime(nowDate);
        TV_diary_month.setText(timeTools.getMonthsFullName()[calendar.get(Calendar.MONTH)]);
        TV_diary_date.setText(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));
        TV_diary_day.setText(timeTools.getDaysFullName()[calendar.get(Calendar.DAY_OF_WEEK) - 1]);
        TV_diary_time.setText(sdf.format(calendar.getTime()));
    }

    private void clearDiary() {
        EDT_diary_title.setText("");
        EDT_diary_content.setText("");
    }

    private void saveDiary() {
        DBManager dbManager = new DBManager(getActivity());
        dbManager.opeDB();
        dbManager.insetDiary(calendar.getTimeInMillis(),
                EDT_diary_title.getText().toString(), EDT_diary_content.getText().toString(),
                0, 0, false, getTopicId());
        dbManager.closeDB();
        clearDiary();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.LL_diary_time_information:
                setCurrentTime();
                break;
            case R.id.IV_diary_clear:
                clearDiary();
                break;
            case R.id.IV_diary_save:
                if (EDT_diary_title.length() > 0 && EDT_diary_content.length() > 0) {
                    saveDiary();
                    ((DiaryActivity) getActivity()).gotoPage(0);
                } else {
                    Toast.makeText(getActivity(), "Diary is empty!", Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }
}
