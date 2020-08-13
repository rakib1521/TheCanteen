package com.argha_rifat.thecanteen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;


public class DrinksMenuActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView cost;

    ArrayList<ModelFood> foodsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drinks_menu);

        recyclerView = findViewById(R.id.recycler_view);
        cost=(TextView) findViewById(R.id.cost_show);

        foodsList = new ArrayList<>();

        foodsList.add(new ModelFood(R.drawable.dew,"Mountain Dew","Aust Canteen","100"));
        foodsList.add(new ModelFood(R.drawable.pepsi,"Pepsi","Aust Canteen","80"));
        foodsList.add(new ModelFood(R.drawable.seven_up,"7 UP","Aust Canteen","90"));
        foodsList.add(new ModelFood(R.drawable.mum_water,"Mum Water","Aust Canteen","70"));
        foodsList.add(new ModelFood(R.drawable.pran_juice,"Pran Juice","Aust Canteen","80"));


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        RecyclerView.LayoutManager rvLiLayoutManager = layoutManager;

        recyclerView.setLayoutManager(rvLiLayoutManager);

        FoodAdapter adapter = new FoodAdapter(this,foodsList,cost);

        recyclerView.setAdapter(adapter);

    }
}
