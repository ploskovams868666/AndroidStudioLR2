package com.example.fragment;

public class CrimeLab {
    public void addCrime(Crime c) {
        mCrimes.add(c);
    }
    public List<Crime> getCrimes() {
        return mCrimes;
    }
}
