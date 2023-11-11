package com.example.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CrimeListFragment extends Fragment {
    private boolean mSubtitleVisible;
    private Callbacks mCallbacks;
    public void updateUI(){
    }
    public interface Callbacks {
        void onCrimeSelected(Crime crime);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.new_crime) {
                Crime crime = new Crime();
                CrimeLab.get(getActivity()).addCrime(crime);
                updateUI();
                mCallbacks.onCrimeSelected(crime);
                return true;
        }
        else return false;
    }
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
    private class CrimeHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        public CrimeHolder(@NonNull View itemView) {
            super(itemView);
        }
        @Override
        public void onClick(View view) {
            mCallbacks.onCrimeSelected(mCrime);
        }
    }
}