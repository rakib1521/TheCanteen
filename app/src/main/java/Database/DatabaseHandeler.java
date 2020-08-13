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

import com.argha_rifat.thecanteen.user_information;
import com.argha_rifat.thecanteen.food_info;

public class DatabaseHandeler extends SQLiteOpenHelper{



    public DatabaseHandeler(Context context) {
        super(context,"user_info",null,1);
        // TODO Auto-generated constructor stub
    }




    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        //Log.v("db created", "yes");


        String sql= "CREATE TABLE IF NOT EXISTS user_info (name TEXT PRIMARY KEY," +
                "semester TEXT"+
                ", year TEXT"+
                ", mobile TEXT"+
                ", password TEXT)";
        db.execSQL(sql);


        db.execSQL(sql);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public void addContact(user_information user_information)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "INSERT INTO user_info(name,semester,year,mobile,password)" +
                "VALUES('"+user_information.getName()+"','"+user_information.getSemester()+"','"+user_information.getYear()+"','"+user_information.getMobile_number()+"','"+user_information.getPassword()+"')";


        db.execSQL(query);



        db.close();

    }
    public void addCart(food_info food_info)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "INSERT INTO cart_menu(name,price)" +
                "VALUES('"+food_info.getName()+"','"+food_info.getPrice()+"')";


        db.execSQL(query);



        db.close();

    }

    public user_information getSingleContact(String name)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] Gname = new String[]{ name };

        String query = "SELECT name,semester,year,mobile,password FROM user_info WHERE name = ?" ;
        Cursor cursor = db.rawQuery(query, Gname);
        user_information myinfo = null;
        if(cursor.moveToFirst())
        {

            myinfo=new user_information(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));
        }

        return myinfo;
    }

    public List<user_information> getAllContact()
    {
        List<user_information> myinfo=new ArrayList<user_information>();

        String selectquery="SELECT * FROM user_info";

        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=db.rawQuery(selectquery, null);

        if(cursor.moveToFirst())
        {
            do
            {
                user_information user_information=new user_information(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));
                myinfo.add(user_information);
            }while(cursor.moveToNext());
        }

        return myinfo;
    }




    public void deleteContact(String name)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        String query = "DELETE from user_info WHERE ID="+ name;
        db.execSQL(query);


        db.close();
    }
    public void deletefood(int id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        String query = "DELETE from cart WHERE ID="+ id;
        db.execSQL(query);


        db.close();
    }
    public void delete()
    {
        SQLiteDatabase db=this.getReadableDatabase();
        SQLiteDatabase db1=this.getWritableDatabase();
        //String query = "DELETE  from CONTACT ";
        //db.execSQL(query);
        String selectquery="SELECT * FROM cart";

        //SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=db.rawQuery(selectquery, null);

        if(cursor.moveToFirst())
        {
            do
            {
                int id=cursor.getInt(0);
                String query = "DELETE from cart where ID= "+id;
                db1.execSQL(query);

            }while(cursor.moveToNext());
        }


        db1.close();
    }

}
