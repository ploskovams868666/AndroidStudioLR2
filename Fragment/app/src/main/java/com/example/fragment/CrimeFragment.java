package com.example.fragment;

public class CrimeFragment {
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
 ...
    }
    private void updateCrime() {
        CrimeLab.get(getActivity()).updateCrime(mCrime);
        mCallbacks.onCrimeUpdated(mCrime);
    }
    private void updateDate() {
        mDateButton.setText(mCrime.getDate().toString());
    }
}
