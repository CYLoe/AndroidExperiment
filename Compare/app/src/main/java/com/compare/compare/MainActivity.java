package com.compare.compare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gridView = (GridView)findViewById(R.id.gridview_book);
        final SheetPreviewAdapter sheetPreviewAdapter = new SheetPreviewAdapter(this);
        gridView.setAdapter(sheetPreviewAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                String selectedSheetId = String.valueOf(sheetPreviewAdapter.getItemId(position));
                Intent intent = new Intent(MainActivity.this, SheetActivity.class)
                        .putExtra("sheetId", selectedSheetId);
                startActivity(intent);
            }
        });

    }
}
