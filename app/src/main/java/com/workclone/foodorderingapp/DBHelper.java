package com.workclone.foodorderingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.workclone.foodorderingapp.models.OrderModel;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "myDataBaseFood.db";
    public static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "orders";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    "id" + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name" + " TEXT," +
                    "phone TEXT , " +
                    "price int , " +
                    "image int , " +
                    "description TEXT , " +
                    "quantity int , " +
                    "foodName TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
          sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
          sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);
          onCreate(sqLiteDatabase);
    }

    public boolean insertOrder(String name , String phone , int price , int image , String description , int quantity , String foodName)
    {
           SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name" , name);
        contentValues.put("price" ,price);
        contentValues.put("phone" , phone);
        contentValues.put("image" , image);
        contentValues.put("description" , description);
        contentValues.put("quantity" , quantity);
        contentValues.put("foodName" , foodName);
        final long id = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        if (id<=0){
            return false;
        }
        else
            return true;
    }
    public boolean updateOrder(String name , String phone , int price , int image , String description , int quantity , String foodName , int id)
    {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id" , id);
        contentValues.put("name" , name);
        contentValues.put("price" ,price);
        contentValues.put("phone" , phone);
        contentValues.put("image" , image);
        contentValues.put("description" , description);
        contentValues.put("quantity" , quantity);
        contentValues.put("foodName" , foodName);
        final long updated = sqLiteDatabase.update(TABLE_NAME, contentValues , "id = "+id , null);
        if (updated<=0){
            return false;
        }
        else
            return true;
    }

    public int deleteOrder(int id){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME , "id = "+id ,null);
    }

    public ArrayList<OrderModel> getOrders(){
        ArrayList<OrderModel> orderModelArrayList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String query = "Select image , foodName , price , id from "+TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if(cursor.moveToFirst()){
            while (cursor.moveToNext()){
                OrderModel orderModel = new OrderModel();
                orderModel.setOrderPic(cursor.getInt(0));
                orderModel.setOrderName(cursor.getString(1));
                orderModel.setOrderPrice(String.format("%d" , cursor.getInt(2)));
                orderModel.setOrderNumber(String.format("%d" , cursor.getInt(3)));
                orderModelArrayList.add(orderModel);
            }
        }
        cursor.close();
        sqLiteDatabase.close();
        return orderModelArrayList;
    }

    public Cursor getOrderById(int id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        //String query = "Select * From orders where id = "+id;
        Cursor cursor = sqLiteDatabase.rawQuery("Select * From orders where id = "+id, null);
    if(cursor!=null){
        cursor.moveToFirst();
    }
    return cursor;
    }

}
