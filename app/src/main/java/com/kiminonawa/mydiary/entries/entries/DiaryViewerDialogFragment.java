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

import com.kiminonawa.mydiary.R;
import com.kiminonawa.mydiary.db.DBManager;

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
    private EditText EDT_diary_title, EDT_diary_content;
    private ImageView IV_diary_delete, IV_diary_clear, IV_diary_save;

    /**
     * Info
     */
    private long diaryId;

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
