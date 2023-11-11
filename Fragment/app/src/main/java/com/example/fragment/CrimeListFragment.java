package com.example.fragment;

import androidx.fragment.app.Fragment;

import java.util.List;

public class CrimeListFragment extends Fragment {
    //...
    private boolean mSubtitleVisible;
    private Callbacks mCallbacks;

    public void updateUI() {
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        List<Crime> crimes = crimeLab.getCrimes();
        if (mAdapter == null) {
            mAdapter = new CrimeAdapter(crimes);
            mCrimeRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setCrimes(crimes);
            mAdapter.notifyDataSetChanged();
        }
        updateSubtitle();
    }
    public interface Callbacks {
        void onCrimeSelected(Crime crime);

        @Override
        public void onAttach(Context context) {
            super.onAttach(context);
            mCallbacks = (Callbacks) context;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setHasOptionsMenu(true);
        }

        //...
        @Override
        public void onSaveInstanceState(Bundle outState) {
            super.onSaveInstanceState(outState);
            outState.putBoolean(SAVED_SUBTITLE_VISIBLE, mSubtitleVisible);
        }

        @Override
        public void onDetach() {
            super.onDetach();
            mCallbacks = null;
        }
        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.new_crime:
                    Crime crime = new Crime();
                    CrimeLab.get(getActivity()).addCrime(crime);
                    updateUI();
                    mCallbacks.onCrimeSelected(crime);
                    return true;
 //...
            }
        }
    }
    private class CrimeHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
 //...
        @Override
        public void onClick(View view) {
            mCallbacks.onCrimeSelected(mCrime);
        }
    }
}
