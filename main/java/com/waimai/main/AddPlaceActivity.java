package com.waimai.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.waimai.dao.PlacemanageDAO;
import com.waimai.model.Waimai_placemanage;

public class AddPlaceActivity extends AppCompatActivity {

    private AppCompatEditText addUser,addPlace;
    private AppCompatImageView addReturn,placeSave;
    private Toolbar addToolbar;
    private PlacemanageDAO placemanageDAO;
    private Waimai_placemanage placemanage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addplace);

        placemanageDAO = new PlacemanageDAO(AddPlaceActivity.this);

        addToolbar = (Toolbar) findViewById(R.id.place_add_toolbar);

        addReturn = (AppCompatImageView) findViewById(R.id.place_add_return);
        addReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        addUser = (AppCompatEditText) findViewById(R.id.place_add_user);
        addPlace = (AppCompatEditText) findViewById(R.id.place_add_edit);

        placeSave = (AppCompatImageView) findViewById(R.id.place_save);
        placeSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!addUser.getText().toString().isEmpty()){
                    if (!addPlace.getText().toString().isEmpty()){
                        placemanage = new Waimai_placemanage(
                                placemanageDAO.getMaxId()+1,
                                addUser.getText().toString(),
                                addPlace.getText().toString()
                        );
                        placemanageDAO.add(placemanage);
                        addUser.setText("");
                        addPlace.setText("");
                        Toast.makeText(AddPlaceActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(AddPlaceActivity.this, "请填写收货地址", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(AddPlaceActivity.this, "请填写收货人", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
