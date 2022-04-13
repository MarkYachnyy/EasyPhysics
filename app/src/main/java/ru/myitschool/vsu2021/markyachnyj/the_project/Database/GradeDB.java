package ru.myitschool.vsu2021.markyachnyj.the_project.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Collection;

import ru.myitschool.vsu2021.markyachnyj.the_project.logic.Grade;

public class GradeDB {
    /*TABLE INFO*/
    private static final String DATABASE_NAME = "the_database.db";
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

    public int insertNewGrade(Grade grade){
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NUMBER, grade.getNumber());
        cv.put(COLUMN_TOPIC_COMPLETED, grade.getTopic_completed());
        cv.put(COLUMN_TOPIC_COUNT, grade.getTopic_count());
        return (int)database.insert(TABLE_NAME,null,cv);
    }

    public int updateGrade(Grade grade){
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NUMBER, grade.getNumber());
        cv.put(COLUMN_TOPIC_COMPLETED, grade.getTopic_completed());
        cv.put(COLUMN_TOPIC_COUNT, grade.getTopic_count());
        return database.update(TABLE_NAME,cv,COLUMN_NUMBER+" = ?",new String[]{String.valueOf(grade.getNumber())});
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
