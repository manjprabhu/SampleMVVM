package com.example.samplemvvm.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.samplemvvm.model.Item;

import java.util.List;


public class ResponseRecycleAdapter extends RecyclerView.Adapter<ResponseRecycleAdapter.ViewHolder> {

    private List<Item> mItems;

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(android.R.id.text1);
        }
    }

    public ResponseRecycleAdapter(List<Item> posts) {
        super();
        mItems = posts;
    }

    @Override
    public ResponseRecycleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View postView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        ViewHolder viewHolder = new ViewHolder(postView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ResponseRecycleAdapter.ViewHolder holder, int position) {
        Item item = mItems.get(position);
        holder.textView.setText(item.getOwner().getDisplayName());
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
