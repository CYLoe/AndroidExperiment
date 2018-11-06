package com.compare.compare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Doraemon on 4/3/2017.
 */

public class BookDatabaseHelper extends SQLiteAssetHelper {
    //region VARIABLES
    private Context mContext;
    private static BookDatabaseHelper BookDBInstance;
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Book.db";

    private final SQLiteDatabase db;

    private final String TABLE_NAME_CATEGORIES = "Categories";
    private final String CATEGORIES_COLUMN_ID = "id";
    private final String CATEGORIES_COLUMN_CATEGORY_NAME = "category_name";
    private final String CATEGORIES_COLUMN_DEFAULT_ROWS = "default_rows";
    private final String CATEGORIES_COLUMN_IMAGE_NAME = "image_name";

    private final String TABLE_NAME_SORTS = "Sorts";
    private final String SORTS_COLUMN_ID = "id";
    private final String SORTS_COLUMN_TYPE = "type";
    private final String SORTS_COLUMN_DIRECTION = "direction";
    private final String SORTS_COLUMN_SPECIAL_LIST = "special_list";

    private final String TABLE_NAME_SHEETS = "Sheets";
    private final String SHEETS_COLUMN_ID = "id";
    private final String SHEETS_COLUMN_SHEET_NAME = "sheet_name";
    private final String SHEETS_COLUMN_CATEGORY_ID = "category_id";

    private final String TABLE_NAME_COLUMNS = "Columns";
    private final String COLUMNS_COLUMN_ID = "id";
    private final String COLUMNS_COLUMN_SHEET_ID = "sheet_id";
    private final String COLUMNS_COLUMN_COLUMN_NAME = "column_name";
    private final String COLUMNS_COLUMN_ORDER_IN_SHEET = "order_in_sheet";
    private final String COLUMNS_COLUMN_RANK_IN_SHEET = "rank_in_sheet";

    private final String TABLE_NAME_ROWS = "Rows";
    private final String ROWS_COLUMN_ID = "id";
    private final String ROWS_COLUMN_SHEET_ID = "sheet_id";
    private final String ROWS_COLUMN_ROW_NAME = "row_name";
    private final String ROWS_COLUMN_UNIT_ID = "unit_id";
    private final String ROWS_COLUMN_ORDER_IN_COLUMN = "order_in_column";
    private final String ROWS_COLUMN_SORT_ID = "sort_id";

    private final String TABLE_NAME_CELLS = "Cells";
    private final String CELLS_COLUMN_ID = "id";
    private final String CELLS_COLUMN_COLUMN_ID = "column_id";
    private final String CELLS_COLUMN_ROW_ID = "row_id";
    private final String CELLS_COLUMN_CELL_VALUE = "cell_value";
    private final String CELLS_COLUMN_RANK = "rank_in_row";

    private final String TABLE_NAME_REPORTS = "Reports";
    private final String REPORTS_COLUMN_ID = "id";
    private final String REPORTS_COLUMN_JSON_DATA = "json_data";
    private final String REPORTS_COLUMN_SHEET_NAME = "sheet_name";
    private final String REPORTS_COLUMN_CATEGORY_NAME = "category_name";
    private final String REPORTS_COLUMN_CHOSEN_COLUMN_NAME = "chosen_column_name";
    private final String REPORTS_COLUMN_TIMESTAMP = "timestamp";
    private final String REPORTS_COLUMN_SENT = "sent";

    private final String TABLE_NAME_UNITS = "Units";
    private final String UNITS_COLUMN_ID = "id";
    private final String UNITS_COLUMN_TYPE = "type";
    private final String UNITS_COLUMN_NAME = "name";
    private final String UNITS_COLUMN_SYMBOL = "symbol";

    //endregion
    //region CONSTRUCTORS
    public static synchronized BookDatabaseHelper getInstance(Context context) {
        if (BookDBInstance == null) {
            BookDBInstance = new BookDatabaseHelper(context.getApplicationContext());
        }
        return BookDBInstance;
    }
    private BookDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.db = this.getWritableDatabase();
        this.mContext = context;
    }
    //endregion
    //region OVERRIDES
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        db.execSQL("PRAGMA foreign_keys=ON");
    }
    /*@Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_CATEGORIES_TABLE = "CREATE TABLE " + TABLE_NAME_CATEGORIES + " (" +
                CATEGORIES_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                CATEGORIES_COLUMN_CATEGORY_NAME + " TEXT NOT NULL DEFAULT 'General', " +
                CATEGORIES_COLUMN_DEFAULT_ROWS + " TEXT NOT NULL DEFAULT '', " +
                CATEGORIES_COLUMN_IMAGE_NAME + " TEXT NOT NULL DEFAULT 'general');";

        final String SQL_CREATE_SORTS_TABLE = "CREATE TABLE " + TABLE_NAME_SORTS + " (" +
                SORTS_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                SORTS_COLUMN_TYPE + " TEXT NOT NULL DEFAULT 'numeric', " +
                SORTS_COLUMN_DIRECTION + " TEXT NOT NULL DEFAULT 'ascending', " +
                SORTS_COLUMN_SPECIAL_LIST + " TEXT NOT NULL DEFAULT '');";

        final String SQL_CREATE_SHEETS_TABLE = "CREATE TABLE " + TABLE_NAME_SHEETS + " (" +
                SHEETS_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                SHEETS_COLUMN_SHEET_NAME + " TEXT NOT NULL DEFAULT 'Untitled', " +
                SHEETS_COLUMN_CATEGORY_ID + " INTEGER NOT NULL DEFAULT 1);";

        final String SQL_CREATE_COLUMNS_TABLE = "CREATE TABLE " + TABLE_NAME_COLUMNS + " (" +
                COLUMNS_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                COLUMNS_COLUMN_SHEET_ID + " INTEGER NOT NULL, " +
                COLUMNS_COLUMN_COLUMN_NAME + " TEXT NOT NULL DEFAULT '', " +
                COLUMNS_COLUMN_ORDER_IN_SHEET + " INTEGER NOT NULL DEFAULT 0, " +
                COLUMNS_COLUMN_RANK_IN_SHEET + " INTEGER NOT NULL DEFAULT 0, " +
                "FOREIGN KEY(" + COLUMNS_COLUMN_SHEET_ID + ") REFERENCES " + TABLE_NAME_SHEETS + "(" + SHEETS_COLUMN_ID + "));";

        final String SQL_CREATE_ROWS_TABLE = "CREATE TABLE " + TABLE_NAME_ROWS + " (" +
                ROWS_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                ROWS_COLUMN_COLUMN_ID + " INTEGER NOT NULL, " +
                ROWS_COLUMN_ROW_NAME + " TEXT NOT NULL DEFAULT '', " +
                ROWS_COLUMN_UNIT_ID + " INTEGER NOT NULL DEFAULT 0, " +
                ROWS_COLUMN_ORDER_IN_COLUMN + " INTEGER NOT NULL DEFAULT 0, " +
                ROWS_COLUMN_SORT_ID + " INTEGER NOT NULL DEFAULT 1, " +
                "FOREIGN KEY(" + ROWS_COLUMN_COLUMN_ID + ") REFERENCES " + TABLE_NAME_COLUMNS + "(" + COLUMNS_COLUMN_ID + "));";

        final String SQL_CREATE_CELLS_TABLE = "CREATE TABLE " + TABLE_NAME_CELLS + " (" +
                CELLS_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                CELLS_COLUMN_ROW_ID + " INTEGER NOT NULL, " +
                CELLS_COLUMN_CELL_VALUE + " TEXT NOT NULL DEFAULT '', " +
                CELLS_COLUMN_RANK + " INTEGER NOT NULL DEFAULT 0, " +
                "FOREIGN KEY(" + CELLS_COLUMN_ROW_ID + ") REFERENCES " + TABLE_NAME_ROWS + "(" + ROWS_COLUMN_ID + "));";

        final String SQL_CREATE_REPORTS_TABLE = "CREATE TABLE " + TABLE_NAME_REPORTS + " (" +
                REPORTS_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                REPORTS_COLUMN_JSON_DATA + " TEXT NOT NULL DEFAULT '', " +
                REPORTS_COLUMN_SHEET_NAME + " TEXT NOT NULL, " +
                REPORTS_COLUMN_CATEGORY_NAME + " TEXT NOT NULL, " +
                REPORTS_COLUMN_CHOSEN_COLUMN_NAME + " TEXT NOT NULL, " +
                REPORTS_COLUMN_TIMESTAMP + " TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP, " +
                REPORTS_COLUMN_SENT + " INTEGER NOT NULL DEFAULT 0);";

        final String SQL_CREATE_UNITS_TABLE = "CREATE TABLE " + TABLE_NAME_UNITS + " (" +
                UNITS_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                UNITS_COLUMN_TYPE + " TEXT NOT NULL DEFAULT '', " +
                UNITS_COLUMN_NAME + " TEXT NOT NULL DEFAULT '', " +
                UNITS_COLUMN_SYMBOL + " TEXT NOT NULL DEFAULT '');";


        db.execSQL(SQL_CREATE_CATEGORIES_TABLE);
        db.execSQL(SQL_CREATE_SORTS_TABLE);
        db.execSQL(SQL_CREATE_SHEETS_TABLE);
        db.execSQL(SQL_CREATE_COLUMNS_TABLE);
        db.execSQL(SQL_CREATE_ROWS_TABLE);
        db.execSQL(SQL_CREATE_CELLS_TABLE);
        db.execSQL(SQL_CREATE_REPORTS_TABLE);
        db.execSQL(SQL_CREATE_UNITS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Ensure data is migrated over properly!
    }*/
    //endregion
    //region DATA FUNCTIONS
    public List<SheetPreview> getSheetPreviewList() {
        List<SheetPreview> listSheetPreviews = new ArrayList<>();

        final String selectAllSheetQueryString = "SELECT s." + SHEETS_COLUMN_ID + ", s." + SHEETS_COLUMN_SHEET_NAME +
                ", c." + CATEGORIES_COLUMN_CATEGORY_NAME + ", c." + CATEGORIES_COLUMN_IMAGE_NAME + " " +
                "FROM " + TABLE_NAME_SHEETS + " s INNER JOIN " +
                TABLE_NAME_CATEGORIES + " c ON s." + SHEETS_COLUMN_CATEGORY_ID + "=c." + CATEGORIES_COLUMN_ID + " " +
                "ORDER BY s." + SHEETS_COLUMN_ID + " ASC;";
        Cursor selectAllSheetQuery = db.rawQuery(selectAllSheetQueryString, null);
        Cursor selectColumnNameQuery = null;
        try {
            if (selectAllSheetQuery.moveToFirst()) {
                do {
                    SheetPreview sheetPreview = new SheetPreview();
                    sheetPreview.setSheetId(String.valueOf(selectAllSheetQuery.getInt(selectAllSheetQuery.getColumnIndexOrThrow(SHEETS_COLUMN_ID))));
                    sheetPreview.setSheetName(selectAllSheetQuery.getString(selectAllSheetQuery.getColumnIndexOrThrow(SHEETS_COLUMN_SHEET_NAME)));
                    sheetPreview.setCategoryName(selectAllSheetQuery.getString(selectAllSheetQuery.getColumnIndexOrThrow(CATEGORIES_COLUMN_CATEGORY_NAME)));
                    sheetPreview.setCategoryImageName(selectAllSheetQuery.getString(selectAllSheetQuery.getColumnIndexOrThrow(CATEGORIES_COLUMN_IMAGE_NAME)));

                    selectColumnNameQuery = db.query(TABLE_NAME_COLUMNS, new String[] {COLUMNS_COLUMN_COLUMN_NAME}, COLUMNS_COLUMN_SHEET_ID + "=?",
                            new String[] {sheetPreview.getSheetId()}, null, null, COLUMNS_COLUMN_ORDER_IN_SHEET + " ASC", "3");

                    if (selectColumnNameQuery.moveToFirst()) {
                        sheetPreview.setColumnOne(selectColumnNameQuery.getString(selectColumnNameQuery.getColumnIndexOrThrow(COLUMNS_COLUMN_COLUMN_NAME)));
                        if (selectColumnNameQuery.moveToNext()) {
                            sheetPreview.setColumnTwo(selectColumnNameQuery.getString(selectColumnNameQuery.getColumnIndexOrThrow(COLUMNS_COLUMN_COLUMN_NAME)));
                            if (selectColumnNameQuery.moveToNext()) {
                                sheetPreview.setHasMoreColumns(true);
                            }
                        }
                    }
                    listSheetPreviews.add(sheetPreview);
                } while (selectAllSheetQuery.moveToNext());
            }
        }
        catch (SQLiteException e) {
            Log.e("SQL Error", e.getMessage());
            return null;
        }
        finally
        {
            selectAllSheetQuery.close();
            if (selectColumnNameQuery != null) {
                selectColumnNameQuery.close();
            }
        }
        return listSheetPreviews;
    }

    public int getNewSheetId() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(SHEETS_COLUMN_SHEET_NAME, R.string.db_sheet_name_default);
        contentValues.put(SHEETS_COLUMN_CATEGORY_ID, 1);
        final int newSheetId;
        try {
            newSheetId = (int) db.insertOrThrow(TABLE_NAME_SHEETS, null, contentValues);
        }
        catch (SQLiteException e) {
            Log.e("SQL Error", e.getMessage());
            return 0;
        }
        return newSheetId;
    }

    public List<SheetColumn> getSheetColumnList(int iSheetId) {
        List<SheetColumn> listSheetColumns = new ArrayList<>();

        Cursor selectColumnsQuery = db.query(TABLE_NAME_COLUMNS, new String[] {COLUMNS_COLUMN_ID, COLUMNS_COLUMN_COLUMN_NAME, COLUMNS_COLUMN_ORDER_IN_SHEET, COLUMNS_COLUMN_RANK_IN_SHEET},
                COLUMNS_COLUMN_SHEET_ID + "=?", new String[] {String.valueOf(iSheetId)}, null, null, COLUMNS_COLUMN_ORDER_IN_SHEET);

        try {
            if (selectColumnsQuery.moveToFirst()) {
                do {
                    SheetColumn sheetColumn = new SheetColumn();
                    sheetColumn.setColumnId(selectColumnsQuery.getInt(selectColumnsQuery.getColumnIndexOrThrow(COLUMNS_COLUMN_ID)));
                    sheetColumn.setColumnName(selectColumnsQuery.getString(selectColumnsQuery.getColumnIndexOrThrow(COLUMNS_COLUMN_COLUMN_NAME)));
                    sheetColumn.setColumnOrder(selectColumnsQuery.getInt(selectColumnsQuery.getColumnIndexOrThrow(COLUMNS_COLUMN_ORDER_IN_SHEET)));
                    sheetColumn.setRankInSheet(selectColumnsQuery.getInt(selectColumnsQuery.getColumnIndexOrThrow(COLUMNS_COLUMN_RANK_IN_SHEET)));
                    listSheetColumns.add(sheetColumn);
                } while (selectColumnsQuery.moveToNext());
            }
            else {
                // create a new column
                ContentValues contentValues = new ContentValues();
                contentValues.put(COLUMNS_COLUMN_SHEET_ID, iSheetId);
                contentValues.put(COLUMNS_COLUMN_COLUMN_NAME, mContext.getString(R.string.db_column_name_default));
                contentValues.put(COLUMNS_COLUMN_ORDER_IN_SHEET, 0);
                contentValues.put(COLUMNS_COLUMN_RANK_IN_SHEET, 0);
                final int newColumnId = (int) db.insertOrThrow(TABLE_NAME_COLUMNS, null, contentValues);

                SheetColumn sheetColumn = new SheetColumn();
                sheetColumn.setColumnId(newColumnId);
                sheetColumn.setColumnName(mContext.getString(R.string.db_column_name_default));
                sheetColumn.setColumnOrder(0);
                sheetColumn.setRankInSheet(0);
                listSheetColumns.add(sheetColumn);
            }
        }
        catch (SQLiteException e) {
            Log.e("SQL Error", e.getMessage());
            return null;
        }
        finally
        {
            selectColumnsQuery.close();
        }
        return listSheetColumns;
    }

    public List<SheetRow> getSheetRowList(int iSheetId) {
        List<SheetRow> listSheetRows = new ArrayList<>();

        Cursor selectRowsQuery = db.query(TABLE_NAME_ROWS, new String[] {ROWS_COLUMN_ID, ROWS_COLUMN_ROW_NAME, ROWS_COLUMN_UNIT_ID, ROWS_COLUMN_ORDER_IN_COLUMN, ROWS_COLUMN_SORT_ID},
                ROWS_COLUMN_SHEET_ID + "=?", new String[] {String.valueOf(iSheetId)}, null, null, ROWS_COLUMN_ORDER_IN_COLUMN);

        try {
            if (selectRowsQuery.moveToFirst()) {
                do {
                    SheetRow sheetRow = new SheetRow();
                    sheetRow.setRowId(selectRowsQuery.getInt(selectRowsQuery.getColumnIndexOrThrow(ROWS_COLUMN_ID)));
                    sheetRow.setRowName(selectRowsQuery.getString(selectRowsQuery.getColumnIndexOrThrow(ROWS_COLUMN_ROW_NAME)));
                    sheetRow.setUnitId(selectRowsQuery.getInt(selectRowsQuery.getColumnIndexOrThrow(ROWS_COLUMN_UNIT_ID)));
                    sheetRow.setRowOrder(selectRowsQuery.getInt(selectRowsQuery.getColumnIndexOrThrow(ROWS_COLUMN_ORDER_IN_COLUMN)));
                    sheetRow.setRowSortId(selectRowsQuery.getInt(selectRowsQuery.getColumnIndexOrThrow(ROWS_COLUMN_SORT_ID)));
                    listSheetRows.add(sheetRow);
                } while (selectRowsQuery.moveToNext());
            }
            else {
                // create a new row
                ContentValues contentValues = new ContentValues();
                contentValues.put(ROWS_COLUMN_SHEET_ID, iSheetId);
                contentValues.put(ROWS_COLUMN_ROW_NAME, mContext.getString(R.string.db_row_name_default));
                contentValues.put(ROWS_COLUMN_UNIT_ID, 0);
                contentValues.put(ROWS_COLUMN_ORDER_IN_COLUMN, 1);
                contentValues.put(ROWS_COLUMN_SORT_ID, 1);
                final int newRowId = (int) db.insertOrThrow(TABLE_NAME_ROWS, null, contentValues);

                SheetRow sheetRow = new SheetRow();
                sheetRow.setRowId(newRowId);
                sheetRow.setRowName(mContext.getString(R.string.db_row_name_default));
                sheetRow.setUnitId(0);
                sheetRow.setRowOrder(1);
                sheetRow.setRowSortId(1);
                listSheetRows.add(sheetRow);
            }
        }
        catch (SQLiteException e) {
            Log.e("SQL Error", e.getMessage());
            return null;
        }
        finally
        {
            selectRowsQuery.close();
        }
        return listSheetRows;
    }

    public List<SheetCell> getSheetCellList(int iSheetId, int iColumnId, int iRowId) {
        List<SheetCell> listSheetCells = new ArrayList<>();

        final String selectCellsQueryString = "SELECT ce." + CELLS_COLUMN_ID + ", ce." + CELLS_COLUMN_COLUMN_ID +
                ", ce." + CELLS_COLUMN_ROW_ID + ", ce." + CELLS_COLUMN_CELL_VALUE + ", ce." + CELLS_COLUMN_RANK + " " +
                "FROM " + TABLE_NAME_CELLS + " ce INNER JOIN " +
                TABLE_NAME_COLUMNS + " co ON ce." + CELLS_COLUMN_COLUMN_ID + "=co." + COLUMNS_COLUMN_ID + " " +
                "WHERE co." + COLUMNS_COLUMN_SHEET_ID + "=" + String.valueOf(iSheetId) + ";";
        Cursor selectCellsQuery = db.rawQuery(selectCellsQueryString, null);

        try {
            if (selectCellsQuery.moveToFirst()) {
                do {
                    SheetCell sheetCell = new SheetCell();
                    sheetCell.setCellId(selectCellsQuery.getInt(selectCellsQuery.getColumnIndexOrThrow(CELLS_COLUMN_ID)));
                    sheetCell.setColumnId(selectCellsQuery.getInt(selectCellsQuery.getColumnIndexOrThrow(CELLS_COLUMN_COLUMN_ID)));
                    sheetCell.setRowId(selectCellsQuery.getInt(selectCellsQuery.getColumnIndexOrThrow(CELLS_COLUMN_ROW_ID)));
                    sheetCell.setCellValue(selectCellsQuery.getString(selectCellsQuery.getColumnIndexOrThrow(CELLS_COLUMN_CELL_VALUE)));
                    sheetCell.setRankInRow(selectCellsQuery.getInt(selectCellsQuery.getColumnIndexOrThrow(CELLS_COLUMN_RANK)));
                    listSheetCells.add(sheetCell);
                } while (selectCellsQuery.moveToNext());
            }
            else {
                // create a new cell
                ContentValues contentValues = new ContentValues();
                contentValues.put(CELLS_COLUMN_COLUMN_ID, iColumnId);
                contentValues.put(CELLS_COLUMN_ROW_ID, iRowId);
                contentValues.put(CELLS_COLUMN_CELL_VALUE, "");
                contentValues.put(CELLS_COLUMN_RANK, 0);
                final int newCellId = (int) db.insertOrThrow(TABLE_NAME_CELLS, null, contentValues);

                SheetCell sheetCell = new SheetCell();
                sheetCell.setCellId(newCellId);
                sheetCell.setColumnId(iColumnId);
                sheetCell.setRowId(iRowId);
                sheetCell.setCellValue("");
                sheetCell.setRankInRow(0);
                listSheetCells.add(sheetCell);
            }
        }
        catch (SQLiteException e) {
            Log.e("SQL Error", e.getMessage());
            return null;
        }
        finally
        {
            selectCellsQuery.close();
        }
        return listSheetCells;
    }
    //endregion
}
