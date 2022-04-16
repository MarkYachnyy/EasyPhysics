package ru.myitschool.vsu2021.markyachnyj.the_project.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import ru.myitschool.vsu2021.markyachnyj.the_project.logic.Grade;
import ru.myitschool.vsu2021.markyachnyj.the_project.logic.Topic;

public class TopicDB {
    /*TABLE INFO*/
    private static final String DATABASE_NAME = "The_Database.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "topic";

    private static final String COLUMN_GRADE_NUMBER = "grade_number";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_TEST_PROGRESS = "test_progress";

    private static final int NUM_COLUMN_GRADE_NUMBER = 0;
    private static final int NUM_COLUMN_NAME = 0;
    private static final int NUM_COLUMN_TEST_PROGRESS = 0;
    /*TABLE INFO*/

    private SQLiteDatabase database;

    public TopicDB(Context context){
        database = (new TopicDB.OpenHelper(context)).getWritableDatabase();
    }

    public int insert(Topic topic, int grade_number){
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_GRADE_NUMBER, grade_number);
        cv.put(COLUMN_NAME, topic.getName());
        cv.put(COLUMN_TEST_PROGRESS, topic.getProgress());
        return (int)database.insert(TABLE_NAME,null,cv);
    }

    public int update(Topic topic){
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, topic.getName());
        cv.put(COLUMN_TEST_PROGRESS, topic.getProgress());
        return database.update(TABLE_NAME,cv,COLUMN_NAME+" = ?",new String[]{String.valueOf(topic.getName())});
    }

    public ArrayList<Topic> getAll(int grade_number){
        Cursor cursor = database.query(TABLE_NAME,null,null,null,null,null,null);
        cursor.moveToFirst();
        ArrayList<Topic> result = new ArrayList<>();
        if(!cursor.isAfterLast()){
            do{
                int grade_number1 = cursor.getInt(NUM_COLUMN_GRADE_NUMBER);
                if(grade_number1==grade_number){
                    String name = cursor.getString(NUM_COLUMN_NAME);
                    float topic_count = (float) cursor.getDouble(NUM_COLUMN_TEST_PROGRESS);
                    result.add(new Topic(name, topic_count));
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        return result;
    }

    public void disintegrate(){
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public void deleteAll(){
        database.delete(TABLE_NAME, null, null);
    }

    private class OpenHelper extends SQLiteOpenHelper {

        OpenHelper(Context context){
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String query = "CREATE TABLE "+TABLE_NAME+" ("+
                    COLUMN_GRADE_NUMBER+" INTEGER, " +
                    COLUMN_NAME+" TEXT UNIQUE, "+
                    COLUMN_TEST_PROGRESS+ "REAL INTEGER)";
            db.execSQL(query);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }
}
