package com.compare.compare;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ListView;
import android.widget.TextView;

public class SheetActivity extends AppCompatActivity {
    private SmartFragmentStatePagerAdapter mColumnsPagerAdapter;
    private ViewPager mViewPager;
    private SheetContent mSheetContent;

    public SheetActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sheet);

        Intent intent = getIntent();
        if (intent.hasExtra("sheetId")) {
            mSheetContent = new SheetContent(this, intent.getStringExtra("sheetId"));
        }
        else
        {
            mSheetContent = new SheetContent(this, "");
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mColumnsPagerAdapter = new ColumnsPagerAdapter(getSupportFragmentManager(), mSheetContent);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mColumnsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sheet, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class ColumnFragment extends Fragment {
        private static SheetContent mSheetContent;
        private static int mColumnNumber;

        public ColumnFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static ColumnFragment newInstance(int columnNumber, SheetContent sheetContent) {
            ColumnFragment fragment = new ColumnFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable("sheetContent", sheetContent);
            bundle.putInt("columnNumber", columnNumber);
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            mSheetContent = getArguments().getParcelable("sheetContent");
            mColumnNumber = getArguments().getInt("columnNumber", 0);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View view = inflater.inflate(R.layout.fragment_column, container, false);
            ListView listView = (ListView)view.findViewById(R.id.rows_data);
            //TextView textView = (TextView) view.findViewById(R.id.section_label);
            //textView.setText(mSheetContent.getSheetColumns().get(mColumnNumber).getColumnName());
            return view;
        }
    }

    public static class ColumnsPagerAdapter extends SmartFragmentStatePagerAdapter {
        private SheetContent mSheetContent;

        public ColumnsPagerAdapter(FragmentManager fm, SheetContent sheetContent) {
            super(fm);
            this.mSheetContent = sheetContent;
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return ColumnFragment.newInstance(position, mSheetContent);
        }

        @Override
        public int getCount() {
            return mSheetContent.getSheetColumns().size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mSheetContent.getSheetColumns().get(position).getColumnName();
        }
    }
}
