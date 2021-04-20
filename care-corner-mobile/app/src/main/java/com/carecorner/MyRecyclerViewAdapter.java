package com.carecorner;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private List<Journal> mData;
    private ItemClickListener mOnBtnClickListener;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    MyRecyclerViewAdapter(Context context, List<Journal> data, ItemClickListener mOnBtnClickListener) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.mOnBtnClickListener = mOnBtnClickListener;
    }

    //inflates row layout when needed from xml
    @Override
    public MyRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_items_view, parent, false);
        return new ViewHolder(rowItem, mOnBtnClickListener);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Journal myJournal = mData.get(position);
        holder.myTextView.setText(myJournal.getName());
        holder.dateText.setText(myJournal.getDate());
        holder.timeText.setText(myJournal.getTime());
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView, dateText, timeText;
        Button btnDelete;
        ItemClickListener mOnBtnClickListener;

        ViewHolder(View itemView, final ItemClickListener mOnBtnClickListener) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.textView1);
            btnDelete = itemView.findViewById(R.id.btnDelete2);
            dateText = itemView.findViewById(R.id.textViewDate);
            timeText = itemView.findViewById(R.id.textViewTime);
            itemView.setOnClickListener(this);

            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION)
                        mOnBtnClickListener.onDeleteBtnClick(position);
                    //Need to remove physcially
                }
            });
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    Journal getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
        void onDeleteBtnClick(int position);
    }
}