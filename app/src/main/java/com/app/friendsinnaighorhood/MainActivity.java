package com.app.friendsinnaighorhood;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int RANGE_ITEM_VALUE = 5;
    public static final int GRID_MAX_ROW_COL = 5;

    private RecyclerView mRView;
    private RAdapter mAdapter;
    private List<GridDataItem> mGridData;
    private Button mRefresh;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mRView  = (RecyclerView) findViewById(R.id.rvView);
        mRefresh = (Button) findViewById(R.id.refresh);
        GridLayoutManager layoutManager = new GridLayoutManager(this,GRID_MAX_ROW_COL);
        mRView.setLayoutManager(layoutManager);
        generateDataAndShow();
        mRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateDataAndShow();
            }
        });
    }

    private void generateDataAndShow() {
        mGridData = new ArrayList<>();
        for (int i = 0; i< GRID_MAX_ROW_COL*GRID_MAX_ROW_COL;i++) {
            int randNoInRange = (int) (Math.random() * RANGE_ITEM_VALUE);
            mGridData.add(new GridDataItem(randNoInRange,false));
        }
        mAdapter = new RAdapter(this,mGridData);
        mRView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
