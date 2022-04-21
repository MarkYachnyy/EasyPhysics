package ru.myitschool.vsu2021.markyachnyj.the_project.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Collection;

import ru.myitschool.vsu2021.markyachnyj.the_project.logic.Grade;

public class GradeDB {
    /*TABLE INFO*/
    private static final String DATABASE_NAME = "The_Database.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "grades";

    private static final String COLUMN_NUMBER = "number";
    private static final String COLUMN_TOPIC_COMPLETED = "topic_completed";
    private static final String COLUMN_TOPIC_COUNT = "topic_count";

    private static final int NUM_COLUMN_NUMBER = 0;
    private static final int NUM_COLUMN_TOPIC_COMPLETED = 1;
    private static final int NUM_COLUMN_TOPIC_COUNT = 2;
    /*TABLE INFO*/

    private SQLiteDatabase database;

    public GradeDB(Context context){
        database = (new OpenHelper(context)).getWritableDatabase();
    }

    public int insert(Grade grade){
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NUMBER, grade.getNumber());
        cv.put(COLUMN_TOPIC_COMPLETED, grade.getTopic_completed());
        cv.put(COLUMN_TOPIC_COUNT, grade.getTopic_count());
        return (int)database.insert(TABLE_NAME,null,cv);
    }

    public int update(Grade grade){
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NUMBER, grade.getNumber());
        cv.put(COLUMN_TOPIC_COMPLETED, grade.getTopic_completed());
        cv.put(COLUMN_TOPIC_COUNT, grade.getTopic_count());
        return database.update(TABLE_NAME,cv,COLUMN_NUMBER+" = ?",new String[]{String.valueOf(grade.getNumber())});
    }

    public ArrayList<Grade> getAll(){
        Cursor cursor = database.query(TABLE_NAME,null,null,null,null,null,null);
        cursor.moveToFirst();
        ArrayList<Grade> result = new ArrayList<>();
        if(!cursor.isAfterLast()){
            do{
                int number = cursor.getInt(NUM_COLUMN_NUMBER);
                int topic_completed = cursor.getInt(NUM_COLUMN_TOPIC_COMPLETED);
                int topic_count = cursor.getInt(NUM_COLUMN_TOPIC_COUNT);
                result.add(new Grade(number, topic_completed, topic_count));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return result;
    }

    public boolean containsGrade(int grade_number){
        ArrayList<Grade> list = getAll();
        for(Grade grade:list){
            if(grade.getNumber()==grade_number){
                return true;
            }
        }
        return false;
    }

    public void disintegrate(){
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public void deleteAll(){
        database.delete(TABLE_NAME, null, null);
    }

    private class OpenHelper extends SQLiteOpenHelper{

        OpenHelper(Context context){
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String query = "CREATE TABLE "+TABLE_NAME+" ("+
                    COLUMN_NUMBER+" INTEGER UNIQUE, " +
                    COLUMN_TOPIC_COMPLETED+" INTEGER, "+
                    COLUMN_TOPIC_COUNT+ " INTEGER)";
            db.execSQL(query);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }
}
