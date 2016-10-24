package com.kiminonawa.mydiary.entries;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.kiminonawa.mydiary.R;
import com.kiminonawa.mydiary.entries.entries.EntriesFragment;

import info.hoang8f.android.segmented.SegmentedGroup;

public class EntriesActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {


    private FragmentManager fragmentManager;


    /**
     * UI
     */
    private LinearLayout LL_entries_topbar_content;
    private SegmentedGroup SG_entries_topbar;
    private RadioButton But_entries_topbar_entries, But_entries_topbar_calendar, But_entries_topbar_diary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entries);


        /**
         * init Fragment
         */
        fragmentManager = getSupportFragmentManager();

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
        Fragment fragment = null;
        Class fragmentClass;
        switch (checkedId) {
            case R.id.But_entries_topbar_entries:
                fragmentClass = EntriesFragment.class;
                break;
            case R.id.But_entries_topbar_calendar:
                fragmentClass = CalendarFragment.class;
                break;
            case R.id.But_entries_topbar_diary:
                fragmentClass = DiaryFragment.class;
                break;
            default:
                fragmentClass = EntriesFragment.class;
                break;
        }
        try {
            fragment = (Fragment) fragmentClass.newInstance();
            fragmentManager.beginTransaction().replace(R.id.RL_entries_content, fragment).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
