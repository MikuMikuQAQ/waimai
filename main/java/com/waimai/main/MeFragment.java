package com.waimai.main;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.waimai.dao.UsermanageDAO;
import com.waimai.model.Waimai_usermanage;

import java.util.ArrayList;
import java.util.List;

public class MeFragment extends Fragment {

    private RecyclerView recyclerView;

    private List<Set> setlist = new ArrayList<>();

    private AppCompatTextView meUsername, moneyText, integralText;

    private UsermanageDAO usermanageDAO;

    private Waimai_usermanage usermanage;

    private RelativeLayout balanceLayout, integralLayout;

    private Double balance;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        usermanageDAO = new UsermanageDAO(getContext());

        meUsername = (AppCompatTextView) view.findViewById(R.id.me_username);
        moneyText = (AppCompatTextView) view.findViewById(R.id.meony_text);
        integralText = (AppCompatTextView) view.findViewById(R.id.integral_text);

        meUsername.setText(usermanageDAO.find().getUsername());
        moneyText.setText(String.valueOf(usermanageDAO.find().getBalance()));
        integralText.setText(String.valueOf(usermanageDAO.find().getIntegral()));

        balanceLayout = (RelativeLayout) view.findViewById(R.id.balance_layout);
        integralLayout = (RelativeLayout) view.findViewById(R.id.integral_layout);

        balanceLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                balance = usermanageDAO.find().getBalance();
                //balance += 50;
                //usermanageDAO.updateMoney(balance,0);
                //moneyText.setText(String.valueOf(usermanageDAO.find().getBalance()));
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                final String[] strings = {"100", "50", "30"};
                builder.setTitle("在线充值,请选择充值金额").setSingleChoiceItems(strings, 3, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i) {
                            case 0:
                                balance += 100;
                                break;
                            case 1:
                                balance += 50;
                                break;
                            case 2:
                                balance += 30;
                                break;
                            default:
                                break;
                        }
                    }
                }).setPositiveButton("充值", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        usermanageDAO.updateMoney(balance, 0);
                        moneyText.setText(String.valueOf(usermanageDAO.find().getBalance()));
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();
            }
        });

        addSetList();

        recyclerView = (RecyclerView) view.findViewById(R.id.setlist);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.addItemDecoration(new DividerItemDecoration(this.getActivity(), DividerItemDecoration.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        SetRecyclerAdapter adapter = new SetRecyclerAdapter(this.getActivity(), setlist);
        recyclerView.setAdapter(adapter);
    }

    private void addSetList() {
        Set pwd = new Set("修改密码", R.drawable.cog_solid);
        setlist.add(pwd);
        Set place = new Set("送餐地址", R.drawable.ic_gps_locate);
        setlist.add(place);
        Set logout = new Set("退出账户", R.drawable.unlink);
        setlist.add(logout);
    }
}
