package com.example.teamchfirebase.login;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class Adapter extends FragmentPagerAdapter {
    private final Context context;
    int totalTabs;
    // tab titles
    private final String[] tabTitles = new String[]{"LOGIN", "SIGNUP"};

    public Adapter(FragmentManager fm, Context context, int totalTabs) {
        super(fm);
        this.context = context;
        this.totalTabs = totalTabs;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        //Toast.makeText(context, "hello pos :" + position, Toast.LENGTH_LONG).show();
        switch (position) {
            case 0:
                return new Login_Fragment();
            case 1:
                return new Signin_Fragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
    // overriding getPageTitle()
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
