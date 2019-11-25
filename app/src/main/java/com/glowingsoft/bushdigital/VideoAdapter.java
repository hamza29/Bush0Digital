package com.glowingsoft.bushdigital;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;


public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.MyViewHolder> {
    Context context;
    String operator;
    String videoModels;
    int NORMAL = 1;
    int AD = 0;

    int count = 0;

    public VideoAdapter(Context context, String circleArrayList) {
        this.videoModels = circleArrayList;
        this.context = context;

        Log.i(">>id", "VideoAdapter: " + operator + "-");
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 8 == 0) {
            return AD;
        } else {
            return NORMAL;
        }
    }


    @Override
    public VideoAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_videp, parent, false);
        return new VideoAdapter.MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {
        holder.s.setText("" + videoModels.toUpperCase());
    }

    @Override
    public int getItemCount() {
        return 5;
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView s;

        public MyViewHolder(View itemView) {
            super(itemView);

            s = itemView.findViewById(R.id.a);
        }
    }
}