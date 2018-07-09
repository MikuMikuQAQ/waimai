package com.waimai.main;

import android.os.Bundle;
import android.os.Handler;
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
import android.widget.Toast;

import com.waimai.dao.DingdanDAO;
import com.waimai.model.Waimai_dingdan;

import java.util.ArrayList;
import java.util.List;

public class DingdanFragment extends Fragment{

    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private List<Dingdan> dingdanList = new ArrayList<>();
    private List<Waimai_dingdan> dingdanLists;
    private DingdanDAO dingdanDAO;
    private View view;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) view = inflater.inflate(R.layout.fragment_dingdan,container,false);
        ViewGroup group = (ViewGroup) view.getParent();
        if (group != null) group.removeView(view);
        return view;
    }
//https://blog.csdn.net/android_zhengyongbo/article/details/68922763?locationNum=12&fps=1
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar = (Toolbar) view.findViewById(R.id.dingdan_toolbar);

        addDingdanList();

        recyclerView = (RecyclerView) view.findViewById(R.id.dingdan_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.addItemDecoration(new DividerItemDecoration(this.getActivity(), DividerItemDecoration.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        DingdanRecyclerAdapter adapter = new DingdanRecyclerAdapter(this.getActivity(),dingdanList);
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh_list);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(getActivity(),"正在刷新……",Toast.LENGTH_SHORT).show();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(),"刷新完成",Toast.LENGTH_SHORT).show();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                },1000);

            }
        });
    }

    private void addDingdanList(){
        Dingdan dingdan;
        dingdanDAO = new DingdanDAO(this.getActivity());
        dingdanLists = dingdanDAO.getScrollData(0,(int)dingdanDAO.getCount());
        String[] strings = new String[dingdanLists.size()];
        PlaceAddress address;
        for (int i = 0; i < dingdanLists.size(); i++){
            Waimai_dingdan waimai_dingdan = dingdanLists.get(i);
            dingdan = new Dingdan(R.drawable.b8ced22b48d6914b7b436deb70303c3f,waimai_dingdan.getShop(),"外卖等"+waimai_dingdan.getFoodnum()+"项商品",waimai_dingdan.getFoodmoney(),waimai_dingdan.getBuytime(),"订单已送达");
            dingdanList.add(dingdan);
        }
    }

}
