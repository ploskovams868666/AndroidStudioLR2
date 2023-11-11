package com.example.fragment;

import androidx.fragment.app.Fragment;

public class CrimeFragment extends Fragment {
    private ImageButton mPhotoButton;
    private ImageView mPhotoView;
    private Callbacks mCallbacks;
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }
    private void updateCrime() {
        CrimeLab.get(getActivity()).updateCrime(mCrime);
        mCallbacks.onCrimeUpdated(mCrime);
    }
    private void updateDate() {
        mDateButton.setText(mCrime.getDate().toString());
    }
    public interface Callbacks {
        void onCrimeUpdated(Crime crime);
    }
    public static CrimeFragment newInstance(UUID crimeId) {
 //...
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallbacks = (Callbacks) context;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
 //...
    }
    @Override
    public void onPause() {
 //...
    }
    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }
}
