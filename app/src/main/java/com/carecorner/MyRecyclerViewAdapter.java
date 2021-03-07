package com.carecorner;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

/**********************************
 * This code has been adapted from:
 * Title: RecyclerView simple adapter
 * Author: Jin Lim
 * Date: 2019
 * Availability: https://stackoverflow.com/questions/40584424/simple-android-recyclerview-example
 **********************************/

public class MyRecyclerViewAdapter<ItemClickListener> extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {
    private List<String> data;
    private ItemClickListener itemClickListener;

    public MyRecyclerViewAdapter(JournalRecyclerMain journalRecyclerMain, List<String> data){
        this.data = data;
    }
    //inflates row layout when needed from xml
    @Override
    public MyRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_items_view, parent, false);
        return new ViewHolder(rowItem);
    }
    //binds data to textview in each row
    @Override
    public void onBindViewHolder(MyRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.textView.setText(this.data.get(position));
    }
    //total number of rows
    @Override
    public int getItemCount() {
        return this.data.size();
    }
    //stores and recycles views as they are scrolled off screen
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView textView;
       // private Button btnDelete;
        public ViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            this.textView = view.findViewById(R.id.textview);
         //   btnDelete = (Button) view.findViewById(R.id.btnDelete);
        }
        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(), "position : " + getLayoutPosition() + " text : " + this.textView.getText(), Toast.LENGTH_SHORT).show();
        }
    }
    //get data at click position
    int getItem(int id) {
        return data.get(id);
    }

    //allows click events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
    //parent activity will implement this method to respond to click events
    public interface ItemClickListner {
        void onItemClick(View view, int position);
    }
}
