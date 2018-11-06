package com.compare.compare;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Doraemon on 4/20/2017.
 */

public class SheetRow implements Parcelable {
    //region VARIABLES
    private int m_iRowId;
    private String m_sRowName;
    private int m_iUnitId;
    private int m_iRowOrder;
    private int m_iRowSortId;
    //endregion
    //region CONSTRUCTORS
    public SheetRow() {
        this.m_iRowId = 0;
        this.m_sRowName = "";
        this.m_iUnitId = 0;
        this.m_iRowOrder = 0;
        this.m_iRowSortId = 0;
    }
    public SheetRow(SheetRow sheetRow) {
        this.m_iRowId = sheetRow.getRowId();
        this.m_sRowName = sheetRow.getRowName();
        this.m_iUnitId = sheetRow.getUnitId();
        this.m_iRowOrder = sheetRow.getRowOrder();
        this.m_iRowSortId = sheetRow.getRowSortId();
    }
    public SheetRow(int iRowId, String sRowName, int iUnitId, int iRowOrder, int iRowSortId) {
        this.m_iRowId = iRowId;
        this.m_sRowName = sRowName;
        this.m_iUnitId = iUnitId;
        this.m_iRowOrder = iRowOrder;
        this.m_iRowSortId = iRowSortId;
    }
    private SheetRow(Parcel in) {
        this.m_iRowId = in.readInt();
        this.m_sRowName = in.readString();
        this.m_iUnitId = in.readInt();
        this.m_iRowOrder = in.readInt();
        this.m_iRowSortId = in.readInt();
    }
    //endregion
    //region GETTERS AND SETTERS
    public int getRowId() {
        return m_iRowId;
    }

    public void setRowId(int iRowId) {
        this.m_iRowId = iRowId;
    }

    public String getRowName() {
        return m_sRowName;
    }

    public void setRowName(String sRowName) {
        this.m_sRowName = sRowName;
    }

    public int getUnitId() {
        return m_iUnitId;
    }

    public void setUnitId(int iUnitId) {
        this.m_iUnitId = iUnitId;
    }

    public int getRowOrder() {
        return m_iRowOrder;
    }

    public void setRowOrder(int iRowOrder) {
        this.m_iRowOrder = iRowOrder;
    }

    public int getRowSortId() {
        return m_iRowSortId;
    }

    public void setRowSortId(int iRowSortId) {
        this.m_iRowSortId = iRowSortId;
    }
    //endregion
    //region PARCELABLE OVERWRITES
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(m_iRowId);
        out.writeString(m_sRowName);
        out.writeInt(m_iUnitId);
        out.writeInt(m_iRowOrder);
        out.writeInt(m_iRowSortId);
    }

    public static final Parcelable.Creator<SheetRow> CREATOR
            = new Parcelable.Creator<SheetRow>() {
        @Override
        public SheetRow createFromParcel(Parcel in) {
            return new SheetRow(in);
        }

        @Override
        public SheetRow[] newArray(int size) {
            return new SheetRow[0];
        }
    };
    //endregion
}
