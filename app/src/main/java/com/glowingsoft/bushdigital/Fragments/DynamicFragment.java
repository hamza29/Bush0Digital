package com.glowingsoft.bushdigital.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.glowingsoft.bushdigital.R;
import com.glowingsoft.bushdigital.VideoAdapter;

import java.util.ArrayList;

public class DynamicFragment extends Fragment {

    View view;
    RecyclerView list;
VideoAdapter videoAdapter;
    public static DynamicFragment newInstance(String val) {
        DynamicFragment fragment = new DynamicFragment();
        Bundle args = new Bundle();
        args.putString("someValue", val);
        fragment.setArguments(args);
        return fragment;
    }

    ArrayList<String> videoModelArrayList = new ArrayList<>();
    String node;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dynamic, container, false);
        node = getArguments().getString("someValue");
        list = view.findViewById(R.id.list);
        videoModelArrayList.clear();
        videoAdapter = new VideoAdapter(getActivity(), node);
        list.setLayoutManager(new LinearLayoutManager(getActivity()));
        list.setAdapter(videoAdapter);


        return view;
    }
}