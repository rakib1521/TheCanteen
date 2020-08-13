package com.argha_rifat.thecanteen;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


import java.util.ArrayList;

import Database.DataBase;


public class CartMenuActivity extends AppCompatActivity {

  public static ArrayList<food_info> food_infoArrayList=new ArrayList<food_info>();
  public static int price;

    DataBase db=new DataBase(this);
    TextView display,cost;
    Button delete,confirm;
    EditText ID;
    int show_price;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_menu);
        display=(TextView)findViewById(R.id.display);
        delete=(Button)findViewById(R.id.delete);

        ID=(EditText)findViewById(R.id.E_id);
        confirm=(Button)findViewById(R.id.confirm);
        cost=(TextView)findViewById(R.id.cost);
        cost.setText((Integer.toString(price)));

        disply();




        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String E_ID = ID.getText().toString();

                db.deletefood(Integer.parseInt(E_ID));
                display.setText("");
                ID.setText("");
                disply();

            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(show_price>0)
                {
                    Random rand = new Random();
                    int myRandomNumber = rand.nextInt(0x10) + 0x10;
                    String hex = Integer.toHexString(myRandomNumber);
                    new AlertDialog.Builder(CartMenuActivity.this).setTitle("Do you want to confirm this order")
                            .setMessage("Please pay  " +show_price+" TK  In the Counter & your order number is " +hex)
                            .setNegativeButton("No",null)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    db.delete();
                                    display.setText("");
                                    ID.setText("");
                                    disply();
                                }
                            }).create().show();
                }
                else
                {
                    Toast.makeText(CartMenuActivity.this,"Cart is Empty", Toast.LENGTH_SHORT).show();

                }



            }
        });


    }
    public void disply()
    {
        int size=food_infoArrayList.size();
        String result=null;

        for(int i=0;i<size;i++)
        {
            result =" Name: "+food_infoArrayList.get(i).getName()+" Price: "+ food_infoArrayList.get(i).getPrice();
            result +="\n";


            db.add(new food_info(food_infoArrayList.get(i).getName(),food_infoArrayList.get(i).getPrice()));
            Log.d("Result",result);
        }

        food_infoArrayList.clear();
        ArrayList<food_info> myContactList=db.getAllContact();
        result = "";
        int d_price=0;
        for(food_info myContact : myContactList)
        {
            result +="ID: "+myContact.getID()+" Name: "+myContact.getPrice()+" price.: "+ myContact.getName();
            result +="\n";
            String g_price=myContact.getName();
            d_price+=Integer.parseInt(g_price);
            Log.d("Result",result);
        }
        if(myContactList.size()  == 0)
        {
            result = "No food to display";
        }
        display.setText(result);
        show_price=d_price;

        cost.setText(Integer.toString(d_price));
    }







}
