package com.bignerdranch.android.criminalintent;


import android.support.v4.app.Fragment;

/**
 * Created by Aaron on 12/13/2015.
 */
public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment(){
        return new CrimeListFragment();
    }
}
