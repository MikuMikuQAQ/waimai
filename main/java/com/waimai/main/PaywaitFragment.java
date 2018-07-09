package com.waimai.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.waimai.dao.DingdanDAO;
import com.waimai.dao.UsermanageDAO;
import com.waimai.model.Waimai_dingdan;

import java.util.Calendar;

public class PaywaitFragment extends Fragment {

    private View view;
    private AppCompatTextView textView;
    private AppCompatButton button;
    private Intent intent;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private PayokFragment payokFragment;
    private UsermanageDAO usermanageDAO;
    private DingdanDAO dingdanDAO;
    private Waimai_dingdan dingdan;
    private Double balance, money;
    private String shopName, dateTime, str;
    private int foodNum, mYear, mMonth, mDay,integral;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) view = inflater.inflate(R.layout.fragment_paywait, container, false);
        ViewGroup group = (ViewGroup) view.getParent();
        if (group != null) group.removeView(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        intent = getActivity().getIntent();
        str = intent.getStringExtra("Money");
        shopName = intent.getStringExtra("shopName");
        foodNum = Integer.parseInt(intent.getStringExtra("foodNum"));
        money = Double.parseDouble(str);

        usermanageDAO = new UsermanageDAO(view.getContext());
        dingdanDAO = new DingdanDAO(view.getContext());

        textView = (AppCompatTextView) view.findViewById(R.id.paywait_money);
        textView.setText(str);

        button = (AppCompatButton) view.findViewById(R.id.paywait_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDate();
                balance = usermanageDAO.find().getBalance();
                integral = usermanageDAO.find().getIntegral();
                if (balance > money) {
                    Double i = balance - money;
                    integral += 20;
                    usermanageDAO.updateMoney(i, 0);
                    usermanageDAO.updateIntegral(integral,0);
                    dingdan = new Waimai_dingdan(
                            dingdanDAO.getMaxId() + 1,
                            0,
                            shopName,
                            money,
                            foodNum,
                            dateTime
                    );
                    dingdanDAO.add(dingdan);
                    fragmentManager = getActivity().getSupportFragmentManager();
                    transaction = fragmentManager.beginTransaction();

                    payokFragment = new PayokFragment();
                    transaction.replace(R.id.payment_layout, payokFragment);

                    transaction.commit();
                } else {
                    Toast.makeText(view.getContext(), "余额不足，请充值", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void getDate() {
        Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        dateTime = mYear + "-" + (mMonth + 1) + "-" + mDay;
    }
}
