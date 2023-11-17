package com.example.fragment;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import java.util.UUID;

public class CrimeFragment extends Fragment {
    private Crime mCrime;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;
    private Button mSuspectButton;
    private Button mReportButton;
    private ImageButton mPhotoButton;
    private ImageView mPhotoView;
    private static final String ARG_CRIME_ID = "crime_id";
    private static final int REQUEST_DATE = 0;
    private static final int REQUEST_CONTACT = 1;
    public static CrimeFragment newInstance(UUID crimeId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_CRIME_ID, crimeId);
        CrimeFragment fragment = new CrimeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID crimeId = (UUID) getArguments().getSerializable(ARG_CRIME_ID);
        mCrime = CrimeLab.get(getActivity()).getCrime(crimeId);
    }
    @Override
    public void onPause() {
        super.onPause();
        CrimeLab.get(getActivity())
                .updateCrime(mCrime);
    }
    @Override
    private void updateDate() {
        mDateButton.setText(mCrime.getDate().toString());

        private String getCrimeReport() {
            String solvedString = null;
            if (mCrime.isSolved()) {
                solvedString = getString(R.string.crime_report_solved);
            } else {
                solvedString = getString(R.string.crime_report_unsolved);
            }
            String dateFormat = "EEE, MMM dd";
            String dateString = DateFormat.format(dateFormat,
                    mCrime.getDate()).toString();
            String suspect = mCrime.getSuspect();
            if (suspect == null) {
                suspect = getString(R.string.crime_report_no_suspect);
            } else {
                suspect = getString(R.string.crime_report_suspect, suspect);
            }
            String report = getString(R.string.crime_report,
                    mCrime.getTitle(), dateString, solvedString, suspect);
            return report;
        }
    }

        @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_crime, container, false);

        mTitleField = (EditText) v.findViewById(R.id.crime_title);
        mTitleField.setText(mCrime.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {
                // Здесь намеренно оставлено пустое место
            }
            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                mCrime.setTitle(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        mSolvedCheckBox = (CheckBox)v.findViewById(R.id.crime_solved);
        mSolvedCheckBox.setChecked(mCrime.isSolved());

        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mCrime.setSolved(b);
            }
        });


        mDateButton = (Button) v.findViewById(R.id.crime_date);
        mDateButton.setText(mCrime.getDate().toString());
        mDateButton.setEnabled(false);
        mReportButton = (Button) v.findViewById(R.id.crime_report);
        mReportButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("text/plain");
                    i.putExtra(Intent.EXTRA_TEXT, getCrimeReport());
                    i.putExtra(Intent.EXTRA_SUBJECT,
                            getString(R.string.crime_report_subject));
                    i = Intent.createChooser(i, getString(R.string.send_report));
                    startActivity(i);
                }
        });
            final Intent pickContact = new Intent(Intent.ACTION_PICK,
                    ContactsContract.Contacts.CONTENT_URI);
            pickContact.addCategory(Intent.CATEGORY_HOME);
            mSuspectButton = (Button) v.findViewById(R.id.crime_suspect);
            mSuspectButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    startActivityForResult(pickContact, REQUEST_CONTACT);
                }
            });
            if (mCrime.getSuspect() != null) {
                mSuspectButton.setText(mCrime.getSuspect());
            }
            PackageManager packageManager = getActivity().getPackageManager();
            if (packageManager.resolveActivity(pickContact,
                    PackageManager.MATCH_DEFAULT_ONLY) == null) {
                mSuspectButton.setEnabled(false);
            }
            mPhotoButton = (ImageButton) v.findViewById(R.id.crime_camera);
            mPhotoView = (ImageView) v.findViewById(R.id.crime_photo);
        return v;
    }
}
}
