package com.waimai.main;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ShopRecyclerAdapter extends RecyclerView.Adapter<ShopRecyclerAdapter.ShopViewHolder> {

    private Context context;
    private List<ShopList> mShopList;

    public ShopRecyclerAdapter(Context context, List<ShopList> shopList) {
        this.context = context;
        mShopList = shopList;
    }

    class ShopViewHolder extends RecyclerView.ViewHolder {

        View shopView;
        ImageView imgId;
        TextView shopName,salesNum,category,condition,offer;

        public ShopViewHolder(View itemView) {
            super(itemView);
            shopView = itemView;
            imgId = (ImageView) itemView.findViewById(R.id.shop_main_img);
            shopName = (TextView) itemView.findViewById(R.id.shop_main_name);
            salesNum = (TextView) itemView.findViewById(R.id.sales_num);
            category = (TextView) itemView.findViewById(R.id.category);
            condition = (TextView) itemView.findViewById(R.id.condition);
            offer = (TextView) itemView.findViewById(R.id.offer);
        }
    }

    @Override
    public ShopViewHolder onCreateViewHolder(final ViewGroup viewGroup, final int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.shop_item,viewGroup,false);
        final ShopViewHolder holder = new ShopViewHolder(view);
        holder.shopView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,BuyActivity.class);
                String str = holder.shopName.getText().toString();
                intent.putExtra("shopName",str);
                context.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ShopViewHolder shopViewHolder, int i) {
        ShopList list = mShopList.get(i);
        shopViewHolder.imgId.setImageResource(list.getImgId());
        shopViewHolder.shopName.setText(list.getShopName());
        shopViewHolder.salesNum.setText(list.getSalesNum());
        shopViewHolder.category.setText(list.getCategory());
        shopViewHolder.condition.setText(list.getCondition());
        shopViewHolder.offer.setText(list.getOffer());
    }

    @Override
    public int getItemCount() {
        return mShopList.size();
    }
}
