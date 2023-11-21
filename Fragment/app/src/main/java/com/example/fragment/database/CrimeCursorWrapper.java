package com.example.fragment.database;

import android.database.Cursor;
import android.database.CursorWrapper;
import java.util.Date;
import java.util.UUID;
import com.example.fragment.Crime;
import com.example.fragment.database.CrimeDbSchema.CrimeTable;



public class CrimeCursorWrapper extends CursorWrapper {
    public CrimeCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Crime getCrime() {
        String uuidString = getString(getColumnIndex(CrimeDbSchema.CrimeTable.Cols.UUID));
        String title = getString(getColumnIndex(CrimeDbSchema.CrimeTable.Cols.TITLE));
        long date = getLong(getColumnIndex(CrimeDbSchema.CrimeTable.Cols.DATE));
        int isSolved = getInt(getColumnIndex(CrimeDbSchema.CrimeTable.Cols.SOLVED));
        Crime crime = new Crime(UUID.fromString(uuidString));
        crime.setTitle(title);
        crime.setDate(new Date(date));
        crime.setSolved(isSolved != 0);
        String suspect = getString(getColumnIndex(CrimeTable.Cols.SUSPECT));
        crime.setSuspect(suspect);
        return crime;

    }
}


