package Database;



import java.util.ArrayList;
import java.util.List;

import android.R.integer;
import android.R.string;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.View;

import com.argha_rifat.thecanteen.user_information;
import com.argha_rifat.thecanteen.food_info;

public class DataBase extends SQLiteOpenHelper{



    public DataBase(Context context) {
        super( context,"Food_Database",null,2);
        // TODO Auto-generated constructor stub
    }





    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        Log.v("db created", "yes");



        String sql= "CREATE TABLE IF NOT EXISTS   cart_menu (ID INTEGER PRIMARY KEY  ," +
                "name TEXT"+
                ", price TEXT)";
        db.execSQL(sql);



    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }



    public void add(food_info food_info)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "INSERT INTO cart_menu (name,price)" +
                "VALUES('"+food_info.getName()+"','"+food_info.getPrice()+"')";


        db.execSQL(query);



        db.close();

    }


    public ArrayList<food_info> getAllContact()
    {
        ArrayList<food_info> mycontactList=new ArrayList<food_info>();

        String selectquery="SELECT * FROM cart_menu";

        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=db.rawQuery(selectquery, null);

        if(cursor.moveToFirst())
        {
            do
            {
                food_info contact= new food_info(cursor.getString(0),cursor.getString(1),cursor.getString(2));
                mycontactList.add(contact);
            }while(cursor.moveToNext());
        }

        return mycontactList;
    }








    public void deletefood(int id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        String query = "DELETE from cart_menu WHERE ID="+ id;
        db.execSQL(query);


        db.close();
    }
    public void delete()
    {
        SQLiteDatabase db=this.getReadableDatabase();
        SQLiteDatabase db1=this.getWritableDatabase();
        //String query = "DELETE  from CONTACT ";
        //db.execSQL(query);
        String selectquery="SELECT * FROM cart_menu";

        //SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=db.rawQuery(selectquery, null);

        if(cursor.moveToFirst())
        {
            do
            {
                int id=cursor.getInt(0);
                String query = "DELETE from cart_menu where ID= "+id;
                db1.execSQL(query);

            }while(cursor.moveToNext());
        }



        db1.close();
    }

}
