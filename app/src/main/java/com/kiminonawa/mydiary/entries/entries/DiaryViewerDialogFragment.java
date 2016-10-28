package com.kiminonawa.mydiary.entries.entries;

import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.kiminonawa.mydiary.R;
import com.kiminonawa.mydiary.db.DBManager;
import com.kiminonawa.mydiary.shared.TimeTools;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by daxia on 2016/10/27.
 */

public class DiaryViewerDialogFragment extends DialogFragment implements View.OnClickListener {


    /**
     * Callback
     */
    public interface DiaryViewerCallback {
        void deleteDiary();
    }

    private DiaryViewerCallback callback;


    /**
     * UI
     */

    private TextView TV_diary_month, TV_diary_date, TV_diary_day, TV_diary_time;
    private EditText EDT_diary_title, EDT_diary_content;
    private ImageView IV_diary_delete, IV_diary_clear, IV_diary_save;

    /**
     * Info
     */
    private long diaryId;


    //TODO Make this dialog's background has radius.
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static DiaryViewerDialogFragment newInstance(long diaryId) {
        Bundle args = new Bundle();
        DiaryViewerDialogFragment fragment = new DiaryViewerDialogFragment();
        args.putLong("diaryId", diaryId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        // request a window without the title
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        try {
            diaryId = getArguments().getLong("diaryId", -1L);
            if (diaryId != -1) {
                DBManager dbManager = new DBManager(getActivity());
                dbManager.opeDB();
                Cursor diaryCursor = dbManager.selectDiaryByDiaryId(diaryId);
                EDT_diary_title.setText(diaryCursor.getString(2));
                EDT_diary_content.setText(diaryCursor.getString(3));
                setDiaryTime(new Date(diaryCursor.getLong(1)));
                diaryCursor.close();
                dbManager.closeDB();
            }
        } catch (NullPointerException e) {
            Log.d("DiaryViewerDialog", e.toString());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.getDialog().setCanceledOnTouchOutside(false);

        View rootView = inflater.inflate(R.layout.fragment_diary, container);
        EDT_diary_title = (EditText) rootView.findViewById(R.id.EDT_diary_title);
        EDT_diary_content = (EditText) rootView.findViewById(R.id.EDT_diary_content);

        TV_diary_month = (TextView) rootView.findViewById(R.id.TV_diary_month);
        TV_diary_date = (TextView) rootView.findViewById(R.id.TV_diary_date);
        TV_diary_day = (TextView) rootView.findViewById(R.id.TV_diary_day);
        TV_diary_time = (TextView) rootView.findViewById(R.id.TV_diary_time);

        IV_diary_delete = (ImageView) rootView.findViewById(R.id.IV_diary_delete);
        IV_diary_delete.setOnClickListener(this);
        IV_diary_clear = (ImageView) rootView.findViewById(R.id.IV_diary_clear);
        IV_diary_clear.setVisibility(View.GONE);
        IV_diary_save = (ImageView) rootView.findViewById(R.id.IV_diary_save);
        IV_diary_save.setVisibility(View.GONE);

        EDT_diary_title.setClickable(false);
        EDT_diary_title.setKeyListener(null);
        EDT_diary_content.setClickable(false);
        EDT_diary_content.setKeyListener(null);

        return rootView;
    }

    private void setDiaryTime(Date diaryDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(diaryDate);
        TimeTools timeTools = TimeTools.getInstance(getActivity().getApplicationContext());
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        TV_diary_month.setText(timeTools.getMonthsFullName()[calendar.get(Calendar.MONTH)]);
        TV_diary_date.setText(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));
        TV_diary_day.setText(timeTools.getDaysFullName()[calendar.get(Calendar.DAY_OF_WEEK) - 1]);
        TV_diary_time.setText(sdf.format(calendar.getTime()));
    }

    public void setCallBack(DiaryViewerCallback callback) {
        this.callback = callback;
    }


    private void deleteDiary() {
        DBManager dbManager = new DBManager(getActivity());
        dbManager.opeDB();
        dbManager.delDiary(diaryId);
        dbManager.closeDB();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.IV_diary_delete:
                deleteDiary();
                callback.deleteDiary();
                dismiss();
                break;
        }

    }
}
