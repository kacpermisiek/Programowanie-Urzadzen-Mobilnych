package pl.edu.uwr.pum.recyclerviewwordlistjava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;

public class CrimeActivity extends AppCompatActivity {

    public static int id;
    private EditText editTitle;
    public CrimeLab crimeList;
    public static Crime currentCrime;
    private CheckBox editSolved;
    private Button dateButton;
    Calendar date;
    ViewPager2 viewPager2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity_view_pager2);

        viewPager2 = findViewById(R.id.detail_view_pager);

        Intent intent = getIntent();

        int currentCrimeId = intent.getIntExtra(CrimeListAdapter.EXTRA_MESSAGE, 0);

        DetailPagerAdapter adapter = new DetailPagerAdapter(this);
        viewPager2.setAdapter(adapter);

        viewPager2.setCurrentItem(currentCrimeId, false);

    }


    public void delete_crime(View view) {
        crimeList.removeCrime(id);
        crimeList.setNewId();
        finish();
    }

    public void firstCrime(View view) { viewPager2.setCurrentItem(0); }

    public void lastCrime(View view) { viewPager2.setCurrentItem(CrimeLab.mCrimes.size()); }

}