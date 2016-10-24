package com.kiminonawa.mydiary.main;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kiminonawa.mydiary.R;
import com.kiminonawa.mydiary.entries.EntriesActivity;
import com.kiminonawa.mydiary.main.topic.ITopic;

import java.util.List;

/**
 * Created by daxia on 2016/10/17.
 */

public class MainTopicAdapter extends RecyclerView.Adapter<MainTopicAdapter.TopicViewHolder> {


    private List<ITopic> topicList;
    private Context mContext;

    public MainTopicAdapter(Context context, List<ITopic> topicList) {
        this.mContext = context;
        this.topicList = topicList;
    }


    @Override
    public TopicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_topic_item, parent, false);
        return new TopicViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return topicList.size();
    }

    @Override
    public void onBindViewHolder(TopicViewHolder holder, final int position) {
        holder.getIconView().setImageResource(topicList.get(position).getIcon());
        holder.getTitleView().setText(topicList.get(position).getTitle());
        holder.getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (topicList.get(position).getType()) {
                    case ITopic.TYPE_ENTRIES:
                        Intent goEntriesPageIntent = new Intent(mContext, EntriesActivity.class);
                        mContext.startActivity(goEntriesPageIntent);
                        break;
                }
            }
        });
    }

    protected class TopicViewHolder extends RecyclerView.ViewHolder {

        private ImageView IV_topic_icon;
        private TextView TV_topic_title;
        private View rootView;

        protected TopicViewHolder(View view) {
            super(view);
            this.rootView = view;
            this.IV_topic_icon = (ImageView) rootView.findViewById(R.id.IV_topic_icon);
            this.TV_topic_title = (TextView) rootView.findViewById(R.id.TV_topic_title);
        }

        protected ImageView getIconView() {
            return IV_topic_icon;
        }

        protected TextView getTitleView() {
            return TV_topic_title;
        }

        protected View getRootView() {
            return rootView;
        }
    }
}
