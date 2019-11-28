package com.glowingsoft.bushdigital;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.glowingsoft.bushdigital.Fragments.DynamicFragment;

import java.util.ArrayList;

public class PlansPagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    ArrayList<String> tabTitle;

    public PlansPagerAdapter(FragmentManager fm, int NumOfTabs, ArrayList<String> tabTitle) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        this.tabTitle = tabTitle;
    }

    @Override
    public Fragment getItem(int position) {
        return new DynamicFragment().newInstance(tabTitle.get(position));
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}