package com.waimai.main;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class SetRecyclerAdapter extends RecyclerView.Adapter<SetRecyclerAdapter.SetViewHolder> {

    private Context context;
    private List<Set> mSetList;

    class SetViewHolder extends RecyclerView.ViewHolder {
        View setView;
        ImageView setImage;
        TextView setText;

        public SetViewHolder(View itemView) {
            super(itemView);
            setView = itemView;
            setImage = (ImageView) itemView.findViewById(R.id.set_img);
            setText = (TextView) itemView.findViewById(R.id.set_name);
        }
    }

    public SetRecyclerAdapter(Context context, List<Set> setList) {
        this.context = context;
        mSetList = setList;
    }

    @Override
    public SetViewHolder onCreateViewHolder(ViewGroup viewGroup, final int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.set_item, viewGroup, false);
        final SetViewHolder holder = new SetViewHolder(view);
        holder.setView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                int position = holder.getAdapterPosition();
                Set set = mSetList.get(position);
                switch (position) {
                    case 0:
                        intent = new Intent(context,ModifypassActivity.class);
                        context.startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(context,PlaceActivity.class);
                        context.startActivity(intent);
                        break;
                    case 2:
                        if (MainActivity.class.isInstance(context)) {
                            MainActivity activity = (MainActivity) context;
                            activity.finish();
                        }
                        break;
                    default:
                        break;
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(SetViewHolder setViewHolder, int i) {
        Set set = mSetList.get(i);
        setViewHolder.setImage.setImageResource(set.getImgId());
        setViewHolder.setText.setText(set.getName());
    }

    @Override
    public int getItemCount() {
        return mSetList.size();
    }
}
