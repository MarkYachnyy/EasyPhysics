package ru.myitschool.vsu2021.markyachnyj.the_project.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import ru.myitschool.vsu2021.markyachnyj.the_project.logic.Grade;
import ru.myitschool.vsu2021.markyachnyj.the_project.logic.Topic;

public class DatabaseManager {
    /*DATABASE_INFO*/
    private static final String DATABASE_NAME = "The_Database.db";
    private static final int DATABASE_VERSION = 1;
    /*DATABASE_INFO*/

    /*GRADE TABLE INFO*/
    private static final String GRADE_TABLE_NAME = "grades";

    private static final String COLUMN_GRADE_NUMBER = "number";
    private static final String COLUMN_GRADE_TOPIC_COMPLETED = "topic_completed";
    private static final String COLUMN_GRADE_TOPIC_COUNT = "topic_count";

    private static final int NUM_COLUMN_GRADE_NUMBER = 0;
    private static final int NUM_COLUMN_GRADE_TOPIC_COMPLETED = 1;
    private static final int NUM_COLUMN_GRADE_TOPIC_COUNT = 2;
    /*GRADE TABLE INFO*/

    /*TABLE INFO*/
    private static final String TOPIC_TABLE_NAME = "topics";

    private static final String COLUMN_TOPIC_GRADE_NUMBER = "grade_number";
    private static final String COLUMN_TOPIC_NAME = "name";
    private static final String COLUMN_TOPIC_TEST_PROGRESS = "test_progress";

    private static final int NUM_COLUMN_TOPIC_GRADE_NUMBER = 0;
    private static final int NUM_COLUMN_TOPIC_NAME = 1;
    private static final int NUM_COLUMN_TOPIC_TEST_PROGRESS = 2;
    /*TABLE INFO*/

    private SQLiteDatabase database;

    public DatabaseManager(Context context){
        database = (new OpenHelper(context)).getWritableDatabase();
    }

    public int insertGrade(Grade grade){
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_GRADE_NUMBER, grade.getNumber());
        cv.put(COLUMN_GRADE_TOPIC_COMPLETED, grade.getTopic_completed());
        cv.put(COLUMN_GRADE_TOPIC_COUNT, grade.getTopic_count());
        return (int)database.insert(GRADE_TABLE_NAME,null,cv);
    }

    public int updateGrade(Grade grade){
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_GRADE_NUMBER, grade.getNumber());
        cv.put(COLUMN_GRADE_TOPIC_COMPLETED, grade.getTopic_completed());
        cv.put(COLUMN_GRADE_TOPIC_COUNT, grade.getTopic_count());
        return database.update(GRADE_TABLE_NAME,cv, COLUMN_GRADE_NUMBER +" = ?",new String[]{String.valueOf(grade.getNumber())});
    }

    public ArrayList<Grade> getAllGrades(){
        Cursor cursor = database.query(GRADE_TABLE_NAME,null,null,null,null,null,null);
        cursor.moveToFirst();
        ArrayList<Grade> result = new ArrayList<>();
        if(!cursor.isAfterLast()){
            do{
                int number = cursor.getInt(NUM_COLUMN_GRADE_NUMBER);
                int topic_completed = cursor.getInt(NUM_COLUMN_GRADE_TOPIC_COMPLETED);
                int topic_count = cursor.getInt(NUM_COLUMN_GRADE_TOPIC_COUNT);
                result.add(new Grade(number, topic_completed, topic_count));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return result;
    }

    public boolean containsGrade(int grade_number){
        ArrayList<Grade> list = getAllGrades();
        for(Grade grade:list){
            if(grade.getNumber()==grade_number){
                return true;
            }
        }
        return false;
    }

    /*public void disintegrate(){
        database.execSQL("DROP TABLE IF EXISTS " + GRADE_TABLE_NAME);
    }*/

    public void deleteAllGrades(){
        database.delete(GRADE_TABLE_NAME, null, null);
    }

    public int insertTopic(Topic topic, int grade_number){
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TOPIC_GRADE_NUMBER, grade_number);
        cv.put(COLUMN_TOPIC_NAME, topic.getName());
        cv.put(COLUMN_TOPIC_TEST_PROGRESS, topic.getProgress());
        return (int)database.insert(TOPIC_TABLE_NAME,null,cv);
    }

    public int updateTopic(Topic topic){
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TOPIC_NAME, topic.getName());
        cv.put(COLUMN_TOPIC_TEST_PROGRESS, topic.getProgress());
        return database.update(TOPIC_TABLE_NAME,cv, COLUMN_TOPIC_NAME +" = ?",new String[]{String.valueOf(topic.getName())});
    }

    public boolean containsTopic(String topic_name){
        ArrayList<Topic> list = getAllTopics();
        for(Topic topic:list){
            if(topic.getName().equals(topic_name)){
                return true;
            }
        }
        return false;
    }

    public ArrayList<Topic> getAllTopics(int grade_number){
        Cursor cursor = database.query(TOPIC_TABLE_NAME,null,null,null,null,null,null);
        cursor.moveToFirst();
        ArrayList<Topic> result = new ArrayList<>();
        if(!cursor.isAfterLast()){
            do{
                int grade_number1 = cursor.getInt(NUM_COLUMN_TOPIC_GRADE_NUMBER);
                if(grade_number1==grade_number){
                    String name = cursor.getString(NUM_COLUMN_TOPIC_NAME);
                    float test_progress = (float) cursor.getDouble(NUM_COLUMN_TOPIC_TEST_PROGRESS);
                    result.add(new Topic(name, test_progress));
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        return result;
    }

    private ArrayList<Topic> getAllTopics(){
        Cursor cursor = database.query(TOPIC_TABLE_NAME,null,null,null,null,null,null);
        cursor.moveToFirst();
        ArrayList<Topic> result = new ArrayList<>();
        if(!cursor.isAfterLast()){
            do{
                String name = cursor.getString(NUM_COLUMN_TOPIC_NAME);
                float test_progress = (float) cursor.getDouble(NUM_COLUMN_TOPIC_TEST_PROGRESS);
                result.add(new Topic(name, test_progress));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return result;
    }

    public void deleteAllTopics(){
        database.delete(TOPIC_TABLE_NAME, null, null);
    }

    private class OpenHelper extends SQLiteOpenHelper{

        OpenHelper(Context context){
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String query = "CREATE TABLE "+ GRADE_TABLE_NAME +" ("+
                    COLUMN_GRADE_NUMBER +" INTEGER UNIQUE, " +
                    COLUMN_GRADE_TOPIC_COMPLETED +" INTEGER, "+
                    COLUMN_GRADE_TOPIC_COUNT + " INTEGER)";
            db.execSQL(query);

            String query1 = "CREATE TABLE "+ TOPIC_TABLE_NAME +" ("+
                    COLUMN_TOPIC_GRADE_NUMBER +" INTEGER, " +
                    COLUMN_TOPIC_NAME +" TEXT UNIQUE, "+
                    COLUMN_TOPIC_TEST_PROGRESS + " REAL)";
            db.execSQL(query1);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + GRADE_TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS "+ TOPIC_TABLE_NAME);
            onCreate(db);
        }
    }
}
