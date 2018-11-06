package com.compare.compare;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Doraemon on 4/20/2017.
 */

public class SheetCell implements Parcelable {
    //region VARIABLES
    private int m_iCellId;
    private int m_iColumnId;
    private int m_iRowId;
    private String m_sCellValue;
    private int m_iRankInRow;
    //endregion
    //region CONSTRUCTORS
    public SheetCell() {
        this.m_iCellId = 0;
        this.m_iColumnId = 1;
        this.m_iRowId = 1;
        this.m_sCellValue = "";
        this.m_iRankInRow = 0;
    }
    public SheetCell(SheetCell sheetCell) {
        this.m_iCellId = sheetCell.getCellId();
        this.m_iColumnId = sheetCell.getColumnId();
        this.m_iRowId = sheetCell.getRowId();
        this.m_sCellValue = sheetCell.getCellValue();
        this.m_iRankInRow = sheetCell.getRankInRow();
    }
    public SheetCell(int iCellId, int iColumnId, int iRowId, String sCellValue, int iRankInRow) {
        this.m_iCellId = iCellId;
        this.m_iColumnId = iColumnId;
        this.m_iRowId = iRowId;
        this.m_sCellValue = sCellValue;
        this.m_iRankInRow = iRankInRow;
    }
    private SheetCell(Parcel in) {
        this.m_iCellId = in.readInt();
        this.m_iColumnId = in.readInt();
        this.m_iRowId = in.readInt();
        this.m_sCellValue = in.readString();
        this.m_iRankInRow = in.readInt();
    }
    //endregion
    //region GETTERS AND SETTERS
    public int getCellId() {
        return m_iCellId;
    }

    public void setCellId(int iCellId) {
        this.m_iCellId = iCellId;
    }

    public int getColumnId() {
        return m_iColumnId;
    }

    public void setColumnId(int iColumnId) {
        this.m_iColumnId = iColumnId;
    }

    public int getRowId() {
        return m_iRowId;
    }

    public void setRowId(int iRowId) {
        this.m_iRowId = iRowId;
    }

    public String getCellValue() {
        return m_sCellValue;
    }

    public void setCellValue(String sCellValue) {
        this.m_sCellValue = sCellValue;
    }

    public int getRankInRow() {
        return m_iRankInRow;
    }

    public void setRankInRow(int iRankInRow) {
        this.m_iRankInRow = iRankInRow;
    }
    //endregion
    //region PARCELABLE OVERWRITES
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(m_iCellId);
        out.writeInt(m_iColumnId);
        out.writeInt(m_iRowId);
        out.writeString(m_sCellValue);
        out.writeInt(m_iRankInRow);
    }

    public static final Parcelable.Creator<SheetCell> CREATOR
            = new Parcelable.Creator<SheetCell>() {
        @Override
        public SheetCell createFromParcel(Parcel in) {
            return new SheetCell(in);
        }

        @Override
        public SheetCell[] newArray(int size) {
            return new SheetCell[0];
        }
    };
    //endregion
}