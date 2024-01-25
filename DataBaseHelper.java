package com.example.todolist.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.todolist.Model.Model;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    private SQLiteDatabase db;

    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="TODO_DATABASE";
    private static final String TABLE_NAME="TODO_TABLE";
    private static final String COL_ID="ID";
    private static final String COL_TASK="TASK";
    private static final String COL_STAT="STATUS";

    private static final String CREATE_TABLE="CREATE TABLE " + TABLE_NAME +
            " ( " + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , "
            + COL_TASK + " TEXT , "
            + COL_STAT + " INTEGER);";

    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME );
        onCreate(db);
    }

    public void insertTask(Model model){
        db=this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_TASK, model.getTask());
        values.put(COL_STAT, 0);
        db.insert(TABLE_NAME,null, values );

    }

    public void updateTask(int id, String task){
        db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COL_TASK,task);
        db.update(TABLE_NAME, values, "ID=?", new String[]{String.valueOf(id)});

    }

    public void updateStatus(int id , int status){
        db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COL_STAT, status);
        db.update(TABLE_NAME,values, "ID=?", new String[]{String.valueOf(id)});

    }

    public void deleteTask(int id){
        db=this.getWritableDatabase();
        db.delete(TABLE_NAME, "ID=?", new String[]{String.valueOf(id)});

    }

    public List<Model> getAllTasks(){
        Model task=null;
                db=this.getReadableDatabase();
        Cursor cursor = null;
        List<Model> modelList= new ArrayList<>();
        String[] columns={COL_ID,COL_TASK, COL_STAT};


        db.beginTransaction();
        try {
            cursor= db.query(TABLE_NAME, columns, null, null, null, null, null);
            if(cursor!=null){
                if(cursor.moveToFirst()){
                    do{
                        task= new Model(cursor.getInt(0),cursor.getString(1),cursor.getInt(2));

                        modelList.add(task);
                    }while (cursor.moveToNext());

                }

            }
        }finally {
            db.endTransaction();
            cursor.close();
        }
    return modelList;
    }

}
