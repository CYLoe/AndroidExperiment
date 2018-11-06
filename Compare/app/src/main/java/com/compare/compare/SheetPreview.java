package com.compare.compare;

/**
 * Created by Doraemon on 4/15/2017.
 */

public class SheetPreview {
    //region VARIABLES
    private String m_sSheetId;
    private String m_sSheetName;
    private String m_sCategoryName;
    private String m_sColumnOne;
    private String m_sColumnTwo;
    private Boolean m_bHasMoreColumns;
    private String m_sCategoryImageName;
    //endregion
    //region CONSTRUCTORS
    public SheetPreview() {
        this.m_sSheetId = "0";
        this.m_sSheetName = "";
        this.m_sCategoryName = "";
        this.m_sColumnOne = "";
        this.m_sColumnTwo = "";
        this.m_bHasMoreColumns = false;
        this.m_sCategoryImageName = "";
    }
    public SheetPreview(SheetPreview sheetPreview) {
        this.m_sSheetId = sheetPreview.getSheetId();
        this.m_sSheetName = sheetPreview.getSheetName();
        this.m_sCategoryName = sheetPreview.getCategoryName();
        this.m_sColumnOne = sheetPreview.getColumnOne();
        this.m_sColumnTwo = sheetPreview.getColumnTwo();
        this.m_bHasMoreColumns = sheetPreview.getHasMoreColumns();
        this.m_sCategoryImageName = sheetPreview.getCategoryImageName();
    }
    //endregion
    //region GETTERS AND SETTERS
    public String getSheetId() {
        return m_sSheetId;
    }

    public void setSheetId(String sheetId) {
        this.m_sSheetId = sheetId;
    }

    public String getSheetName() {
        return m_sSheetName;
    }

    public void setSheetName(String sheetName) {
        this.m_sSheetName = sheetName;
    }

    public String getCategoryName() {
        return m_sCategoryName;
    }

    public void setCategoryName(String categoryName) {
        this.m_sCategoryName = categoryName;
    }

    public String getColumnOne() {
        return m_sColumnOne;
    }

    public void setColumnOne(String columnOne) {
        this.m_sColumnOne = columnOne;
    }

    public String getColumnTwo() {
        return m_sColumnTwo;
    }

    public void setColumnTwo(String columnTwo) {
        this.m_sColumnTwo = columnTwo;
    }

    public Boolean getHasMoreColumns() {
        return m_bHasMoreColumns;
    }

    public void setHasMoreColumns(Boolean hasMoreColumns) {
        this.m_bHasMoreColumns = hasMoreColumns;
    }

    public String getCategoryImageName() {
        return m_sCategoryImageName;
    }

    public void setCategoryImageName(String categoryImageName) {
        this.m_sCategoryImageName = categoryImageName;
    }
    //endregion
}
