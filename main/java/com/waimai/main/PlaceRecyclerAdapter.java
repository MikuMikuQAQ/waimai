package com.waimai.main;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.waimai.dao.PlacemanageDAO;

import java.util.List;

public class PlaceRecyclerAdapter extends RecyclerView.Adapter<PlaceRecyclerAdapter.PlaceViewHolder> {

    private Context context;
    private List<PlaceAddress> mPlaceLists;
    private PlacemanageDAO placemanageDAO;

    public PlaceRecyclerAdapter(Context context, List<PlaceAddress> placeLists) {
        this.context = context;
        mPlaceLists = placeLists;
    }

    public class PlaceViewHolder extends RecyclerView.ViewHolder {

        View placeView;
        AppCompatImageView imgId;
        TextView userPlace,userName;

        public PlaceViewHolder(View itemView) {
            super(itemView);
            placeView = itemView;
            imgId = (AppCompatImageView) itemView.findViewById(R.id.trash_img);
            userName = (TextView) itemView.findViewById(R.id.user_name);
            userPlace = (TextView) itemView.findViewById(R.id.user_place);
        }
    }

    @Override
    public PlaceViewHolder onCreateViewHolder( ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.place_item,viewGroup,false);
        final PlaceViewHolder holder = new PlaceViewHolder(view);
        placemanageDAO = new PlacemanageDAO(context);
        holder.imgId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(view.getContext(),"删除功能敬请期待",Toast.LENGTH_SHORT).show();
                Intent intent;
                int position = holder.getAdapterPosition()+1;
                placemanageDAO.delete(position);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(PlaceViewHolder placeViewHolder, int i) {
        PlaceAddress address = mPlaceLists.get(i);
        placeViewHolder.imgId.setBackgroundResource(address.getImgId());
        placeViewHolder.userPlace.setText(address.getUserAddreess());
        placeViewHolder.userName.setText(address.getUserName());
    }

    @Override
    public int getItemCount() {
        return mPlaceLists.size();
    }
}
