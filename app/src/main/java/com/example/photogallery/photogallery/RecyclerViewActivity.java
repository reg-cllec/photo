package com.example.photogallery.photogallery;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

import com.lxj.xrefreshlayout.XRefreshLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dance on 2017/4/3.
 */

//this activity is the entry point of the application, show recent photos in grid
// user can swipe to refresh, set view by 1 column or 3 column,
// default is 2 column(also availible by refresh button)
public class RecyclerViewActivity extends AppCompatActivity {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    ArrayList<Images> list = new ArrayList<>();
    @BindView(R.id.xrefreshLayout)
    XRefreshLayout xrefreshLayout;
    private GalleryAdapter myAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        ButterKnife.bind(this);

        initView();

        initData();


        xrefreshLayout.setOnRefreshListener(new XRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestDataFromServer(false);
            }

            @Override
            public void onLoadMore() {
                requestDataFromServer(true);
            }
        });

        recyclerview.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        myAdapter = new GalleryAdapter(this, list);
        recyclerview.setAdapter(myAdapter);

    }

    private void initView() {
        String title = "Photo Gallery";
        getSupportActionBar().setTitle(title);
    }

    private void initData() {
        //prepareData
        list.clear();
        list.addAll(HandleXML.updatePhotos());

    }

    private void requestDataFromServer(final boolean isLoadMore) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                // refresh the photos, get recent updated
                list.clear();
                list.addAll(HandleXML.updatePhotos());

                if (isLoadMore) {
                    SystemClock.sleep(1);
                } else {
                    SystemClock.sleep(1);
                }

                //updateUI
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        myAdapter.notifyDataSetChanged();
                        xrefreshLayout.completeRefresh();
                    }
                });

            }
        }.start();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.linear_manager:
                initData();
                recyclerview.setLayoutManager(new GridLayoutManager(this,2));
                myAdapter.notifyDataSetChanged();
                break;
            case R.id.grid_manager:
                initData();
                recyclerview.setLayoutManager(new GridLayoutManager(this,1));
                myAdapter.notifyDataSetChanged();
                break;
            case R.id.staggered_manager:
                initData();
                recyclerview.setLayoutManager(new GridLayoutManager(this,3));
                myAdapter.notifyDataSetChanged();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }
}
