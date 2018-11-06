package com.compare.compare;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Doraemon on 4/29/2017.
 */

public class SheetCellAdapter extends ArrayAdapter<SheetCell> {
    public SheetCellAdapter(Context context, ArrayList<SheetCell> sheetCells) {
        super(context, 0, sheetCells);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SheetCell sheetCell = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_column, parent, false);
        }

        return convertView;
    }
}
