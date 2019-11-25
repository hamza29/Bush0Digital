package com.glowingsoft.bushdigital.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.glowingsoft.bushdigital.CategoriesModel;
import com.glowingsoft.bushdigital.MyApp;
import com.glowingsoft.bushdigital.PlansPagerAdapter;
import com.glowingsoft.bushdigital.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Objects;

import io.objectbox.Box;

public class Categories extends Fragment {
    TabLayout tab;
    ViewPager viewPager;
    ArrayList<String> tabTitle = new ArrayList<>();
    Box<CategoriesModel> siteModelBox;

    public Categories() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    ArrayList<String> category = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categories, container, false);
        tab = view.findViewById(R.id.tabs);
        viewPager = view.findViewById(R.id.frameLayout);
        siteModelBox = ((MyApp) Objects.requireNonNull(getActivity()).getApplication())
                .getBoxStore()
                .boxFor(CategoriesModel.class);
        tabTitle.clear();


//        Log.e("TGED", "SIZE" + siteModelBox.getAll());
        for (int i = 0; i < siteModelBox.getAll().size(); i++) {
            tab.addTab(tab.newTab().setText("" + siteModelBox.getAll().get(i).getTitle()));
            tabTitle.add(siteModelBox.getAll().get(i).getTitle());

        }

        tab.setTabGravity(TabLayout.GRAVITY_CENTER);
        tab.setTabMode(TabLayout.MODE_SCROLLABLE);
        PlansPagerAdapter adapter = new PlansPagerAdapter
                (getChildFragmentManager(), tab.getTabCount(), tabTitle);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));
        tab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

        });
        return view;
    }

    //

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
