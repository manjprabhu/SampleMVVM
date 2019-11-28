package com.example.samplemvvm.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.samplemvvm.R;
import com.example.samplemvvm.model.Item;

import java.util.List;

public class ResponseRecycleAdapter extends RecyclerView.Adapter<ResponseRecycleAdapter.ViewHolder> {

    private List<Item> mItems;
    private Context mContext;

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;
        public ImageView imageView;


        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv_title);
            imageView =(ImageView) itemView.findViewById(R.id.iv_logo);
        }
    }

    public ResponseRecycleAdapter(List<Item> posts,Context context) {
        super();
        mItems = posts;
        this.mContext = context;
    }

    @Override
    public ResponseRecycleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View postView = LayoutInflater.from(parent.getContext()).inflate(R.layout.response_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(postView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ResponseRecycleAdapter.ViewHolder holder, int position) {
        Item item = mItems.get(position);
        holder.textView.setText(item.getOwner().getDisplayName());
        Glide.with(mContext).load(item.getOwner().getProfileImage()).error(Glide.with(holder.imageView).load(R.drawable.ic_launcher_background)).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void updateResponse(List<Item> items) {
        mItems = items;
        notifyDataSetChanged();
    }
}
