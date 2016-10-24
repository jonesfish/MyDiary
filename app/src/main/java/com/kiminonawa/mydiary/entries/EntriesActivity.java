package com.kiminonawa.mydiary.entries;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.kiminonawa.mydiary.R;
import com.kiminonawa.mydiary.entries.entries.BaseEntriesFragment;
import com.kiminonawa.mydiary.entries.entries.EntriesFragment;

import info.hoang8f.android.segmented.SegmentedGroup;

public class EntriesActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener {


    private FragmentManager fragmentManager;


    /**
     * UI
     */
    private LinearLayout LL_entries_topbar_content;
    private ViewPager ViewPager_entries_content;
    private SegmentedGroup SG_entries_topbar;
    private RadioButton But_entries_topbar_entries, But_entries_topbar_calendar, But_entries_topbar_diary;


    /**
     * View pager
     */
    private PagerAdapter mPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entries);


        /**
         * init Fragment
         */
        fragmentManager = getSupportFragmentManager();
        /**
         * Init Viewpager
         */
        ViewPager_entries_content = (ViewPager) findViewById(R.id.ViewPager_entries_content);
        mPagerAdapter = new ScreenSlidePagerAdapter(fragmentManager);
        ViewPager_entries_content.setAdapter(mPagerAdapter);
        ViewPager_entries_content.addOnPageChangeListener(onPageChangeListener);
        /**
         * init UI
         */
        LL_entries_topbar_content = (LinearLayout) findViewById(R.id.LL_entries_topbar_content);
        SG_entries_topbar = (SegmentedGroup) findViewById(R.id.SG_entries_topbar);
        SG_entries_topbar.setOnCheckedChangeListener(this);
        But_entries_topbar_entries = (RadioButton) findViewById(R.id.But_entries_topbar_entries);
        But_entries_topbar_calendar = (RadioButton) findViewById(R.id.But_entries_topbar_calendar);
        But_entries_topbar_diary = (RadioButton) findViewById(R.id.But_entries_topbar_diary);
        But_entries_topbar_entries.setChecked(true);


    }


    public void popTopBar() {
        //TODO make topbar always on Top
//        LL_entries_topbar_content.bringToFront();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.But_entries_topbar_entries:
                ViewPager_entries_content.setCurrentItem(0);
                break;
            case R.id.But_entries_topbar_calendar:
                ViewPager_entries_content.setCurrentItem(1);
                break;
            case R.id.But_entries_topbar_diary:
                ViewPager_entries_content.setCurrentItem(2);
                break;
        }
    }


    private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.SimpleOnPageChangeListener() {
        @Override
        public void onPageSelected(int position) {
            switch (position) {
                default:
                    But_entries_topbar_entries.setChecked(true);
                    break;
                case 1:
                    But_entries_topbar_calendar.setChecked(true);
                    break;
                case 2:
                    But_entries_topbar_diary.setChecked(true);
                    break;
            }
        }
    };

    private class ScreenSlidePagerAdapter extends FragmentPagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public BaseEntriesFragment getItem(int position) {
            BaseEntriesFragment fragment;
            switch (position) {
                default:
                    fragment = new EntriesFragment();
                    break;
                case 1:
                    fragment = new CalendarFragment();
                    break;
                case 2:
                    fragment = new DiaryFragment();
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
