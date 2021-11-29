package pl.edu.uwr.pum.recyclerviewwordlistjava;

import static pl.edu.uwr.pum.recyclerviewwordlistjava.CrimeListAdapter.EXTRA_MESSAGE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.text.ParseException;
import java.util.Date;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CrimeListAdapter crimeListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        crimeListAdapter = new CrimeListAdapter(this);
        recyclerView.setAdapter(crimeListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onResume() {

        super.onResume();
        crimeListAdapter.notifyDataSetChanged();
    }

    public void add_crime(View view) {
        int position = CrimeLab.mCrimes.size();
        Intent intent = new Intent(this, CrimeActivity.class);
        intent.putExtra(EXTRA_MESSAGE, position);
        startActivity(intent);
    }
}