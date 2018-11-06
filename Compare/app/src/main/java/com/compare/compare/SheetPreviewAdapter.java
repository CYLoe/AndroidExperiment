package com.compare.compare;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by Doraemon on 4/15/2017.
 */

public class SheetPreviewAdapter extends BaseAdapter {
    private final Context mContext;
    private final List<SheetPreview> mSheetPreviews;

    public SheetPreviewAdapter(Context context) {
        this.mContext = context;
        BookDatabaseHelper db = BookDatabaseHelper.getInstance(context);
        mSheetPreviews = db.getSheetPreviewList();
    }

    @Override
    public int getCount() {
        return mSheetPreviews.size();
    }

    @Override
    public Object getItem(int position) {
        return mSheetPreviews.get(position);
    }

    @Override
    public long getItemId(int position) {
        return Long.valueOf(mSheetPreviews.get(position).getSheetId());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.linearlayout_book, null);
        }

        SheetPreview sheetPreview = mSheetPreviews.get(position);

        final ImageView imageView = (ImageView)convertView.findViewById(R.id.imageview_category);
        final TextView sheetNameTextView = (TextView)convertView.findViewById(R.id.textview_sheet_name);
        final TextView categoryNameTextView = (TextView)convertView.findViewById(R.id.textview_category_name);
        final TextView columnOneTextView = (TextView)convertView.findViewById(R.id.textview_column_1);
        final TextView versusTextView = (TextView)convertView.findViewById(R.id.textview_versus);
        final TextView columnTwoTextView = (TextView)convertView.findViewById(R.id.textview_column_2);
        final TextView etcTextView = (TextView)convertView.findViewById(R.id.textview_etc);

        try {
            Class res = R.drawable.class;
            Field field = res.getField(sheetPreview.getCategoryImageName());
            imageView.setImageResource(field.getInt(null));
        }
        catch (Exception e) {
            Log.e("Get drawable id error.", e.getMessage());
        }
        sheetNameTextView.setText(sheetPreview.getSheetName());
        categoryNameTextView.setText(sheetPreview.getCategoryName());
        columnOneTextView.setText(sheetPreview.getColumnOne());
        if (sheetPreview.getColumnTwo().isEmpty()) {
            versusTextView.setText("");
        }
        else {
            versusTextView.setText(R.string.book_adapter_versus);
        }
        columnTwoTextView.setText(sheetPreview.getColumnTwo());
        if (sheetPreview.getHasMoreColumns()) {
            etcTextView.setText(R.string.book_adapter_etc);
        }
        else {
            etcTextView.setText("");
        }

        return convertView;
    }
}
