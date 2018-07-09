package com.waimai.main;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class BuyActivity extends AppCompatActivity {

    private Toolbar buyBar;
    private AppCompatImageView buyReturn, buyShopImg;
    private AppCompatTextView buyShopName, buySum;
    private RecyclerView menuRight;
    private AppCompatButton buyButton;
    private List<MenuRight> menuRights = new ArrayList<>();
    private MenurightRecyclerAdapter rightAdapter;
    private Intent getDeta,sendMsg;
    private String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);

        buyBar = (Toolbar) findViewById(R.id.buy_toolbar);

        buyReturn = (AppCompatImageView) findViewById(R.id.buy_return);
        buyShopImg = (AppCompatImageView) findViewById(R.id.buy_shop_img);

        buyReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        getDeta = getIntent();
        str = getIntent().getStringExtra("shopName");

        buyShopName = (AppCompatTextView) findViewById(R.id.buy_shop_name);
        buySum = (AppCompatTextView) findViewById(R.id.buy_sum);

        buyShopName.setText(str);

        menuRight = (RecyclerView) findViewById(R.id.menu_right);

        buyButton = (AppCompatButton) findViewById(R.id.buy_button);

        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(BuyActivity.this,String.valueOf(rightAdapter.getJiSuan()),Toast.LENGTH_SHORT).show();
                if (rightAdapter.getJiSuan() <= 0){
                    Toast.makeText(BuyActivity.this,"请选择菜品",Toast.LENGTH_SHORT).show();
                }else {
                    sendMsg = new Intent(BuyActivity.this,PaymentActivity.class);
                    sendMsg.putExtra("Money",String.valueOf(rightAdapter.getJiSuan()));
                    sendMsg.putExtra("shopName",str);
                    sendMsg.putExtra("foodNum",String.valueOf(rightAdapter.getfoodCount()));
                    startActivity(sendMsg);
                }
            }
        });

        addMenuRight();

        LinearLayoutManager layoutManager2 = new LinearLayoutManager(BuyActivity.this);

        menuRight.setLayoutManager(layoutManager2);

        layoutManager2.setOrientation(OrientationHelper.VERTICAL);

        menuRight.addItemDecoration(new DividerItemDecoration(BuyActivity.this, DividerItemDecoration.VERTICAL));

        menuRight.setItemAnimator(new DefaultItemAnimator());
        rightAdapter = new MenurightRecyclerAdapter(BuyActivity.this, menuRights);

        menuRight.setAdapter(rightAdapter);
    }

    private void addMenuRight() {
        MenuRight right;
        right = new MenuRight(R.drawable.b8ced22b48d6914b7b436deb70303c3f, "红烧肉", "12");
        menuRights.add(right);
        right = new MenuRight(R.drawable.b8ced22b48d6914b7b436deb70303c3f, "红烧牛肉", "14");
        menuRights.add(right);
        right = new MenuRight(R.drawable.b8ced22b48d6914b7b436deb70303c3f, "青椒小炒肉", "10");
        menuRights.add(right);
        right = new MenuRight(R.drawable.b8ced22b48d6914b7b436deb70303c3f, "土豆小炒肉", "10");
        menuRights.add(right);
        right = new MenuRight(R.drawable.b8ced22b48d6914b7b436deb70303c3f, "回锅肉", "10");
        menuRights.add(right);
        right = new MenuRight(R.drawable.b8ced22b48d6914b7b436deb70303c3f, "盐煎肉", "10");
        menuRights.add(right);
        right = new MenuRight(R.drawable.b8ced22b48d6914b7b436deb70303c3f, "鱼香肉丝", "9");
        menuRights.add(right);
        right = new MenuRight(R.drawable.b8ced22b48d6914b7b436deb70303c3f, "泡椒炒肉", "9");
        menuRights.add(right);
        right = new MenuRight(R.drawable.b8ced22b48d6914b7b436deb70303c3f, "藤藤菜", "6");
        menuRights.add(right);
        right = new MenuRight(R.drawable.b8ced22b48d6914b7b436deb70303c3f, "土豆丝", "6");
        menuRights.add(right);
        right = new MenuRight(R.drawable.b8ced22b48d6914b7b436deb70303c3f, "煮白菜", "6");
        menuRights.add(right);
    }

    public void updateUI(Double sum){
        if (sum == 0){
            buySum.setText("请下单");
        }else buySum.setText("¥"+sum);
    }
}
