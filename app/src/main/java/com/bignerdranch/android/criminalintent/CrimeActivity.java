package com.bignerdranch.android.criminalintent;


public class CrimeActivity extends SingleFragmentActivity {

    @Override
    protected CrimeListFragment createFragment() {
        return new CrimeListFragment();
    }

}
