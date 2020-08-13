package com.argha_rifat.thecanteen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import Database.DatabaseHelper;

public class BreakfastMenuActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView cost;

    ArrayList<ModelFood> foodsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breakfast_menu);

        cost=(TextView) findViewById(R.id.cost_show);
        recyclerView = findViewById(R.id.recycler_view);

        foodsList = new ArrayList<>();
        final DatabaseHelper db= new DatabaseHelper(this);
        final String datbasename="food";
       // ArrayList<food_info> myContactList = db.getAllContact(datbasename);

        foodsList.add(new ModelFood(R.drawable.dal_vaji,"Dal Vaji","Aust Canteen","100"));
        foodsList.add(new ModelFood(R.drawable.paratha,"Paratha","Aust Canteen","80"));
        foodsList.add(new ModelFood(R.drawable.egg_rool,"Egg Roll","Aust Canteen","90"));
        foodsList.add(new ModelFood(R.drawable.egg,"Egg","Aust Canteen","70"));
        foodsList.add(new ModelFood(R.drawable.egg_chop,"Egg Chop","Aust Canteen","80"));
        /*for(food_info myContact : myContactList)
        {
            int image=myContact.item_image;
            foodsList.add(new ModelFood(R.drawable.image,myContact.getName(),myContact.getPlace(),myContact.getPrice()));
        }*/


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        RecyclerView.LayoutManager rvLiLayoutManager = layoutManager;

        recyclerView.setLayoutManager(rvLiLayoutManager);

        FoodAdapter adapter = new FoodAdapter(this,foodsList,cost);

        recyclerView.setAdapter(adapter);

    }
}
