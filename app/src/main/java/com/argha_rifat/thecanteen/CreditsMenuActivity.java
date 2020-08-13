package com.argha_rifat.thecanteen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class CreditsMenuActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    ArrayList<ModelFood> foodsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits_menu);

        recyclerView = findViewById(R.id.recycler_view);

        foodsList = new ArrayList<>();

        foodsList.add(new ModelFood(R.drawable.rifat,"Rakib Hossain Rifat","16.02.04.099","2.1"));
        foodsList.add(new ModelFood(R.drawable.argha,"Argha Shipan Sarkar","16.02.04.110","2.1"));



        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        RecyclerView.LayoutManager rvLiLayoutManager = layoutManager;

        recyclerView.setLayoutManager(rvLiLayoutManager);

        CreditAdapter adapter = new CreditAdapter(this,foodsList);

        recyclerView.setAdapter(adapter);

    }
}
