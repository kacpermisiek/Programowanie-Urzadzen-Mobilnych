package pl.edu.uwr.pum.recyclerviewwordlistjava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class CrimeActivity extends AppCompatActivity {

    public int id;
    private EditText editTitle;
    public CrimeLab crimeList;
    public static Crime currentCrime;
    private CheckBox editSolved;

    public static int dElement = -1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime);

        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);

        if (fragment == null) {
            fragment = new DateFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentContainer, fragment)
                    .commit();
        }

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
        //currentCrime.setDate();
        return super.onOptionsItemSelected(item);
    }

    public void delete_crime(View view) {
        crimeList.removeCrime(id);
        crimeList.setNewId();
        finish();
    }

}