package com.compare.compare;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Doraemon on 4/18/2017.
 */

public class SheetContent implements Parcelable {
    //region VARIABLES
    private final int m_iSheetId;
    private List<SheetColumn> m_lSheetColumns;
    private List<SheetRow> m_lSheetRows;
    private List<SheetCell> m_lSheetCells;
    //endregion
    //region CONSTRUCTORS
    public SheetContent(Context context, String sSheetId) {
        BookDatabaseHelper db = BookDatabaseHelper.getInstance(context);
        if (sSheetId.isEmpty()) {
            this.m_iSheetId = db.getNewSheetId();
        }
        else {
            this.m_iSheetId = Integer.valueOf(sSheetId);
        }
        this.m_lSheetColumns = db.getSheetColumnList(m_iSheetId);
        this.m_lSheetRows = db.getSheetRowList(m_iSheetId);
        this.m_lSheetCells = db.getSheetCellList(m_iSheetId, m_lSheetColumns.get(0).getColumnId(), m_lSheetRows.get(0).getRowId());
    }
    private SheetContent(Parcel in) {
        this.m_iSheetId = in.readInt();
        this.m_lSheetColumns = new ArrayList<>();
        in.readTypedList(m_lSheetColumns, SheetColumn.CREATOR);
        this.m_lSheetRows = new ArrayList<>();
        in.readTypedList(m_lSheetRows, SheetRow.CREATOR);
        this.m_lSheetCells = new ArrayList<>();
        in.readTypedList(m_lSheetCells, SheetCell.CREATOR);
    }
    //endregion
    //region GETTERS
    public int getSheetId() {
        return m_iSheetId;
    }

    public List<SheetColumn> getSheetColumns() {
        return m_lSheetColumns;
    }

    public List<SheetRow> getSheetRows() {
        return m_lSheetRows;
    }

    public List<SheetCell> getSheetCells() {
        return m_lSheetCells;
    }
    //endregion
    //region PARCELABLE OVERWRITES
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(m_iSheetId);
        out.writeTypedList(m_lSheetColumns);
        out.writeTypedList(m_lSheetRows);
        out.writeTypedList(m_lSheetCells);
    }

    public static final Parcelable.Creator<SheetContent> CREATOR
            = new Parcelable.Creator<SheetContent>() {
        @Override
        public SheetContent createFromParcel(Parcel in) {
            return new SheetContent(in);
        }

        @Override
        public SheetContent[] newArray(int size) {
            return new SheetContent[0];
        }
    };
    //endregion
    //region DATA FUNCTIONS

    //endregion
}
