package com.carecorner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ReportingAdapter extends RecyclerView.Adapter<ReportingAdapter.ViewHolder> {

    private List<Reporting> rData;
    private ItemClickListener rOnBtnClickListener;
    private LayoutInflater rInflater;
    private ItemClickListener rClickListener;

    ReportingAdapter(Context context, List<Reporting> data, ItemClickListener OnBtnClickListener) {
        this.rInflater = LayoutInflater.from(context);
        this.rData = data;
        this.rOnBtnClickListener = OnBtnClickListener;
    }
    @Override
    public ReportingAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.reporting_adapter_activity, parent, false);
        return new ReportingAdapter.ViewHolder(rowItem, rOnBtnClickListener);
    }

    @Override
    public void onBindViewHolder(ReportingAdapter.ViewHolder holder, int position) {
        Reporting myReporting = rData.get(position);
        holder.thisTextView.setText(myReporting.getName());
    }

    @Override
    public int getItemCount() {
        return rData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView thisTextView;
        //Button btnDelete;
        ReportingAdapter.ItemClickListener OnBtnClickListener;

        ViewHolder(View itemView, final ReportingAdapter.ItemClickListener OnBtnClickListener) {
            super(itemView);
            thisTextView = itemView.findViewById(R.id.textView2);
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
            if (rClickListener != null) rClickListener.onItemClick(view, getAdapterPosition());
        }
    }
    Reporting getItem(int id) {
        return rData.get(id);
    }

    void setClickListener(ReportingAdapter.ItemClickListener itemClickListener) {
        this.rClickListener = itemClickListener;
    }
    public interface ItemClickListener {
        void onItemClick(View view, int position);
        void onDeleteBtnClick(int position);
    }
}
