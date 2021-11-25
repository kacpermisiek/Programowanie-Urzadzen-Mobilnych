package com.example.crimeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    private LinkedList<String> wordlList = new LinkedList<>();
    private RecyclerView recyclerView;
    private WordListAdapter wordListAdapter;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 0; i < 50; i ++)
        {
            wordlList.addLast("Word" + i);
        }

        recyclerView = findViewById(R.id.recyclerView);
        wordListAdapter = new WordListAdapter(this, wordlList);
        recyclerView.setAdapter(wordListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}