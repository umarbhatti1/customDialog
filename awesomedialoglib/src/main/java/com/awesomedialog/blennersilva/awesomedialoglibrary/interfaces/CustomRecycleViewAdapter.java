package com.awesomedialog.blennersilva.awesomedialoglibrary.interfaces;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.awesomedialog.blennersilva.awesomedialoglibrary.R;

import java.util.List;

public class CustomRecycleViewAdapter extends RecyclerView.Adapter<CustomRecycleViewAdapter.ViewHolder> {

    private List<String> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private int rowIndex = -1;

    public CustomRecycleViewAdapter(Context context, List<String> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    @NonNull
    @Override
    public CustomRecycleViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = mInflater.inflate(R.layout.recycler_view, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomRecycleViewAdapter.ViewHolder holder, int position) {
        String string = mData.get(position);
        if (true) {
            holder.imageView.setImageResource(R.drawable.ic_attach_money_black_24dp);
        } else {
            holder.imageView.setImageDrawable(null);
        }
        holder.myTextView.setText(string);
        holder.linearLayout.setOnClickListener(view -> {
            rowIndex = position;
            mClickListener.onItemClick(view, position);
            notifyDataSetChanged();
        });
        if (rowIndex == position) {
            holder.myTextView.setBackgroundColor(Color.GRAY);
        } else {
            holder.myTextView.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    public int getItemCount() {
        return mData.size();
    }

    // convenience method for getting data at click position
    String getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;
        LinearLayout linearLayout;
        ImageView imageView;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.text1);
            imageView = itemView.findViewById(R.id.imageView4);
            linearLayout = itemView.findViewById(R.id.linear);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }
}
