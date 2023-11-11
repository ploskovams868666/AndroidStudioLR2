package com.example.fragment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class CrimePagerActivity extends AppCompatActivity
        implements CrimeFragment.Callbacks {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_pager);
    }
    @Override
    public void onCrimeUpdated(Crime crime) {
    }
}