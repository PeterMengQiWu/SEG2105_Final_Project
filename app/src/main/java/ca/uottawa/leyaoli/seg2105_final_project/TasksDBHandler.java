package ca.uottawa.leyaoli.seg2105_final_project;

/**
 * Created by Kevin-Lee on 2017/11/27.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class TasksDBHandler extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "tasks.db";
    private static final String TABLE_TASKS = "tasks";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "taskname";
    public static final String COLUMN_POINTS = "points";
    public static final String COLUMN_dueDate = "due_date";
    public static final String COLUMN_dueTime = "due_time";
    public static final String COLUMN_Creator = "creator";
    public static final String COLUMN_Worker = "worker";
    public static final String COLUMN_States = "states";

    public TasksDBHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " +
                TABLE_TASKS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_POINTS + " REAL,"
                + COLUMN_dueDate + " TEXT,"
                + COLUMN_dueTime + " TEXT,"
                + COLUMN_Creator + " TEXT,"
                + COLUMN_Worker + " TEXT,"
                + COLUMN_States + " TAXK" + ")";
        db.execSQL(CREATE_PRODUCTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASKS);
        onCreate(db);
    }

    public void addTask (Task task){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, task.getName());
        values.put(COLUMN_POINTS, task.getPoints());
        values.put(COLUMN_dueDate, task.getDueDate());
        values.put(COLUMN_dueTime, task.getDueTime());
        values.put(COLUMN_Creator, task.getCreator());
        values.put(COLUMN_Worker, task.getWorker());
        values.put(COLUMN_States, task.getStates());
        db.insert(TABLE_TASKS, null, values);
        db.close();
    }

    public Task findTaskByName(String name){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * FROM " + TABLE_TASKS + " WHERE " +
                COLUMN_NAME + " = \""+ name + "\"";
        Cursor cursor = db.rawQuery(query,null);
        Task task = new Task();
        if (cursor.moveToFirst()){
            task.setName(cursor.getString(1));
            task.setPoints(Double.parseDouble(cursor.getString(2)));
            task.setDueDate(cursor.getString(3));
            task.setDueTime(cursor.getString(4));
            task.setCreator(cursor.getString(5));
            if (cursor.getString(6)!=null)
                task.setWorker(cursor.getString(6));
            task.setStates(cursor.getString(7));
        } else {
            task = null;
        }
        db.close();
        return task;
    }

    public Task findTaskByCreator(String creator){
       SQLiteDatabase db = this.getReadableDatabase();
       String query = "Select * FROM " + TABLE_TASKS + " WHERE " +
               COLUMN_Creator + " = \""+ creator + "\"";
       Cursor cursor = db.rawQuery(query,null);
       Task task = new Task();
       if (cursor.moveToFirst()){
           task.setName(cursor.getString(1));
           task.setPoints(Double.parseDouble(cursor.getString(2)));
           task.setDueDate(cursor.getString(3));
           task.setDueTime(cursor.getString(4));
           task.setCreator(cursor.getString(5));
           if (cursor.getString(6)!=null)
               task.setWorker(cursor.getString(6));
               task.setStates(cursor.getString(7));
       } else {
           task = null;
       }
       db.close();
       return task;
    }

    public Task findTaskByWorker(String worker) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * FROM " + TABLE_TASKS + " WHERE " +
                COLUMN_Worker + " = \""+ worker + "\"";
        Cursor cursor = db.rawQuery(query,null);
        Task task = new Task();
        if (cursor.moveToFirst()){
            task.setName(cursor.getString(1));
            task.setPoints(Double.parseDouble(cursor.getString(2)));
            task.setDueDate(cursor.getString(3));
            task.setDueTime(cursor.getString(4));
            task.setCreator(cursor.getString(5));
            if (cursor.getString(6)!=null)
                task.setWorker(cursor.getString(6));
            task.setStates(cursor.getString(7));
        } else {
            task = null;
        }
        db.close();
        return task;
    }

    public boolean deleteTask (String taskName, String creator){
        boolean result = false;
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * FROM " + TABLE_TASKS + " WHERE " +
                COLUMN_NAME + " = \"" + taskName + "\"";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            if (creator.compareTo(cursor.getString(5))==0){
                String id = cursor.getString(0);
                db.delete(TABLE_TASKS, COLUMN_ID + " = " + id, null);
                cursor.close();
                result = true;
            }
        }
        db.close();
        return result;
    }

    public List<Task> getTaskList(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * FROM " + TABLE_TASKS;
        Cursor cursor = db.rawQuery(query, null);
        List<Task> taskList = new ArrayList<Task>();
        Task task;
        if (cursor.moveToFirst()){
            do{
                task = new Task();
                task.setName(cursor.getString(1));
                task.setPoints(Double.parseDouble(cursor.getString(2)));
                task.setDueDate(cursor.getString(3));
                task.setDueTime(cursor.getString(4));
                task.setCreator(cursor.getString(5));
                if (cursor.getString(6)!=null)
                    task.setWorker(cursor.getString(6));
                task.setStates(cursor.getString(7));
                taskList.add(task);
            }while(cursor.moveToNext());
        }
        db.close();
        return taskList;
    }
}

