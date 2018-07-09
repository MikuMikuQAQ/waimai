package com.waimai.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class DingdanRecyclerAdapter extends RecyclerView.Adapter<DingdanRecyclerAdapter.DingdanViewHolder> {

    private Context context;
    private List<Dingdan> mDingdan;

    public DingdanRecyclerAdapter(Context context, List<Dingdan> dingdanList) {
        this.context = context;
        mDingdan = dingdanList;
    }

    public class DingdanViewHolder extends RecyclerView.ViewHolder {

        View dingdanView;
        ImageView dingdanImg;
        TextView shopName,buytimeText,foodName,status,dingdanMoney;

        public DingdanViewHolder(View itemView) {
            super(itemView);
            dingdanView = itemView;
            dingdanImg = (ImageView) itemView.findViewById(R.id.shop_img);
            shopName = (TextView) itemView.findViewById(R.id.shop_dingdan_name);
            buytimeText = (TextView) itemView.findViewById(R.id.sales_num);
            foodName = (TextView) itemView.findViewById(R.id.food_name);
            status = (TextView) itemView.findViewById(R.id.status);
            dingdanMoney = (TextView) itemView.findViewById(R.id.dingdan_money);
        }
    }

    @Override
    public DingdanViewHolder onCreateViewHolder( ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.dingdan_item,viewGroup,false);
        final DingdanViewHolder holder = new DingdanViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder( DingdanViewHolder dingdanViewHolder, int i) {
        Dingdan dingdan = mDingdan.get(i);
        dingdanViewHolder.dingdanImg.setImageResource(dingdan.getImgId());
        dingdanViewHolder.shopName.setText(dingdan.getShopName());
        dingdanViewHolder.buytimeText.setText(dingdan.getBuyTime());
        dingdanViewHolder.foodName.setText(dingdan.getFoodName());
        dingdanViewHolder.status.setText(dingdan.getStatus());
        dingdanViewHolder.dingdanMoney.setText(String.valueOf(dingdan.getFoodMoney()));
    }

    @Override
    public int getItemCount() {
        return mDingdan.size();
    }
}
