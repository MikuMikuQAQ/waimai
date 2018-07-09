package com.waimai.main;

import android.content.Context;
import android.os.Message;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class MenurightRecyclerAdapter extends RecyclerView.Adapter<MenurightRecyclerAdapter.MenurightViewHolder> {

    private Context context;
    private List<MenuRight> mMenuRightLists;
    public double sum = 0;
    private BuyActivity buyActivity;
    private int count = 0;

    public MenurightRecyclerAdapter(Context context, List<MenuRight> mMenuRightLists) {
        this.context = context;
        this.mMenuRightLists = mMenuRightLists;
    }

    public class MenurightViewHolder extends RecyclerView.ViewHolder {

        View rightView;
        AppCompatImageView imgId, menuAdd, menuLess;
        AppCompatTextView menuFoodName, menuMoney, menuNum, buySum;

        public MenurightViewHolder(View itemView) {
            super(itemView);
            rightView = itemView;
            imgId = (AppCompatImageView) itemView.findViewById(R.id.menu_img);
            menuFoodName = (AppCompatTextView) itemView.findViewById(R.id.menu_food_name);
            menuMoney = (AppCompatTextView) itemView.findViewById(R.id.menu_money);
            menuNum = (AppCompatTextView) itemView.findViewById(R.id.menu_num);
            menuAdd = (AppCompatImageView) itemView.findViewById(R.id.menu_add);
            menuLess = (AppCompatImageView) itemView.findViewById(R.id.menu_less);
            buySum = (AppCompatTextView) itemView.findViewById(R.id.buy_sum);

        }
    }

    @Override
    public MenurightViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.menu_right, viewGroup, false);
        final MenurightViewHolder holder = new MenurightViewHolder(view);
        holder.menuAdd.setOnClickListener(new View.OnClickListener() {
            int sum = 0;

            @Override
            public void onClick(View view) {
                sum = Integer.parseInt(holder.menuNum.getText().toString());
                sum++;
                holder.menuNum.setText(String.valueOf(sum));
                double food = Double.parseDouble(holder.menuMoney.getText().toString());
                foodCount(0);
                jiSuan(food, 0);
            }
        });
        holder.menuLess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int sum = Integer.parseInt(holder.menuNum.getText().toString());
                if (sum > 0) {
                    sum--;
                    holder.menuNum.setText(String.valueOf(sum));
                    double food = Double.parseDouble(holder.menuMoney.getText().toString());
                    foodCount(1);
                    jiSuan(food, 1);
                }

            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(MenurightViewHolder menurightViewHolder, int i) {
        MenuRight right = mMenuRightLists.get(i);
        menurightViewHolder.imgId.setImageResource(right.getImgId());
        menurightViewHolder.menuFoodName.setText(right.getBuyRightTitle());
        menurightViewHolder.menuMoney.setText(right.getMoney());
    }

    @Override
    public int getItemCount() {
        return mMenuRightLists.size();
    }

    public void jiSuan(double js, int jc) {
        buyActivity = (BuyActivity) context;
        switch (jc) {
            case 0:
                sum = js + sum;
                buyActivity.updateUI(sum);
                break;
            case 1:
                if (sum >= js) {
                    sum = sum - js;
                    buyActivity.updateUI(sum);
                }
                break;
            default:
                break;
        }
    }

    public double getJiSuan() {
        return sum;
    }

    public void foodCount(int count) {
        switch (count) {
            case 0:
                this.count += 1;
                break;
            case 1:
                if (this.count <= 0) break;
                else {
                    this.count -= 1;
                    break;
                }
            default:
                break;
        }
    }

    public int getfoodCount() {
        return count;
    }
}
