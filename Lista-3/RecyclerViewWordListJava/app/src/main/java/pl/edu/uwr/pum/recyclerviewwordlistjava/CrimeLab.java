package pl.edu.uwr.pum.recyclerviewwordlistjava;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class CrimeLab {
    private static CrimeLab sCrimeLab;

    private List<Crime> mCrimes;

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
            mCrimes.add(crime);
        }
    }

//    public List<Crime> getCrimes(){
//        return mCrimes;
//    }

    public Crime getCrime(int id) {
        for (Crime crime : mCrimes) {
            if (crime.getId() == id) {
                return crime;
            }
        }
        return null;
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
