package com.waimai.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.waimai.dao.PlacemanageDAO;
import com.waimai.model.Waimai_placemanage;

import java.util.ArrayList;
import java.util.List;

public class PlaceActivity extends AppCompatActivity {

    private AppCompatImageView placeReturn, placeAdd;
    private Toolbar placeToolbar;
    private Intent intent;
    private RecyclerView recyclerView;
    private List<PlaceAddress> placeAddressList = new ArrayList<>();
    private List<Waimai_placemanage> placemanageLists;
    private PlacemanageDAO placemanageDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);

        placeToolbar = (Toolbar) findViewById(R.id.place_toolbar);

        placeReturn = (AppCompatImageView) findViewById(R.id.place_return);
        placeAdd = (AppCompatImageView) findViewById(R.id.place_add);

        placeReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        placeAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(PlaceActivity.this, AddPlaceActivity.class);
                startActivity(intent);
            }
        });

        addPlaceList();

        recyclerView = (RecyclerView) findViewById(R.id.place_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(PlaceActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.addItemDecoration(new DividerItemDecoration(PlaceActivity.this, DividerItemDecoration.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        PlaceRecyclerAdapter adapter = new PlaceRecyclerAdapter(PlaceActivity.this, placeAddressList);
        recyclerView.setAdapter(adapter);
    }

    private void addPlaceList() {
        placemanageDAO = new PlacemanageDAO(PlaceActivity.this);
        placemanageLists = placemanageDAO.getScrollData(0,(int)placemanageDAO.getCount());
        String[] strings = new String[placemanageLists.size()];
        PlaceAddress address;
        for (int i = 0; i < placemanageLists.size(); i++){
            Waimai_placemanage placemanage = placemanageLists.get(i);
            address = new PlaceAddress(R.drawable.ic_trash, placemanage.getReceiptuser(), placemanage.getReceiptplace());
            placeAddressList.add(address);
        }
    }
}
