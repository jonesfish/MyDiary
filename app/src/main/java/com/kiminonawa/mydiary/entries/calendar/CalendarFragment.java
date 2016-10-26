package com.kiminonawa.mydiary.entries.calendar;


import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kiminonawa.mydiary.R;
import com.kiminonawa.mydiary.entries.entries.BaseEntriesFragment;

import java.util.Calendar;
import java.util.Date;


public class CalendarFragment extends BaseEntriesFragment implements Animation.AnimationListener {


    /**
     * Calendar
     */
    private Calendar calendar;
    private Date currentDate;
    private String[] monthsFullName;
    private String[] daysFullName;
    private int dateChange = 0;
    private int miniTouchGestureWidth = 120;

    /**
     * Animation
     */
    Animation fadeOutAm, fadeInAm;

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
        initAnimation();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_cnlendar, container, false);

        LL_calendar_content = (LinearLayout) rootView.findViewById(R.id.LL_calendar_content);
        TV_calendar_months = (TextView) rootView.findViewById(R.id.TV_calendar_months);
        TV_calendar_date = (TextView) rootView.findViewById(R.id.TV_calendar_date);
        TV_calendar_day = (TextView) rootView.findViewById(R.id.TV_calendar_day);
        updateCalendar();

        LL_calendar_content.setOnTouchListener(canlderOnTouchListener);

        return rootView;
    }

    private void updateCalendar() {
        TV_calendar_months.setText(monthsFullName[calendar.get(Calendar.MONTH)]);
        TV_calendar_date.setText(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));
        TV_calendar_day.setText(daysFullName[calendar.get(Calendar.DAY_OF_WEEK) - 1]);
    }

    private void initAnimation() {
        //TODO This aniimation is temp , it should be used by paper curl effect!
        fadeInAm = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in_am);
        fadeOutAm = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_out_am);
        fadeOutAm.setAnimationListener(this);
    }


    private void startAnimation() {
        try {
            fadeOutAm.reset();
            LL_calendar_content.clearAnimation();
            LL_calendar_content.startAnimation(fadeOutAm);
        } catch (Resources.NotFoundException e) {
            Log.e("NotFoundException", e.toString());
        }
    }

    @Override
    public void onAnimationStart(Animation animation) {
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        if (animation == fadeOutAm) {
            calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + dateChange);
            updateCalendar();
            LL_calendar_content.startAnimation(fadeInAm);
        }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    private View.OnTouchListener canlderOnTouchListener = new View.OnTouchListener() {
        private float initialTouchX;

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                    initialTouchX = event.getRawX();
                    break;
                case MotionEvent.ACTION_UP:
                    if ((initialTouchX - event.getRawX()) > miniTouchGestureWidth) {
                        dateChange = 1;
                        startAnimation();
                    } else if ((initialTouchX - event.getRawX()) < (-miniTouchGestureWidth)) {
                        dateChange = -1;
                        startAnimation();
                    }
                    v.getParent().requestDisallowInterceptTouchEvent(false);

                    break;
                case MotionEvent.ACTION_MOVE:
                    break;
            }

            return true;
        }
    };
}
