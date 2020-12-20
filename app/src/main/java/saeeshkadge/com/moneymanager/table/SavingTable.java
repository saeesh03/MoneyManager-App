package saeeshkadge.com.moneymanager.table;

import android.provider.BaseColumns;

import java.util.ArrayList;

public class SavingTable implements BaseColumns {
    public static final String TABLE_NAME = "savings";
    public static final String AMOUNT = "amount";
    //public static final String TYPE = "type";
    public static final String DATE = "date";

    public static final String CREATE_TABLE_QUERY = "create table " + TABLE_NAME + " (" +
            _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            AMOUNT + " INTEGER," +
            //TYPE + " TEXT, " +
            DATE + " TEXT )";

    public static final String SELECT_ALL = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + _ID + " DESC";

    public static final String SELECT_ALL_GROUP_BY_CATEGORY = "SELECT " + _ID + ", date, sum(amount) as amount FROM "; //+ TABLE_NAME + " GROUP BY type";

    public static String getSavingForDate(String date) {
        return "SELECT * FROM " + TABLE_NAME + " WHERE date like '" + date + "%' ORDER BY " + _ID + " DESC";
    }

    public static String getConsolidatedSavingsForDates(ArrayList<String> dates) {
        String dateLike = "";
        for (String date : dates) {
            dateLike += "date like '" + date + "%' " + (dates.get(dates.size() - 1) == date ? "" : "or ");
        }

        return "SELECT " + _ID + ", date, sum(amount) as amount FROM " + TABLE_NAME + " WHERE " + dateLike + " GROUP BY date";
    }
}
