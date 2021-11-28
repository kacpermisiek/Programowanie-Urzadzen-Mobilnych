package pl.edu.uwr.pum.recyclerviewwordlistjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;


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
}