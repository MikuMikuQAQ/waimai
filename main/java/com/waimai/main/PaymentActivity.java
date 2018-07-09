package com.waimai.main;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class PaymentActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;

    private FragmentTransaction transaction;

    private PaywaitFragment paywaitFragment;

    private PayokFragment payokFragment;

    private Toolbar toolbar;

    private AppCompatImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();

        paywaitFragment = new PaywaitFragment();
        transaction.replace(R.id.payment_layout,paywaitFragment);

        transaction.commit();

        toolbar = (Toolbar) findViewById(R.id.payment_toolbar);

        imageView = (AppCompatImageView) findViewById(R.id.payment_return);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
