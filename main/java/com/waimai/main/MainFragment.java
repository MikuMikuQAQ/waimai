package com.waimai.main;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {

    private View view;
    private List<ShopList> shopLists = new ArrayList<>();
    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private SwipeRefreshLayout refreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        if (view == null) view = inflater.inflate(R.layout.fragment_main,container,false);
        ViewGroup group = (ViewGroup) view.getParent();
        if (group != null) group.removeView(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        toolbar = (Toolbar) view.findViewById(R.id.main_toolbar);

        addShopList();

        recyclerView = (RecyclerView) view.findViewById(R.id.main_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.addItemDecoration(new DividerItemDecoration(this.getActivity(),DividerItemDecoration.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        ShopRecyclerAdapter adapter = new ShopRecyclerAdapter(this.getActivity(),shopLists);
        recyclerView.setAdapter(adapter);

        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh_main_list);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(getActivity(),"正在刷新……",Toast.LENGTH_SHORT).show();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(),"刷新完成",Toast.LENGTH_SHORT).show();
                        refreshLayout.setRefreshing(false);
                    }
                },1000);
            }
        });

    }

    public void addShopList(){
        ShopList list;
        for (int i = 1;i<=6;i++){
        list = new ShopList(R.drawable.b8ced22b48d6914b7b436deb70303c3f,"第"+i+"食堂","月销 666","学校食堂","起送 ¥0","每单送20积分");
        shopLists.add(list);
        }
    }
}
