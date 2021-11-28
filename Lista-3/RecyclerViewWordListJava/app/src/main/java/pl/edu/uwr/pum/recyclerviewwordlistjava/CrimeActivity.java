package pl.edu.uwr.pum.recyclerviewwordlistjava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class CrimeActivity extends AppCompatActivity {

    private int id;
    private EditText editTitle;
    private CrimeLab crimeList;
    private Crime currentCrime;
    private CheckBox editSolved;

    public static int dElement = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime);

        editTitle = findViewById(R.id.title_edit);
        editSolved = findViewById(R.id.set_solved);

        Intent intent = getIntent();
        id = intent.getIntExtra(CrimeListAdapter.EXTRA_MESSAGE, 0);
        crimeList = CrimeLab.get(this);
        currentCrime = crimeList.getCrime(id);
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

}