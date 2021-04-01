package com.carecorner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ReportingAdapter extends RecyclerView.Adapter<ReportingAdapter.ViewHolder> {

    private List<reporting> Data;
    private ItemClickListener OnBtnClickListener;
    private LayoutInflater Inflater;
    private ItemClickListener ClickListener;

    ReportingAdapter(Context context, List<reporting> data, ItemClickListener OnBtnClickListener) {
        this.Inflater = LayoutInflater.from(context);
        this.Data = data;
        this.OnBtnClickListener = OnBtnClickListener;
    }
    @Override
    public ReportingAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.reporting_adapter_activity, parent, false);
        return new ReportingAdapter.ViewHolder(rowItem, OnBtnClickListener);
    }

    @Override
    public void onBindViewHolder(ReportingAdapter.ViewHolder holder, int position) {
        reporting myReporting = Data.get(position);
        //holder.myTextView.setText(myReporting.getName());
    }

    @Override
    public int getItemCount() {
        return Data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //TextView myTextView;
        //Button btnDelete;
        ReportingAdapter.ItemClickListener OnBtnClickListener;

        ViewHolder(View itemView, final ReportingAdapter.ItemClickListener OnBtnClickListener) {
            super(itemView);
            //myTextView = itemView.findViewById(R.id.textView1);
            //btnDelete = itemView.findViewById(R.id.btnDelete2);
            itemView.setOnClickListener(this);

            //btnDelete.setOnClickListener(new View.OnClickListener() {
            //    @Override
            //    public void onClick(View v) {
            //        int position = getAdapterPosition();
            //        if(position != RecyclerView.NO_POSITION)
            //            mOnBtnClickListener.onDeleteBtnClick(position);
            //        //Need to remove physcially
             //   }
            //});
        }

        @Override
        public void onClick(View view) {
            if (ClickListener != null) ClickListener.onItemClick(view, getAdapterPosition());
        }
    }
    reporting getItem(int id) {
        return Data.get(id);
    }

    void setClickListener(ReportingAdapter.ItemClickListener itemClickListener) {
        this.ClickListener = itemClickListener;
    }
    public interface ItemClickListener {
        void onItemClick(View view, int position);
        void onDeleteBtnClick(int position);
    }
}
