package com.compare.compare;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Doraemon on 4/20/2017.
 */

public class SheetColumn implements Parcelable {
    //region VARIABLES
    private int m_iColumnId;
    private String m_sColumnName;
    private int m_iColumnOrder;
    private int m_iRankInSheet;
    //endregion
    //region CONSTRUCTORS
    public SheetColumn() {
        this.m_iColumnId = 0;
        this.m_sColumnName = "";
        this.m_iColumnOrder = 0;
        this.m_iRankInSheet = 0;
    }
    public SheetColumn(SheetColumn sheetColumns) {
        this.m_iColumnId = sheetColumns.getColumnId();
        this.m_sColumnName = sheetColumns.getColumnName();
        this.m_iColumnOrder = sheetColumns.getColumnOrder();
        this.m_iRankInSheet = sheetColumns.getRankInSheet();
    }
    public SheetColumn(int iColumnId, String sColumnName, int iColumnOrder, int iRankInSheet) {
        this.m_iColumnId = iColumnId;
        this.m_sColumnName = sColumnName;
        this.m_iColumnOrder = iColumnOrder;
        this.m_iRankInSheet = iRankInSheet;
    }
    private SheetColumn(Parcel in) {
        this.m_iColumnId = in.readInt();
        this.m_sColumnName = in.readString();
        this.m_iColumnOrder = in.readInt();
        this.m_iRankInSheet = in.readInt();
    }
    //endregion
    //region GETTERS AND SETTERS
    public int getColumnId() {
        return m_iColumnId;
    }

    public void setColumnId(int iColumnId) {
        this.m_iColumnId = iColumnId;
    }

    public String getColumnName() {
        return m_sColumnName;
    }

    public void setColumnName(String sColumnName) {
        this.m_sColumnName = sColumnName;
    }

    public int getColumnOrder() {
        return m_iColumnOrder;
    }

    public void setColumnOrder(int iColumnOrder) {
        this.m_iColumnOrder = iColumnOrder;
    }

    public int getRankInSheet() {
        return m_iRankInSheet;
    }

    public void setRankInSheet(int iRankInSheet) {
        this.m_iRankInSheet = iRankInSheet;
    }
    //endregion
    //region PARCELABLE OVERWRITES
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(m_iColumnId);
        out.writeString(m_sColumnName);
        out.writeInt(m_iColumnOrder);
        out.writeInt(m_iRankInSheet);
    }

    public static final Parcelable.Creator<SheetColumn> CREATOR
            = new Parcelable.Creator<SheetColumn>() {
        @Override
        public SheetColumn createFromParcel(Parcel in) {
            return new SheetColumn(in);
        }

        @Override
        public SheetColumn[] newArray(int size) {
            return new SheetColumn[0];
        }
    };
    //endregion
}
