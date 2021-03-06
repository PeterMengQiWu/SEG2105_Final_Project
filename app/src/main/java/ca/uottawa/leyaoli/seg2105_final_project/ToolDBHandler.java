package ca.uottawa.leyaoli.seg2105_final_project;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tyson on 2017-11-30.
 */

public class ToolDBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "tool.db";
    private static final String TABLE_TOOL = "tools";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_ToolType = "tool_type";
    public static final String COLUMN_ToolName = "tool_name";
    public static final String COLUMN_ToolUse = "tool_use";
    public static final String COLUMN_States = "states";
    public ToolDBHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " +
                TABLE_TOOL + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_ToolType + " TEXT,"
                + COLUMN_ToolName + " TEXT,"
                + COLUMN_ToolUse + " TEXT,"
                + COLUMN_States + " TEXT"+ ")";
        db.execSQL(CREATE_PRODUCTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TOOL);
        onCreate(db);
    }

    public void addTool(Shopping shopping){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ToolName,shopping.getName());
        values.put(COLUMN_ToolType,shopping.getType());
        values.put(COLUMN_ToolUse,shopping.getIsUsed());
        values.put(COLUMN_States,shopping.isSelected() );
        db.insert(TABLE_TOOL,null,values);
        db.close();
    }

    public void updateUse (String use, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_TOOL + " SET " + COLUMN_ToolUse + " = \"" + use + "\" WHERE " + COLUMN_ToolName + " = \"" + name + "\"";
        db.execSQL(query);
    }

    public void updateStates (String states, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_TOOL + " SET " + COLUMN_States + " = \"" + states + "\" WHERE " + COLUMN_ToolName + " = \"" + name + "\"";
        db.execSQL(query);
    }

    public List<Shopping> FindShoppingList (String type, String states){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * FROM " + TABLE_TOOL + " WHERE " +
                COLUMN_ToolType + " = \""+ type + "\" AND " +
                COLUMN_States + " = \""+ states + "\"";
        Cursor cursor = db.rawQuery(query,null);
        List<Shopping> tools = new ArrayList<Shopping>();
        Shopping tool;
        if (cursor.moveToFirst()) {
            do {
                tool = new Shopping();
                tool.setName(cursor.getString(2));
                tool.setType(cursor.getString(1));
                tool.setIsUsed(cursor.getString(3));
                tool.setSelected(cursor.getString(4));
                tools.add(tool);
            } while (cursor.moveToNext());
        }
        db.close();
        return tools;
    }

    public boolean deleteTools (String toolName){
        boolean result = false;
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * FROM " + TABLE_TOOL + " WHERE " +
                COLUMN_ToolName + " = \"" + toolName + "\"";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
                String id = cursor.getString(0);
                db.delete(TABLE_TOOL, COLUMN_ID + " = " + id, null);
                cursor.close();
                result = true;
        }
        db.close();
        return result;
    }
}
