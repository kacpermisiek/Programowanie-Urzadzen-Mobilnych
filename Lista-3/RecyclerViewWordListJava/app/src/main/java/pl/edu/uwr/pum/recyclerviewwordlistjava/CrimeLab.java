package pl.edu.uwr.pum.recyclerviewwordlistjava;

import android.content.Context;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CrimeLab {
    private static CrimeLab sCrimeLab;

    public static List<Crime> mCrimes;

    public int getSize() { return mCrimes.size(); }

    public static CrimeLab get(Context context) {
        if(sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }

        return sCrimeLab;
    }

    private CrimeLab(Context context) {
        mCrimes = new ArrayList<>();
        for (int i = 0; i < 50; i++){
            Crime crime = new Crime();
            crime.setTitle("Crime #"+i);
            crime.setSolved(i%2==0);
            crime.setId(i);
            crime.setDate(new Date());


            mCrimes.add(crime);
        }
    }


    public Crime getCrime(int id) {
        for (Crime crime : mCrimes) {
            if (crime.getId() == id) {
                return crime;
            }
        }
        Crime crime = new Crime();
        crime.setTitle("New Crime");
        crime.setSolved(false);
        crime.setId(mCrimes.size());
        crime.setDate(new Date());
        mCrimes.add(crime);
        return crime;
    }

    public void removeCrime(int id) { mCrimes.remove(id); }





    public void setNewId(){
        int i = 0;
        for (Crime crime : mCrimes) {
            crime.setId(i);
            i++;
        }
    }
}
