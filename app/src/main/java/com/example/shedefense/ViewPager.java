package com.example.shedefense;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


public class ViewPager extends FragmentPagerAdapter {



    public ViewPager(@NonNull FragmentManager fm,int behavior)
    {
        super(fm, behavior);
    }
    @NonNull
    @Override

    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:return new WelcomeFragment();
            case 1:return new SosFragment();
            case 2:return new EmCallFragment();
            case 3:return new TrackFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
