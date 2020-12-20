package saeeshkadge.com.moneymanager.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

//import saeeshkadge.com.moneymanager.model.ExpenseType;
import saeeshkadge.com.moneymanager.model.Saving;
import saeeshkadge.com.moneymanager.table.SavingTable;
//import saeeshkadge.com.moneymanager.table.ExpenseTypeTable;

import static saeeshkadge.com.moneymanager.utils.DateUtil.getCurrentWeeksDates;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class SavingDatabaseHelper extends SQLiteOpenHelper {
    public static final String SAVING_DB = "saving";

    public SavingDatabaseHelper(Context context) {
        super(context, SAVING_DB, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SavingTable.CREATE_TABLE_QUERY);
        //sqLiteDatabase.execSQL(ExpenseTypeTable.CREATE_TABLE_QUERY);
        //seedExpenseTypes(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }

   /* public List<String> getExpenseTypes() {
        ArrayList<String> expenseTypes = new ArrayList<>();

        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(ExpenseTypeTable.SELECT_ALL, null);

        if(isCursorPopulated(cursor)){
            do {
                String type = cursor.getString(cursor.getColumnIndex(ExpenseTypeTable.TYPE));
                expenseTypes.add(type);
            } while(cursor.moveToNext());
        }

        return expenseTypes;
    }*/

    public void deleteAll() {
        SQLiteDatabase database = this.getWritableDatabase();
        //database.delete(ExpenseTypeTable.TABLE_NAME, "", new String[]{});
        database.delete(SavingTable.TABLE_NAME, "", new String[]{});
        database.close();
    }

    public void addSaving(Saving saving) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SavingTable.AMOUNT, saving.getAmount());
        //values.put(SavingTable.TYPE, saving.getType());
        values.put(SavingTable.DATE, saving.getDate());

        database.insert(SavingTable.TABLE_NAME, null, values);
    }

    public List<Saving> getSaving() {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(SavingTable.SELECT_ALL, null);

        return buildSaving(cursor);
    }

    /*public List<Saving> getTotalSavings() {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(SavingTable.getConsolidatedSavingsForDates(getCurrentWeeksDates()), null);
        return buildSaving(cursor);
    }*/

    public List<Saving> getCurrentWeeksSaving() {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(SavingTable.getConsolidatedSavingsForDates(getCurrentWeeksDates()), null);
        return buildSaving(cursor);
    }

    public List<Saving> getSavingsGroupByCategory() {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(SavingTable.SELECT_ALL_GROUP_BY_CATEGORY, null);
        return buildSaving(cursor);
    }


    /*public void addExpenseType(ExpenseType type) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SavingTable.TYPE, type.getType());

        database.insert(ExpenseTypeTable.TABLE_NAME, null, values);
    }*/

    public void truncate(String tableName) {
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("delete from " + tableName);
    }

    private List<Saving> buildSaving(Cursor cursor) {
        List<Saving> savings = new ArrayList<>();
        if(isCursorPopulated(cursor)){
            do {
                //String type = cursor.getString(cursor.getColumnIndex(SavingTable.TYPE));
                String amount = cursor.getString(cursor.getColumnIndex(SavingTable.AMOUNT));
                String date = cursor.getString(cursor.getColumnIndex(SavingTable.DATE));
                String id = cursor.getString(cursor.getColumnIndex(SavingTable._ID));

                Saving saving = id == null ? new Saving(parseLong(amount), date) : new Saving(parseInt(id), parseLong(amount), date);
                savings.add(saving);
            } while(cursor.moveToNext());
        }

        return savings;
    }

    private boolean isCursorPopulated(Cursor cursor) {
        return cursor != null && cursor.moveToFirst();
    }

    /*private void seedExpenseTypes(SQLiteDatabase sqLiteDatabase) {
        List<ExpenseType> expenseTypes = ExpenseTypeTable.seedData();
        for (ExpenseType expenseType : expenseTypes) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(ExpenseTypeTable.TYPE, expenseType.getType());

            sqLiteDatabase.insert(ExpenseTypeTable.TABLE_NAME, null, contentValues);
        }
    }*/
}
