package com.example.samplemvvm.adapters;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.samplemvvm.R;
import com.example.samplemvvm.model.Item;

public class ResponseAdapter extends PagedListAdapter<Item, ResponseAdapter.ItemViewHolder> {

    private Context mContext;

    public ResponseAdapter(Context context) {
        super(callback);
        this.mContext = context;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.response_list_item,viewGroup,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int i) {
        Item item = getItem(i);
        itemViewHolder.tv.setText(item.getOwner().getDisplayName());
        Glide.with(mContext).load(item.getOwner().getProfileImage()).error(Glide.with(itemViewHolder.iv).load(R.drawable.ic_launcher_background)).into(itemViewHolder.iv);

    }

    private static DiffUtil.ItemCallback<Item> callback = new DiffUtil.ItemCallback<Item>() {
        @Override
        public boolean areItemsTheSame(@NonNull Item oldItem, @NonNull Item newItem) {
            return oldItem.getQuestionId() == newItem.getQuestionId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Item oldItem, @NonNull Item newItem) {
            return oldItem.equals(newItem);
        }
    };

    class ItemViewHolder  extends RecyclerView.ViewHolder {

        private ImageView iv;
        private TextView tv;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv_title);
            iv = itemView.findViewById(R.id.iv_logo);
        }
    }
}
