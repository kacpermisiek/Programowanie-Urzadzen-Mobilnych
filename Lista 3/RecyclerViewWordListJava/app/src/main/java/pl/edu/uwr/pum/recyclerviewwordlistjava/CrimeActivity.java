package pl.edu.uwr.pum.recyclerviewwordlistjava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime);

        editTitle = findViewById(R.id.title_edit);
        editSolved = findViewById(R.id.set_solved);
        dateButton = findViewById(R.id.date_edit_button);

        Intent intent = getIntent();
        id = intent.getIntExtra(CrimeListAdapter.EXTRA_MESSAGE, 0);
        crimeList = CrimeLab.get(this);
        currentCrime = crimeList.getCrime(id);
        dateButton.setText(currentCrime.getDate().toString());
        editTitle.setText(currentCrime.getTitle());
        editSolved.setChecked(currentCrime.getSolved());
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        currentCrime.setTitle(editTitle.getText().toString());
        currentCrime.setSolved(editSolved.isChecked());
        return super.onOptionsItemSelected(item);
    }

    public void delete_crime(View view) {
        crimeList.removeCrime(id);
        crimeList.setNewId();
        finish();
    }


    public void showDateTimePicker(View view) {
        final Calendar currentDate = Calendar.getInstance();
        date = Calendar.getInstance();
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                date.set(year, monthOfYear, dayOfMonth);
                new TimePickerDialog(peekAvailableContext(), new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        date.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        date.set(Calendar.MINUTE, minute);
                        currentCrime.setDate(date.getTime());
                        dateButton.setText(date.getTime().toString());
                    }
                }, currentDate.get(Calendar.HOUR_OF_DAY), currentDate.get(Calendar.MINUTE), false).show();
            }
        }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE)).show();
    }
}